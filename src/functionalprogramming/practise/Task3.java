package functionalprogramming.practise;

import java.util.Map;

public class Task3 {
    static void main() {
        Map<String, Integer> map = Map.of(
                "string1", 1,
                "strin2", 2,
                "string3", 3,
                "string4", 5,
                "strin5", 5
        );

        int sum = map.entrySet().stream()
                .filter(entry -> entry.getKey().length() < 7)
                .map(Map.Entry::getValue)
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println(sum);
    }
}
