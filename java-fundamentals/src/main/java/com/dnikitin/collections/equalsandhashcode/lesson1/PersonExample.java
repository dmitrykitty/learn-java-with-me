package com.dnikitin.collections.equalsandhashcode.lesson1;

public class PersonExample {
    public static void main(String[] args) {
        Person p1 = new Person(1, "Jan", 20);
        Person p2 = new Person(2, "Piotr", 36);

        Person p3 = new Person(p1.getId(), p1.getFirstName(), p1.getAge());

        System.out.println(p1.hashCode());
        System.out.println(p1.equals(p2));
        System.out.println(p1.equals(p3));
    }
}
