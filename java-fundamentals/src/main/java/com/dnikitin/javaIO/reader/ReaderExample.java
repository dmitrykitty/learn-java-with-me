package com.dnikitin.javaIO.reader;

import java.io.*;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class ReaderExample {
    static void main() throws IOException {
        File file = Path.of("resources", "test.txt").toFile();
        try (BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            //FileReader - simple to FileInputStream, but decorating it with BufferReader
            //new methods like readLine, skip, readString and ... or lines(stream of strings)
            String string = fileReader.lines()
                    .limit(3)
                    .collect(Collectors.joining("\n"));

            System.out.println(string);
        }

    }
}
