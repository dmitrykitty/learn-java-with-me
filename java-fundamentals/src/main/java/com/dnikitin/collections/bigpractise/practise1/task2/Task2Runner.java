package com.dnikitin.collections.bigpractise.practise1.task2;

import java.util.List;

public class Task2Runner {
    static void main() {
        List<Integer> numbers = List.of(3, 7, 3, -1, 2, 3, 7, 2, 15, 15);

        System.out.println(ArrayUtils.countUniqueNumbers(numbers));
        System.out.println(ArrayUtils.getNotRepeatedNumbersCount(numbers));
    }
}
