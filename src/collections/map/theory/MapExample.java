package collections.map.theory;

import collections.equalsandhashcode.lesson1.Person;

import java.util.HashMap;
import java.util.Map;

public class MapExample {
    static void main() {
        Person p1 = new Person(1, "Jan", 20);
        Person p2 = new Person(2, "Piotr", 36);

        Map<Integer, Person> personMap = new HashMap<>();
        personMap.put(p1.getId(), p1);
        personMap.put(p2.getId(), p2);

        System.out.println(personMap.get(1));
    }
}
