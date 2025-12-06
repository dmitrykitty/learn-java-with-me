package com.dnikitin.oop.lesson1.task1_inheritance;

public class Computer {
    private Ram ram;
    private Ssd ssd;

    public Computer(Ram ram, Ssd ssd){
        this.ram = ram;
        this.ssd = ssd;
    }

    public Computer(){
        this(new Ram(16), new Ssd(128)); //wywołanie juz istniejacego konstruktora
    }

    public Ram getRam() {
        return ram;
    }
    public Ssd getSsd() {
        return ssd;
    }

    public void printState(){
        System.out.println("Ram: " + ram.getSize() + " ssd: " + ssd.getSize());
    }
}
