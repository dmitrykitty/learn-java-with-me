package com.dnikitin.javaIO.outputstream;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class OutputStreamExample {
    static void main() throws IOException {
        File file = Path.of("resources", "output.txt").toFile();
        try (FileOutputStream outputStream = new FileOutputStream(file, true /*, append = boolean */)) {
            //moznaz tez dodac Decorator BufferOutputStream(działą szybciej)
            String s = "I like Java!";
            outputStream.write(System.lineSeparator().getBytes());
            outputStream.write(s.getBytes());

        }
    }

}
