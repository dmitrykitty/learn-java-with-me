package collections.treemap;

import collections.equalsandhashcode.lesson1.Person;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapExample {
    static void main() {
        Person p1 = new Person(15, "Jan", 20);
        Person p2 = new Person(2, "Piotr", 36);
        Person p3 = new Person(9, "Krzysztof", 29);
        Person p4 = new Person(4, "Anna", 13);

        Map<Integer, Person> personMap = new TreeMap<>();
        personMap.putIfAbsent(p1.getId(), p1);
        personMap.putIfAbsent(p2.getId(), p2);
        personMap.putIfAbsent(p3.getId(), p3);
        personMap.putIfAbsent(p4.getId(), p4);

        for(Map.Entry<Integer, Person> entry : personMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }
}
