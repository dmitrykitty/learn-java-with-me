package com.dnikitin.multithreading.sychronizedcollections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListDemo {
    static void main() {
        List<Integer> integers = Collections.synchronizedList(new ArrayList<>()); //w taki sposob nie potrzebuje synchronized
        //wszystkie metody wewnątrz synchronizedList przeciązone i mają w sobie block synchronized
        //to devide responsibility - inside used addition attribute mutex for synchronization on it, gdzie mutex = this

        ListThread listThread1 = new ListThread(integers);
        ListThread listThread2 = new ListThread(integers);
        ListThread listThread3 = new ListThread(integers);
        ListThread listThread4 = new ListThread(integers);
        ListThread listThread5 = new ListThread(integers);
        ListThread listThread6 = new ListThread(integers);
        ListThread listThread7 = new ListThread(integers);
        ListThread listThread8 = new ListThread(integers);

        listThread1.start();
        listThread2.start();
        listThread3.start();
        listThread4.start();
        listThread5.start();
        listThread6.start();
        listThread7.start();
        listThread8.start();

        try {
            listThread1.join();
            listThread2.join();
            listThread3.join();
            listThread4.join();
            listThread5.join();
            listThread6.join();
            listThread7.join();
            listThread8.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(integers.size());
    }
}
