package javaIO.practice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class TasksRunner {
    static void main() {
        Path path = Path.of("resources", "test.txt");
        try {
            WordStructure.printAllWordsStatsWithVowel(path);
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        System.out.println("=================================");

        try {
            WordStructure.printWordsEndsWithFirstLetterNextWord(path);
        } catch (IOException _){
            System.err.println("Problem while reading file");
        }
        Path path2 = Path.of("resources", "test2.txt");
        try{
            CountNums.countNumsAmountInEachLine(path2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path path3 = new File("src/javaIO/practice/WordStructure.java").toPath();
        Path path4 = Path.of("resources", "WorldStructureNew.java");

        try{
            CodeEditor.changePublicToPrivate(path3, path4 );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try{
            TextEditor.reverseText(path3, Path.of("resources", "test3.java") );
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
