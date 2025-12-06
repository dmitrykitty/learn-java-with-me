package com.dnikitin.oop.lesson2_abstract_classes.task1;

public class Main {
    public static void main(String[] args) {
        Base b1 = new Derived();
        Base b2 = new Derived2();

        printBases(b1, b2);

    }

    public static void printBases(Base... bases) {
        for (Base base : bases) {
            base.print();
        }
    }
}
