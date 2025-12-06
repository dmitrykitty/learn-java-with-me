package com.dnikitin.internal.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class SocketServerRunner {
    static void main() {
        //params - port, backlog(amount of connections we can keep)
        try (ServerSocket serverSocket = new ServerSocket(7777);
             //wait until client connect to the server
             var socket = serverSocket.accept();
             var output = new DataOutputStream(socket.getOutputStream());
             var input = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in);
        ) {

            String requestFromClient = input.readUTF();
            while (!"stop".equals(requestFromClient)){
                System.out.println("ClientRequest: " + requestFromClient);
                String responseFromServer = scanner.nextLine();
                output.writeUTF(responseFromServer);
                requestFromClient = input.readUTF();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
