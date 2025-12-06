package com.dnikitin.functionalprogramming.practise;

import java.util.List;

public class Task1 {
    static void main() {
        List<Integer> lst = List.of(1, 2, 3, 4, 5, 10, 15, 12, 20, 25, 25);

        lst.stream()
                .filter(a -> a % 5 == 0 && a % 2 != 0 )
                .mapToInt(Integer::intValue)
                .average()
                .ifPresent(System.out::println);
    }
}
