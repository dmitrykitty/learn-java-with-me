package com.dnikitin.multithreading.volatil;

/**
 * Demonstrates the <b>Memory Visibility</b> problem in a multithreaded environment.
 * <p>
 * <strong>Why is {@code volatile} required here?</strong>
 * <p>
 * Without the {@code volatile} keyword, the Java Memory Model (JMM)(heap) does not guarantee
 * that the change made to {@code flag} by {@code thread2} will be immediately visible
 * to {@code thread1}. This results in an infinite loop (livelock) for two main reasons:
 * <ul>
 * <li><strong>CPU Caching:</strong> {@code thread1} may run on a different CPU core than
 * {@code thread2}. It can cache the value of {@code flag} (as {@code false}) in its
 * local L1/L2 cache or registers and never refresh it from the Main Memory.
 * It simply does not see the update.</li>
 *
 * <li><strong>JIT Compiler Optimizations (Loop Hoisting):</strong> The Just-In-Time
 * compiler might detect that {@code flag} is not modified inside the {@code while} loop.
 * To optimize performance, it may "hoist" the check out of the loop, effectively changing
 * the code to:
 * <pre>{@code
 * if (!flag) {
 * while (true) { ... }
 * }
 * }</pre>
 * </li>
 * </ul>
 * <p>
 * <strong>The Solution:</strong>
 * Adding {@code volatile} forces the JVM to establish a <i>happens-before</i> relationship.
 * Volatile block the ability of any optimization and saving variable to the core cash.
 * It ensures that:
 * <ol>
 * <li>Reads and writes to {@code flag} bypass the local CPU cache and go directly
 * to/from <strong>Main Memory (RAM)</strong>.</li>
 * <li>Instruction reordering that could affect the visibility of this variable is prevented.</li>
 * </ol>
 */
public class VolatileDemo {
    // The volatile keyword guarantees visibility across threads
    private static volatile boolean flag = false;
    static void main() throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            while (!flag) {
                System.out.println("Flag is false");
            }
        });
        thread1.start();
        Thread.sleep(5);

        Thread thread2 = new Thread(() -> {
            flag = true;
            System.out.println("Flag is true");
        });
        thread2.start();
    }
}
