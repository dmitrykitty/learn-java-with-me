package com.dnikitin.http.server;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class HttpClientRunner {
    /**
     * Entry point demonstrating the asynchronous capabilities of Java's HttpClient.
     * The main thread will initiate multiple requests without waiting for each to finish sequentially.
     */
    static void main() throws IOException, InterruptedException, ExecutionException {
        // Configuring the client with HTTP/1.1 to match my custom server implementation.
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        // Constructing an immutable HttpRequest object.
        // Note: BodyPublishers.ofFile automatically handles the reading of the file
        // and setting the Content-Length header based on the file size.
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/first.html"))
                .header("content-type", "application/json")
                .POST(BodyPublishers.ofFile(Path.of("java-network/src/main/java/com/dnikitin/http/client/mainmimetypes/first.json")))
                .build();

        /*
         * sendAsync() sends the request and immediately returns a CompletableFuture.
         * It does NOT block the main thread. The actual I/O operations happen
         * in the HttpClient's internal executor threads.
         */
        CompletableFuture<HttpResponse<String>> response1 = httpClient.sendAsync(request, BodyHandlers.ofString());
        CompletableFuture<HttpResponse<String>> response2 = httpClient.sendAsync(request, BodyHandlers.ofString());
        CompletableFuture<HttpResponse<String>> response3 = httpClient.sendAsync(request, BodyHandlers.ofString());

        //waiting for completing all threads
        CompletableFuture.allOf(response1, response2, response3).join();

        /*
         * get() is a blocking call. It halts the main thread until the Future is completed.
         * While we wait for response3 here, response1 and response2 are likely being
         * processed in parallel by our multi-threaded server.
         */
        System.out.println(response3.get().headers());
        System.out.println();
        System.out.println(response3.get().body());
    }
}
