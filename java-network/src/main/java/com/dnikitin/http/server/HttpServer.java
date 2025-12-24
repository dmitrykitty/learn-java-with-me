package com.dnikitin.http.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HttpServer {

    private final int PORT;
    /**
     * Use of a thread pool to implement the "Thread-per-Request" model.
     * This decouples connection acceptance from request processing.
     */
    private final Executor pool;
    private boolean running = true;

    /**
     * HTTP protocol requires CRLF (\r\n) as a line terminator for headers.
     */
    private static final String CRLF = "\r\n";
    /**
     * The base directory where static assets are located on the filesystem.
     */
    private static final Path ROOTPATH = Path.of("java-network/src/main/java/com/dnikitin/http/client/mainmimetypes");

    public HttpServer(int port, int poolSize) {
        PORT = port;
        pool = Executors.newFixedThreadPool(poolSize);
        //we ca use newVirtualThreadPerTask and use simple send without sendAsync an Future
    }

    public void start() {
        try {
            /*
             * ServerSocket binds to the specified port and listens for incoming TCP connections.
             * The OS manages a 'backlog' queue of connections waiting to be accepted.
             */
            ServerSocket server = new ServerSocket(PORT);
            while (running) {
                try {
                    /*
                     * The accept() method is a blocking call. It waits until a new client
                     * completes the TCP three-way handshake.
                     */
                    Socket socket = server.accept();
                    System.out.println("Accepted connection from " + socket.getInetAddress().getHostName());

                    /*
                     * Offloading the socket handling to a worker thread.
                     * This allows the main thread to return to accept() immediately.
                     */
                    pool.execute(() -> processSocket(socket));
                } catch (IOException e) {
                    // Inner catch prevents a single connection error from stopping the entire server.
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            // Fatal error: occurs if the port is already in use or the OS denies binding.
            throw new RuntimeException(e);
        }
    }

    private void processSocket(Socket socket) {
        /*
         * try-with-resources ensures that the Socket and its streams are closed automatically.
         * Closing the socket sends a FIN packet to the client, terminating the TCP session.
         */
        try (socket; //to close socket after reading
             BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
             BufferedOutputStream outputStream = new BufferedOutputStream(new BufferedOutputStream(socket.getOutputStream()))) {

            /*
             * Parsing the Request Line (e.g., "GET /index.html HTTP/1.1").
             */
            String requestLine = readLine(inputStream);
            if (requestLine.isEmpty()) return;

            String[] parts = requestLine.split(" ");
            if (parts.length < 2) return;

            String path = parts[1];
            // Defaulting to index.html if the root path is requested.
            if (path.equals("/")) {
                path = "/index.html";
            }

            /*
             * Mapping the URI to the physical filesystem.
             * substring(1) is used to convert an absolute URI path (starting with /)
             * into a relative path for the resolve() method.
             */
            Path filePath = ROOTPATH.resolve(path.substring(1));

            byte[] body;
            String statusLine;

            /*
             * Verification of resource existence and preventing directory access (Files.isDirectory).
             */
            if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
                body = Files.readAllBytes(filePath);
                statusLine = "HTTP/1.1 200 OK";
            } else {
                body = "<html><body><h1>404 Not Found</h1></body></html>".getBytes();
                statusLine = "HTTP/1.1 404 Not Found";
            }

            /*
             * Building the HTTP response. Two CRLF sequences represent the end of the header section.
             */
            String headers = statusLine + CRLF +
                    "Content-Type: text/html" + CRLF +
                    "Content-Length: " + body.length + CRLF +
                    CRLF;

            outputStream.write(headers.getBytes());
            outputStream.write(body);
            /*
             * flush() is essential to force the buffered data through the socket
             * buffer to the OS network stack.
             */
            outputStream.flush();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Custom readLine implementation to read byte-by-byte.
     * This avoids the "greedy buffer" issue of BufferedReader, ensuring that
     * we don't over-read into the Request Body.
     */
    private String readLine(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        int b;
        while ((b = is.read()) != -1) {
            if (b == '\n') break;
            if (b != '\r') sb.append((char) b);
        }
        return sb.toString();
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
