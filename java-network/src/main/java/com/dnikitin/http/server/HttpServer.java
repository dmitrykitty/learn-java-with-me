package com.dnikitin.http.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class HttpServer {

    private final int PORT;

    public HttpServer(int port) {
        PORT = port;
    }

    public void start() {
        try {
            ServerSocket server = new ServerSocket(PORT);
            Socket socket = server.accept();

            processSocket(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processSocket(Socket socket) {
        try (socket; //to close socket after reading
             BufferedInputStream inputStream = new BufferedInputStream(socket.getInputStream());
             BufferedOutputStream outputStream = new BufferedOutputStream(new BufferedOutputStream(socket.getOutputStream()))) {

            //logic of how to parse and understand request
            String line;
            int length = 0;
            while (!(line = readLine(inputStream)).isEmpty()) {
                System.out.println("Header: " + line);
                if (line.toLowerCase().startsWith("content-length:")) {
                    length = Integer.parseInt(line.split(":")[1].trim());
                }
            }
            System.out.println("RequestBody: " + new String(inputStream.readNBytes(length)));

            byte[] body = Files.readAllBytes(Path.of("java-network/src/main/java/com/dnikitin/http/client/mainmimetypes/first.html"));
            byte[] headers = """
                    HTTP/1.1 200 OK\r
                    content-type: text/html\r
                    content-length: %s
                    """.formatted(body.length).getBytes();

            outputStream.write(headers);
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(body);

        } catch (IOException e) {
            // TODO: log erros message
            throw new RuntimeException(e);
        }
    }
    private String readLine(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        int b;
        while ((b = is.read()) != -1) {
            if (b == '\n') break;
            if (b != '\r') sb.append((char) b);
        }
        return sb.toString();
    }
}
