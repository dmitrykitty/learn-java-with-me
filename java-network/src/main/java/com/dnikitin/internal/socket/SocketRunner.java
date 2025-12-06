package com.dnikitin.internal.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketRunner {
    static void main() {
        //http 80 by default
        //https 443
        String host = "google.com";
        InetAddress inet4Address;
        try {
            inet4Address = Inet4Address.getByName(host);
            //ipv4
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }


        int port = 80;
        //params - string proxy(dns or ip), int port
        //implements closeable
        try (var socket = new Socket(inet4Address, port);
             var outputStream = new DataOutputStream(socket.getOutputStream()); //for more comfortable work - new DataStream
             var inputStream = new DataInputStream(socket.getInputStream())) {

            // Instead of sending specific Java format (writeUTF), we send raw bytes
            // representing a valid HTTP 1.1 request ending with new lines (\r\n)
            String request = "GET / HTTP/1.1\r\n" +
                    "Host: " + host + "\r\n" +
                    "Connection: close\r\n" +
                    "\r\n"; // Empty line indicates end of headers

            outputStream.write(request.getBytes()); //request
            outputStream.flush(); //to send gropup of bytes in one

            System.out.println("Request sent: \n" + request);

            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()){
                System.out.printf(scanner.nextLine());
            }

        } catch (IOException e) {
            throw new RuntimeException("Connection failed", e);
        }
    }
}
