package com.dnikitin.http.client.url;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class UrlRunner {

    static void main() throws IOException {
        URL url = new URL("file:D:\\study\\AGH\\JAVA_NEW\\courses\\course_2\\java-fundamentals\\src\\main\\java\\com\\dnikitin\\collections\\iterator\\IteratoRunner.java");
        URLConnection conn = url.openConnection();
        System.out.println(new String(conn.getInputStream().readAllBytes()));
    }

    private static void getGoogleConnection() throws IOException {
        URL url = new URL("https://www.google.com");
        URLConnection urlConnection = url.openConnection();

        urlConnection.getHeaderFields(); //map<string, list<string>>
        urlConnection.getInputStream();//return body
        urlConnection.getContent(); //the same, as getInputStream
    }
}
