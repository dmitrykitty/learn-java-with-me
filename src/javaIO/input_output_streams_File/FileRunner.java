package javaIO.input_output_streams_File;

import java.io.File;
import java.io.IOException;

public class FileRunner {
    static void main() throws IOException {
        File file = new File("resources/test.txt");

        System.out.println(file.createNewFile()); //tworzenie pliku - true jezeli stworzono i nie istnial wczesniej

        System.out.println(file.exists());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.length()); //size
        //canExecute() - czy mozemy wykonywac
        //canRead() - czy mozemy odczytywac
        //canWrite() - czy mozemy zmieniac

        System.out.println(file.canExecute());
        System.out.println(file.getAbsolutePath()); //D:\study\AGH\JAVA_NEW\course_2\resources\test.txt

        File dir = new File("resources/test/dir");

        if (dir.mkdirs()) {
            System.out.println("Directories " + dir + " were successfully created ");
        } else {
            System.out.println("Error creating directories");
        }
        String[] list = dir.list(/*filter*/); //-> zwraca array z nazw plikow

    }
}
