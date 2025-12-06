package com.dnikitin.collections.equalsandhashcode.lesson1;

import java.util.Objects;

public class Person implements Comparable<Person>{

    private final int id;
    private String firstName;
    private int age;


    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.id, o.id);
//        if(this.id == o.id){
//            return 0;
//        } else if(this.id > o.id){
//            return 1;
//        } else {
//            return -1;
//        }
    }

    public Person(int id, String firstName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && age == person.age && Objects.equals(firstName, person.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }
}
