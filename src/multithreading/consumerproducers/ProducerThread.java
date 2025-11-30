package multithreading.consumerproducers;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerThread extends Thread {
    private final int MAX_CAPACITY = 10;
    private final Queue<Integer> list;

    public ProducerThread(Queue<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 1. CRITICAL SECTION START
                // We acquire the monitor (lock) on the shared 'list' object.
                // Only one thread (Producer or Consumer) can be inside this block at a time.
                synchronized (list) {

                    // 2. CHECKING CONDITION (THE "GUARDED BLOCK")
                    // We use a 'while' loop instead of 'if' to protect against:
                    // a) Spurious wakeups (OS waking up thread without reason).
                    // b) "Thief" threads (another thread stealing space before we get to it).
                    while (list.size() > MAX_CAPACITY) {
                        System.out.println(getName() + " : to many nums in the list");

                        // 3. RELEASING THE LOCK
                        // 'wait()' releases the monitor and puts this thread into the Wait Set.
                        // It stays here sleeping until Consumer calls 'notifyAll()'.
                        list.wait();
                    }

                    // 4. MODIFYING SHARED STATE
                    // We are guaranteed to have the lock and space in the list here.
                    int random = RandomUtil.getRandomNum(0, 10);
                    list.add(random);
                    System.out.println(getName() + " : add number " + random + ", current size " + list.size());

                    // 5. NOTIFYING OTHERS (CRITICAL!)
                    // We must notify the Consumer that there is new data to consume.
                    // If the Consumer is sleeping because the list was empty, this wakes it up.
                    list.notifyAll();

                } // 6. CRITICAL SECTION END (Monitor is released here)

                // 7. SIMULATING WORK (OUTSIDE SYNCHRONIZED)
                // We use Thread.sleep() here to simulate the time it takes to "produce" the item.
                // IMPORTANT: We do this OUTSIDE the synchronized block so we don't hold the lock
                // while sleeping. This allows the Consumer to empty the list while we rest.
                int delay = RandomUtil.getRandomDelay(50, 200);
                System.out.println(getName() + " : waiting for " + delay + " mls");
                Thread.sleep(delay);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


