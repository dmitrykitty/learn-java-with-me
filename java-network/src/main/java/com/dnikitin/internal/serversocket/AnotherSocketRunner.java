package com.dnikitin.internal.serversocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class AnotherSocketRunner {
    static void main() throws UnknownHostException {

        String host = "localhost";
        InetAddress inet4Address = Inet4Address.getByName(host);
        int port = 7777;

        try (var socket = new Socket(inet4Address, port);
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in);
        ) {

            while (scanner.hasNextLine()) {
                String request = scanner.nextLine();
                outputStream.writeUTF(request);
                System.out.println("Server: " + inputStream.readUTF());

            }

        } catch (IOException e) {
            throw new RuntimeException("Connection failed", e);
        }
    }
}
