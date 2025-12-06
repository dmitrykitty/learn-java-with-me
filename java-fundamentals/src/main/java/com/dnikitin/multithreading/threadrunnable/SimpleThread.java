package com.dnikitin.multithreading.threadrunnable;

public class SimpleThread extends Thread{

    @Override
    //to samo co main tylko dla tego threadu
    public void run() {
        System.out.println("Hello " + getName());
    }
}
