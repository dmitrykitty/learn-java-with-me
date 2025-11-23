package javaIO.input_stream;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;

public class InputStreamExample {
    static void main() throws IOException {
        //input stream - abstract class for all typef of input streams
        //imput stream implements closable (interface close()), and cloasable implements autoclosable for
        //try with resources

        //two ways howe to add file by the path
        File file = new File(String.join(File.separator, "resources", "test.txt"));
        File resources = Path.of("resources", "test.txt").toFile();

        try (FileInputStream fileInputStream = new FileInputStream(resources)) {
            byte[] bytes = fileInputStream.readAllBytes();
            String song = new String(bytes);
            System.out.println(song);
        }

        try(FileInputStream fileInputStream = new FileInputStream(resources)){
            byte[] bytes2 = new byte[fileInputStream.available()]; //available() -> amount of available bytes
            byte currentByte;
            int counter = 0;
            //int read = fileInputStream.read();//-> -1 if end of the file
            while((currentByte = (byte) fileInputStream.read()) != -1 && counter <= 30){
                bytes2[counter++] = currentByte;
            }
            System.out.println("-----------------------");
            System.out.println(new String(bytes2, 0, counter)); //to read from 0 to counter byte
        }
    }
}
