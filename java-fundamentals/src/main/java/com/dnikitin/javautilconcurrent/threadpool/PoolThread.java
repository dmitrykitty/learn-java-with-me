package com.dnikitin.javautilconcurrent.threadpool;

import java.util.Queue;

public class PoolThread extends Thread {
    // Shared queue of tasks. All worker threads reference this same object.
    private final Queue<Runnable> tasks;

    public PoolThread(Queue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        // 1. THE EVENT LOOP
        // This thread runs forever (until the program stops or it's interrupted).
        // It's always looking for new work to do.
        while (true) {
            Runnable task = null;

            // 2. CRITICAL SECTION (Acquiring the Lock)
            // We must synchronize on 'tasks' because multiple threads (workers and the submitter)
            // access it simultaneously.
            synchronized (tasks) {

                // 3. THE GUARDED BLOCK (Waiting for Work)
                // If there are no tasks, we must wait.
                // We use a 'while' loop instead of 'if' to protect against "Spurious Wakeups"
                // (where the OS wakes up a thread without a notify signal).
                while (tasks.isEmpty()) {
                    try {
                        // 4. RELEASING THE LOCK & SLEEPING
                        // wait() does two things atomically:
                        // a) Releases the monitor (lock) on 'tasks' so others can modify it.
                        // b) Puts this thread into the Wait Set (sleep state).
                        tasks.wait();
                    } catch (InterruptedException e) {
                        // In a real executor, we would handle shutdown here.
                        throw new RuntimeException(e);
                    }
                }

                // 5. DEQUEUEING THE TASK
                // If we are here, it means we have the lock AND the queue is not empty.
                task = tasks.remove();
            }
            // 6. RELEASING THE LOCK (End of Synchronized Block)
            // The monitor is released here.

            // 7. EXECUTING THE TASK (Concurrency happens here!)
            // CRITICAL: We run the task OUTSIDE the synchronized block.
            // If we ran it inside, only one thread could work at a time (effectively single-threaded).
            // By running it here, other threads can access the queue while this one is busy working.
            try {
                task.run();
            } catch (RuntimeException e) {
                // Catching exception to prevent the worker thread from dying due to a bad task.
                System.err.println("Thread pool task failed: " + e.getMessage());
            }
        }
    }

    /**
     * NOTE: Usually, this method belongs to the ThreadPool manager class, not the Worker thread.
     * It adds a new task to the shared queue and wakes up a waiting worker.
     */
    public void execute(Runnable task) {
        synchronized (tasks) {
            // 1. Add the task to the shared queue
            tasks.add(task);

            // 2. Wake up ONE waiting worker
            // We use notify() because one task can be handled by only one thread.
            // Calling notify() moves one thread from the Wait Set to the Entry Set.
            tasks.notify();
        }
    }
}
