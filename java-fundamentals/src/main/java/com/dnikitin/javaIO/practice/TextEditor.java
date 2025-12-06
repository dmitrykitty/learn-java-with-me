package com.dnikitin.javaIO.practice;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextEditor {
    public static void reverseText(Path from, Path to) throws IOException {
        try (Stream<String> lines = Files.lines(from)) {
            List<String> stringList = lines
                    .map(line -> new StringBuilder(line).reverse().toString())
                    .toList();

            Files.write(to, stringList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void reverseText2(Path from, Path to) throws IOException {
        try (Stream<String> lines = Files.lines(from)) {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(to, StandardOpenOption.APPEND);

            lines
                    .map(StringBuilder::new)
                    .map(StringBuilder::reverse)
                    .map(StringBuilder::toString)
                    .forEach(line -> {
                        try {
                            bufferedWriter.write(line);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
