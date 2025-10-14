package strings.lesson12;

public class Task2 {
    void main(String[] args) {
        String text = "Hello world!, Hello";
        System.out.println(EndsAndStartsWithWord(text, "Hello"));
    }


    public static boolean EndsAndStartsWithWord(String text, String value) {
        return text.endsWith(value) && text.startsWith(value);

    }

}

