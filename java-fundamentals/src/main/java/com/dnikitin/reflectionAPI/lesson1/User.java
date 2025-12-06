package com.dnikitin.reflectionAPI.lesson1;

import com.dnikitin.reflectionAPI.annotations.MinAge;

import java.io.Serializable;

public class User extends Person implements Serializable, Comparable<User> {

    private String name;
    @MinAge
    //@MinAge(age=21) - gdyby nie było default age in annotation
    //@Minage(20) - gdyby imię metod w annotacji było value(zarezerwowane)
    private int age;

    public User(long id, String name, int age) {
        this.name = name;
        super(id);
        this.age = age;
    }

    @Override
    public int compareTo(User o) {
        return Long.compare(this.getId(), o.getId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
