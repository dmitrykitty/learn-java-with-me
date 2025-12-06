package com.dnikitin.multithreading.consumerproducers;

import java.util.Queue;

public class ConsumerThread extends Thread {
    private final Queue<Integer> list;

    public ConsumerThread(Queue<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 1. CRITICAL SECTION START
                // Acquire lock to safely access the shared queue.
                synchronized (list) {

                    // 2. CHECKING CONDITION
                    // Check if the list is empty. If so, we must wait for data.
                    // Loop ensures we re-check the condition after waking up.
                    while (list.isEmpty()) {
                        System.out.println(getName() + " : list is empty");

                        // 3. WAIT FOR PRODUCER
                        // Releases lock and waits until Producer calls 'notifyAll()'.
                        list.wait();
                    }

                    // 4. CONSUMING DATA
                    Integer removedValue = list.remove();
                    System.out.println(getName() + " : removed value " + removedValue);

                    // 5. NOTIFYING PRODUCER
                    // If the Producer was sleeping because the list was full,
                    // this signal tells him there is now space available.
                    list.notifyAll();
                }// 6. MONITOR RELEASED AUTOMATICALLY HERE

                // 7. SIMULATING CONSUMPTION TIME
                // Simulating random processing time (0-10ms as per task).
                // Using Thread.sleep() implies "I am busy working", which is different
                // from list.wait() which implies "I cannot proceed, I need resources".
                int delay = RandomUtil.getRandomDelay(50, 200);
                System.out.println(getName() + " : working for " + delay + " mls");
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

