package com.dnikitin.http.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;

public class HttpClientRunner {
    static void main() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080"))
                .header("content-type", "application/json")
                .POST(BodyPublishers.ofFile(Path.of("java-network/src/main/java/com/dnikitin/http/client/mainmimetypes/first.json")))
                .build();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        System.out.println(response.headers());
        System.out.println();
        System.out.println(response.body());
    }
}
