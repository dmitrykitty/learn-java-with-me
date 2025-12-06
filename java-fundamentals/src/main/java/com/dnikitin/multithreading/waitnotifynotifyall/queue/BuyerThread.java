package com.dnikitin.multithreading.waitnotifynotifyall.queue;

import java.util.Queue;

public class BuyerThread implements Runnable {
    private final Queue<CashBox> cashboxes;

    public BuyerThread(Queue<CashBox> cashboxes) {
        this.cashboxes = cashboxes;
    }

    @Override
    public void run() {
        try {
            // 1. ACQUIRING THE MONITOR (LOCK)
            // We enter a synchronized block on the 'cashboxes' object.
            // Only ONE thread can execute code inside this block at a time.
            // Other threads must wait here in the "Entry Set" (BLOCKED state).
            synchronized (cashboxes) {
                while (true) {
                    // 2. CHECKING CONDITION
                    // We check if there are available cashboxes.
                    // This check is now thread-safe because we hold the monitor.
                    if (!cashboxes.isEmpty()) {
                        // 3. CRITICAL SECTION ACTION
                        // We remove the element. The NoSuchElementException is prevented here
                        // because no other thread can remove an item between isEmpty() and remove().
                        CashBox cashBox = cashboxes.remove();
                        System.out.println(Thread.currentThread().getName() + ": " + cashBox);


                        // 4. SIMULATING WORK (Using wait with timeout)
                        // 'cashboxes.wait(5L)' does two things:
                        // a) It releases the lock (monitor) on 'cashboxes'.
                        // b) It pauses this thread for 5 milliseconds.
                        //
                        // Note: If we used 'Thread.sleep(5L)', the lock would NOT be released,
                        // blocking all other threads even while this one is sleeping.
                        cashboxes.wait(1000L); //wait used only inside synchronized method

                        System.out.println(Thread.currentThread().getName() + ": " + cashBox + " is free");
                        // 5. RETURNING RESOURCE
                        // We add the cashbox back to the queue.
                        cashboxes.add(cashBox);
                        // 6. NOTIFICATION
                        // notify() wakes up a single random thread that is currently waiting
                        // on this object's monitor (in the Wait Set).
                        // Ideally, we should use 'notifyAll()' to wake up all threads to avoid
                        // a situation where we wake up the wrong thread.
                        cashboxes.notifyAll(); //notify only one thread
                        //notifyAll notify all Threads
                        break;
                    } else {
                        System.out.println(Thread.currentThread().getName() + ": no free  cashboxes. Waiting...");
                        // 7. WAITING FOR RESOURCES
                        // The queue is empty. We cannot proceed.
                        // wait() releases the lock (monitor) and puts the thread into the "Wait Set".
                        // This thread will sleep here until another thread calls notify() or notifyAll().
                        cashboxes.wait(); //wait until another thread do now call methods notify or notifyAll
                    }
                }
                // 8. RELEASING THE LOCK
                // The thread automatically releases the monitor when exiting the synchronized block.
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
