package com.dnikitin.multithreading.threadrunnable;

public class SimpleRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println("Hello from runnable: " + Thread.currentThread().getName());
        //dlatego, ze nie extendim zaden klass - nie ma metod getName i tak dalej
        //dostep przez statyczne metody
    }
}
