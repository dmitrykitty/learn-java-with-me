package com.dnikitin.javaIO.wirter;

import java.io.*;
import java.nio.file.Path;

public class WriterExample {
    static void main() throws IOException {
        File output = Path.of("resources", "output.md").toFile();
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(output))) {
            fileWriter.append("# Java IO");
            fileWriter.newLine();
            fileWriter.append(" ### Writer");
        }
    }
}
