package com.dnikitin.javaIO.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FilesExample {

    static void main() throws IOException {
        Path path = Path.of("resources", "output.txt");
        Files.write(path, List.of("Hello!", "Dima"));
        try(BufferedWriter fileWriter = Files.newBufferedWriter(path)){
            //------//--------

        }
    }
}
