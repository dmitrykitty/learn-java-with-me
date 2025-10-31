package collections.sorting.theory;


import collections.equalsandhashcode.lesson1.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortExample {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 9, -4, 12, 3); //mutable list

        Collections.sort(list);
        System.out.println(list);

        List<Person> persons = Arrays.asList(
                new Person(5, "Jan", 20),
                new Person(1, "Piotr", 36),
                new Person(2, "Kamil", 25)
        );
        Collections.sort(persons);
        System.out.println(persons);

        persons.sort(new PersonComparator());
        System.out.println(persons);

        persons.sort(Comparator.comparing(Person::getFirstName).thenComparing(Person::getAge));
    }

    public static class PersonComparator implements java.util.Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getFirstName().compareTo(o2.getFirstName());
        }
    }


}
