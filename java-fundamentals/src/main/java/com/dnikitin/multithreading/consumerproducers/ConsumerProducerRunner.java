package com.dnikitin.multithreading.consumerproducers;

import java.util.LinkedList;
import java.util.Queue;

public class ConsumerProducerRunner {
    static void main() {
        Queue<Integer> list = new  LinkedList<>();

        Thread consumerThread = new ConsumerThread(list);
        consumerThread.setName("Consumer");
        Thread producerThreadA = new ProducerThread(list);
        producerThreadA.setName("Producer A");
        Thread producerThreadB = new ProducerThread(list);
        producerThreadB.setName("Producer B");

        consumerThread.start();
        producerThreadA.start();
        producerThreadB.start();


    }
}
