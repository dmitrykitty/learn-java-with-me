package collections.map.practise;

import collections.equalsandhashcode.lesson1.Person;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapPractise {
    static void main() {

        Person p1 = new Person(1, "Jan", 20);
        Person p2 = new Person(2, "Piotr", 36);

        Map<Integer, Person> personMap = new HashMap<>();
        personMap.put(p1.getId(), p1);
        personMap.put(p2.getId(), p2);

        System.out.println(personMap.keySet());
        System.out.println(personMap.values());
        System.out.println(personMap.entrySet());

        //Set<Integer> keys = personMap.keySet();
        var keys = personMap.keySet();
        for (var key : keys) {
            System.out.println(key);
        }

        //Collection<Person> values = personMap.values();
        var values = personMap.values();
        for (var value : values) {
            System.out.println(value);
        }

        //Set<Map.Entry<Integer, Person>> entries = personMap.entrySet();
        var entries = personMap.entrySet();
        for (var entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println(personMap.containsKey(1));
        System.out.println(personMap.containsValue(p1));
    }
}
