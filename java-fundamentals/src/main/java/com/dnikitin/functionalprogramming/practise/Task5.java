package com.dnikitin.functionalprogramming.practise;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Task5 {
    static void main() {
        List<Person> persons = List.of(
                new Person("Ivan", "Ivanov", 20),
                new Person("Petr", "Petrov", 25),
                new Person("Sveta", "Svetikova", 33),
                new Person("Kate", "Ivanova", 25),
                new Person("Slava", "Slavikov", 18),
                new Person("Arni", "Kutuzov12324", 56)
        );

        persons.stream()
                .filter(person -> person.firstName().length() + person.lastName().length() < 15)
                .max(Comparator.comparing(Person::age))
                .ifPresent(p -> System.out.println(p.firstName() + " " + p.lastName()));

        Map<Integer, List<Person>> map = persons.stream()
                .collect(Collectors.groupingBy(Person::age)); //-> map (key(age), value(List<Person> when person.age = key))


    }
}
