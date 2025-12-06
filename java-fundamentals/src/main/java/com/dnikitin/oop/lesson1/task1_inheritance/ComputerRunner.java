package com.dnikitin.oop.lesson1.task1_inheritance;

public class ComputerRunner {
    void main(){
        Computer c = new Computer(new Ram(1024), new Ssd(1024));
        c.printState();

        Laptop laptop = new Laptop(new Ram(1024), new Ssd(1024), 15);
        laptop.printState();
        laptop.open();

    }
}
