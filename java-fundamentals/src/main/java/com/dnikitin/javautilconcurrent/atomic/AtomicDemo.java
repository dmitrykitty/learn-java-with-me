package com.dnikitin.javautilconcurrent.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;


public class AtomicDemo {
    static void main() {
        // 1. ATOMICITY WITHOUT LOCKS
        // AtomicInteger ensures that operations like increment are thread-safe
        // WITHOUT using the 'synchronized' keyword (which is heavy/slow).
        AtomicInteger atomicInteger = new AtomicInteger(10);

        // 2. INCREMENT OPERATION
        // Under the hood, this doesn't just do "value++".
        // It uses a loop with the Compare-And-Swap (CAS) algorithm.
        int i = atomicInteger.incrementAndGet();
        System.out.println(i); // 11

        /*
         * --- HOW IT WORKS INSIDE (CAS - Compare-And-Swap) ---
         * * Instead of locking the whole object (Pessimistic Locking), Atomics use
         * Optimistic Locking via a hardware CPU instruction called CAS.
         * * The CAS algorithm works like this (simplified loop):
         * * while(true) {
         * int currentValue = getValueFromMemory(); // e.g., 10
         * int newValue = currentValue + 1;         // e.g., 11
         * * // CAS Instruction (Native/CPU level):
         * // "Check if the value at memory address X is still 'currentValue' (10).
         * // If YES -> Change it to 'newValue' (11) and return true.
         * // If NO (another thread changed it to 12) -> Do nothing, return false."
         * * if (compareAndSwap(currentValue, newValue)) {
         * return newValue; // Success!
         * }
         * // If failed, loop again and try with the new current value.
         * }
         */

        // 3. THE MAGIC CLASS: sun.misc.Unsafe
        // Atomic classes delegate all the hard work to this class.
        // Unsafe allows Java to perform low-level operations usually reserved for C++:
        // - Direct memory access (allocating/freeing off-heap memory).
        // - Positioning objects in memory.
        // - Hardware-level synchronization (CAS).

        try {
            // This line typically throws a SecurityException!
            // Java forbids accessing Unsafe directly in user code to prevent
            // you from crashing the JVM with manual memory management.
            Unsafe unsafe = Unsafe.getUnsafe();
        } catch (SecurityException e) {
            System.out.println("Cannot access Unsafe directly: " + e.getMessage());
        }

        // HACK: How to get Unsafe via Reflection (Just for education, don't do this in production!)
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafeInstance = (Unsafe) field.get(null);
            System.out.println("Got Unsafe via Reflection!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
