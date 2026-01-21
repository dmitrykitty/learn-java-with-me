package com.dnikitin.multithreading.threadrunnable;

import java.util.ArrayList;
import java.util.List;

public class VirtualThreads {

    static void main() {

        Runnable r = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Index " + i);
            }
        };

        Thread vThread1 = Thread.ofVirtual().unstarted(r);
        vThread1.start();

        try {
            vThread1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<Thread> threadList = new ArrayList<>();
        int threadAmount = 100_000;
        for (int i = 0; i < threadAmount; i++) {
            int finalI = i;
            Thread vThread = Thread.ofVirtual().start(() -> {
                int result = 1;
                for (int j = 1; j <= 10; j++) {
                    result *= j;
                }
                System.out.println("Thread[" + finalI + "]: " + result);
            });
            threadList.add(vThread);
        }

        for (Thread t : threadList) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

