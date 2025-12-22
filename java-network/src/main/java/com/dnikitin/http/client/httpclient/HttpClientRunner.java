package com.dnikitin.http.client.httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpResponse.*;

public class HttpClientRunner {
    static void main() throws IOException, InterruptedException {
        try (HttpClient httpClient = HttpClient.newBuilder() //thread safe
                .version(HttpClient.Version.HTTP_1_1) //HTTP_2 by default
                .build()) {

            HttpRequest request = HttpRequest.newBuilder(URI.create("https://www.google.com"))
                    .GET()
                    .build();
            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());//or sentAsdync -> Completable<Future>
            //bodyHandler way, how we can read responce body (convert to string)

            System.out.println(response.statusCode());
            System.out.println(response.headers());
            System.out.println(response.body());

            HttpRequest request2 = HttpRequest.newBuilder(URI.create("https://www.google.com"))
                    .POST(HttpRequest.BodyPublishers.ofString("give"))
                    .build();


        }
    }
}
