package javaIO.practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CountNums {
    /**
     * Given a text file. For each line, find and print the maximum number of consecutive digits.
    */
    public static void countNumsAmountInEachLine(Path path) throws IOException {
        Files.readAllLines(path).stream()
                .map(CountNums::countNums)
                .forEach(System.out::println);
    }

    private static int countNums(String s) {
        int count = 0;
        int maxCount = 0;
        for(int i = 0; i < s.length(); i++){
            if(Character.isDigit(s.charAt(i))){
                count++;
            }
            else{
                if(maxCount < count)
                    maxCount = count;
                count = 0;
            }
        }
        return maxCount;
    }
}
