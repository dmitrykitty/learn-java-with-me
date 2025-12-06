package com.dnikitin.functionalprogramming.practise;

import java.util.List;
import java.util.stream.Collectors;

public class Task4 {
    static void main() {

        List<Integer> lst = List.of(5, 2, 4, 2, 1);

        String result = lst.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
        System.out.println(result);

    }
}
