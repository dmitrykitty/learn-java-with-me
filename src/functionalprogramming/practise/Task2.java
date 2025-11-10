package functionalprogramming.practise;

import java.util.List;
import java.util.stream.Collectors;

public class Task2 {
    static void main() {
        List<String> stringList = List.of(
                "string-1",
                "string-2",
                "string-3",
                "string-4",
                "string-10",
                "string-10",
                "string-10",
                "string-12",
                "string-16"
        );

        int uniqueStrings = stringList.stream()
                .filter(a -> a.length() > 8)
                .collect(Collectors.toSet())//konwertacja stream -> set
                .size();

        System.out.println(uniqueStrings);

        long uniqueStrings2 = stringList.stream()
                .filter(a -> a.length() > 8)
                .distinct() //uusuwa dublikaty, jak w SQL
                .count();
    }
}
