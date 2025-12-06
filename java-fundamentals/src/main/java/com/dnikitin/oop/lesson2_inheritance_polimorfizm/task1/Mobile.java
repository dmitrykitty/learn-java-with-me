package com.dnikitin.oop.lesson2_inheritance_polimorfizm.task1;

public class Mobile extends Computer{

    public Mobile(Ram ram, Ssd ssd) {
        super(ram, ssd);
    }

    @Override
    public void load() {
        System.out.println("Loading...");
    }
}
