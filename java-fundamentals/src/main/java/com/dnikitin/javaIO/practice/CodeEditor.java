package com.dnikitin.javaIO.practice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CodeEditor {
    /**
     * Given a file with Java code. Read the program text from the file and replace the word `public` with `private`
     * in the declarations of class attributes and methods. Save the result to another pre-created file.
     * @param from from whitch file
     * @param to to whitch file will be saved result
     * @throws IOException no file
     */
    public static void changePublicToPrivate(Path from, Path to) throws IOException {
        String stringValue = Files.readString(from);
        String string = stringValue.replace("public", "private");
        Files.writeString(to, string);
    }
}
