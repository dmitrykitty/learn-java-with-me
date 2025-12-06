package com.dnikitin.multithreading.atomicoperations;

import com.sun.security.jgss.GSSUtil;

public class CounterDemo {
    /**
     * count++ - non atomic operation
     * 1. reading
     * 2. increment
     * 3. saving
     *  tread1 ------ 8 -> 8 + 1 -> count -> 9
     *  jednoczesnie
     *  tread2 ------ 8 -> 8 + 1 -> count -> 9
     *
     *  jako wynik dwie operacji, ale jeden raz  increment, bo wykonywały się jednoczesnie
     */
    static void main() {
        Counter counter = new Counter();
        Thread thread1 = new Thread(new CounterThread(counter));
        Thread thread2 = new Thread(new CounterThread(counter));
        Thread thread3 = new Thread(new CounterThread(counter));
        Thread thread4 = new Thread(new CounterThread(counter));
        Thread thread5 = new Thread(new CounterThread(counter));
        Thread thread6 = new Thread(new CounterThread(counter));
        Thread thread7 = new Thread(new CounterThread(counter));
        Thread thread8 = new Thread(new CounterThread(counter));
        Thread thread9 = new Thread(new CounterThread(counter));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
            thread7.join();
            thread8.join();
            thread9.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(counter.getCount());
    }
}
