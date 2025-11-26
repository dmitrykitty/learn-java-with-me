package javaIO.practice;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Counter {
    /**
     * Given a text file. For each line, find and print the maximum number of consecutive digits.
     */
    public static void countNumsAmountInEachLine(Path path) throws IOException {
        Files.readAllLines(path).stream()
                .map(Counter::countNums)
                .forEach(System.out::println);
    }

    private static int countNums(String s) {
        int count = 0;
        int maxCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                count++;
            } else {
                if (maxCount < count)
                    maxCount = count;
                count = 0;
            }
        }
        return maxCount;
    }

    public static void countLettersFrequency(Path from, Path to) throws IOException {
        String text = Files.readString(from);
        Map<Character, Integer> result = countLetters(text);
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(to.toFile()))) {
            for (Map.Entry<Character, Integer> entry : result.entrySet()) {
                fileWriter.append(entry.getKey() + " - " + entry.getValue());
                fileWriter.newLine();
            }
        }
    }

    private static Map<Character, Integer> countLetters(String text) {
        Map<Character, Integer> result = new TreeMap<>();
        for (int i = 0; i < text.length(); i++) {
            char curChar = text.charAt(i);
            if (Character.isAlphabetic(curChar)) {
                curChar = Character.toLowerCase(curChar);
                result.put(curChar, result.getOrDefault(curChar, 0) + 1);
            }
        }
        return result;
    }
}
