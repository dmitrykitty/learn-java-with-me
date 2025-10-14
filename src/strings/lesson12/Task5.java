package strings.lesson12;

import java.util.Arrays;

public class Task5 {
    static void main() {
        String text = "1234567890";
        System.out.println(Arrays.toString(splitIntoNChars(text, 3)));

    }

    static String[] splitIntoNChars(String text, int n){
        int arraySize = text.length() % n == 0 ? text.length() / n : text.length() / n + 1;
        String[] result = new String[arraySize];
        for(int i = 0; i < text.length() / n; i++){
            result[i] = text.substring(i * n, (i + 1) * n);
        }
        if(text.length() % n != 0){
            result[result.length - 1] = text.substring(text.length() - text.length() % n);
        }
        return result;
    }
}
