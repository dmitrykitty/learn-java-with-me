package javaIO.practice;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Set;


private class WordStructure {
    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u');

    /**
     * Given a text file, find and print to the console all words starting with a vowel
     */
    private static void printAllWordsStatsWithVowel(Path path) throws IOException {
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNext()) {
                String next = scanner.next();
                if (VOWELS.contains(next.charAt(0))) {
                    System.out.println(next);
                }
            }
        }
    }

    /**
     * Given a text file, find and print to the console all words
     * where the last letter of one word matches the first letter of the next word.
     */
    private static void printWordsEndsWithFirstLetterNextWord(Path path) throws IOException {
        try (Scanner scanner = new Scanner(path)) {
            if(!scanner.hasNext()){
                return;
            }
            String currWord = scanner.next();
            while (scanner.hasNext()){
                String next = scanner.next();

                char lastOfFirst = currWord.charAt(currWord.length() - 1);
                char firstOfSecond = next.charAt(0);
                if(Character.toUpperCase(lastOfFirst) == Character.toUpperCase(firstOfSecond)){
                    System.out.println(currWord + " " + next);
                }
                currWord = next;
            }
        }
    }
}
