package com.dnikitin.http.server;

public class HttpServerRunner {
    public static void main(String[] args) {
        HttpServer server = new HttpServer(8080);
        server.start();
    }
}
