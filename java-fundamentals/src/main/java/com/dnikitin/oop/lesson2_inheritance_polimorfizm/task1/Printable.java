package com.dnikitin.oop.lesson2_inheritance_polimorfizm.task1;

import java.util.Random;

//interfejs moze przechowywac tylko metody bez realizacji i stałe zmienne. Od Java 8 moze byc implementacja
public interface Printable {
    String SOME_STRING = "SOME_STRING";
    Random RANDOM = new Random();

    default void printWithRandom() {
        //default methods muszą miec realizacje

        System.out.println(generateRandom());
        printState();
    }

    //wewnętrzny metod dla pracy z interfejsem - od JAva 9
    private int generateRandom(){
        return RANDOM.nextInt();
    }

    void printState();
}
