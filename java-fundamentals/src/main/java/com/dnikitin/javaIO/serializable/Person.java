package com.dnikitin.javaIO.serializable;

import java.io.Serializable;

public class Person implements Serializable {
    private int age;
    private transient String name; //to pole nie będzie searelizowac sie

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
