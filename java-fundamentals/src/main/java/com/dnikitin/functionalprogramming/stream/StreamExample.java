package com.dnikitin.functionalprogramming.stream;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.IntStream;

public class StreamExample {
    static void main() {
        List<String> listOfStrings = List.of("111", "222", "333", "444", "66", "22");

        //before stream
        for (String string : listOfStrings) {
            string += string;
            Integer intValue = Integer.valueOf(string);
            if ((intValue & 1) == 0) {
                System.out.println(intValue);
            }
        }

        //with stream
        listOfStrings.stream()
                .map(s -> s + s)
                .map(Integer::valueOf)// s -> Integer.valueOf(s)
                .filter(i -> (i & 1) == 0)
                .sorted()
                .skip(1) //skip first element
                .limit(2) //return only 2 elements
                .forEach(System.out::println); //continue until terminated operation (return type NOT stream)

        IntSummaryStatistics stats = listOfStrings.stream()
                .map(s -> s + s)
                .map(Integer::valueOf)// s -> Integer.valueOf(s)
                .filter(i -> (i & 1) == 0)
                .sorted()
                .mapToInt(Integer::intValue)// value -> value.intValue() (casting mapy na basic type)
                //dostajemy dodatkowe metody max, min, average, count, sum
                //.mapToObj(Integer::valueof) -> mapuje na obiekt
                .summaryStatistics(); //zwraca obiekt IntSummaryStatistics w ktorym juz są te wszystkie metody agregujace


        System.out.println(stats);

        IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .peek(System.out::println)
                .filter(i -> i % 2 == 0)
                .forEach(System.out::println);

        IntStream.range(1, 10)
                .forEach(System.out::println);

        IntStream.iterate(1, i -> i + 3)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);


    }
}
