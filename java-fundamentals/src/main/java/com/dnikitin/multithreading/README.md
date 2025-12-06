# Multithreading & Concurrency in Java

## PART I: Comprehensive Theoretical Introduction

Multithreading is a core feature of Java that allows the concurrent execution of two or more parts of a program for maximum utilization of the CPU. However, managing shared state and communication between threads is one of the most complex tasks in software engineering.

### 1. The Concurrency Class Hierarchy
Understanding the relationship between Java's core concurrency classes is essential for writing clean code.

* **`java.lang.Runnable` (The Task)**:
    * An interface representing a "job" to be done. It has a single method: `run()`.
    * It decouples the *logic* of the task from the *mechanism* of execution.
* **`java.lang.Thread` (The Worker)**:
    * A class that maps to an actual Operating System thread.
    * It accepts a `Runnable` in its constructor and executes it via the `start()` method.
* **`java.util.concurrent.ExecutorService` (The Manager)** *(Modern Approach)*:
    * A higher-level abstraction that manages a pool of threads. instead of manually creating `new Thread()`, you submit `Runnable` tasks to an executor.

**Visualizing the Workflow:**
`Runnable` (What to do)  --->  `Thread` (Who does it)  --->  `CPU` (Where it runs)

### 2. The Java Memory Model (JMM)
To understand concurrency issues, you must understand how threads view memory.
* **Main Memory (Heap)**: The shared storage where all objects live. Accessible by all threads.
* **Thread Local Memory (Stack & CPU Cache)**: Each thread has its own stack (for local variables) and a high-speed CPU cache.
* **The Visibility Problem**: A thread often copies variables from Main Memory to its local Cache for performance. If Thread A modifies a variable in its cache but hasn't flushed it back to Main Memory yet, Thread B will continue to see the old "stale" value.

### 3. The Three Key Concepts of Correctness
For a multithreaded program to be safe, it must satisfy three properties:
1.  **Atomicity**: Operations must happen "all at once" or not at all. Example: `count++` is NOT atomic (Read -> Modify -> Write). If two threads do this simultaneously, one update is lost.
2.  **Visibility**: Changes made by one thread must be visible to others immediately.
3.  **Ordering**: The compiler and processor may reorder instructions to optimize performance, which can break logic in concurrent environments.

### 4. Synchronization Mechanisms
Java provides tools to enforce these properties:

* **Monitors & The `synchronized` Keyword**:
    * Every object in Java has an internal entity called a **Monitor** (or Intrinsic Lock).
    * When a thread enters a `synchronized` block/method, it **acquires** the monitor.
    * **Rule**: Only one thread can hold a specific monitor at a time. Others are blocked.
    * *Guarantees*: Atomicity and Visibility.

* **The `volatile` Keyword**:
    * A lightweight synchronization mechanism that targets **Visibility**.
    * It forces the CPU to read/write the variable directly to Main Memory, bypassing the cache.
    * *Warning*: It does **NOT** guarantee atomicity (it doesn't prevent race conditions on operations like `i++`).

* **Inter-Thread Communication (`wait` / `notify`)**:
    * Threads need to coordinate (e.g., "I finished work, now you go").
    * **`wait()`**: "I cannot proceed. I release the monitor and sleep until notified."
    * **`notify()` / `notifyAll()`**: "I changed the state. Wake up waiting threads."

* **Deadlock**:
    * A situation where two or more threads are blocked forever, waiting for each other to release resources.

---

## PART II: Package Content & Solutions

This package contains practical implementations demonstrating the theoretical concepts described above.

### 1. Thread Creation & Lifecycle (`threadrunnable`)
* **Concepts**: Creating execution units and the difference between inheritance and composition.
* **Implementation**:
    * **`SimpleThread`**: Extends `Thread`. Limited because Java allows single inheritance only.
    * **`SimpleRunnable`**: Implements `Runnable`. Preferred approach as it separates the task from the thread logic.
* **Lifecycle Demo**: `ThreadDemo` explores states like `NEW`, `RUNNABLE`, `TERMINATED` and uses `join()` to ensure the main thread waits for workers to complete.

### 2. The Race Condition Problem (`atomicoperations`)
* **Scenario**: Multiple threads trying to increment a shared `count` variable.
* **Problem**: Without synchronization, the final count is random because `count++` is not atomic.
* **Solution**: The `Counter` class uses `synchronized` methods and blocks to ensure that only one thread can increment the value at a time, protecting the critical section.

### 3. The Visibility Problem (`volatil`)
* **Scenario**: `VolatileDemo` creates a loop that runs until a boolean flag is set to `true` by another thread.
* **Observation**: Without `volatile`, the loop may run forever because the reader thread caches the `false` value. Adding `volatile` fixes this immediately by forcing memory reads.

### 4. Deadlock Simulation (`deadlockproblem`)
* **Scenario**: A banking system where `AccountThread` transfers money between two accounts.
* **The Trap**: Thread 1 locks Account A then B. Thread 2 locks Account B then A. If they run simultaneously, they lock the first resource and wait indefinitely for the second.
* **Takeaway**: Locks must always be acquired in a consistent, global order (e.g., ordered by ID) to prevent deadlocks.

### 5. Coordination with Wait/Notify (`waitnotifynotifyall`)
* **Scenario**: A shop queue simulation (`CashBoxRunner`).
* **Logic**: `BuyerThread` enters a `synchronized` block on the `cashboxes` queue. If no cashbox is free, it calls `wait()` (releasing the lock). When a cashbox is returned, `notifyAll()` is called to wake up waiting buyers.

### 6. The Producer-Consumer Pattern (`consumerproducers`)
* **Scenario**: Coordinating a fast `Producer` and a slow `Consumer` using a shared buffer (List) of limited size (10).
* **Key Implementation Details**:
    * **Guarded Blocks**: Uses `while (list.isEmpty())` instead of `if` to protect against spurious wakeups.
    * **Full Lifecycle**: The Producer waits if the list is full; the Consumer waits if the list is empty. Both notify each other upon completing their work.
    * **Simulation**: Uses `Thread.sleep()` outside the synchronized block to simulate work without holding the lock.

### 7. Thread-Safe Objects (`threadsafetyobject`)
* **`SafetyObjectDemo`**: Guidelines for creating thread-safe classes:
    * Use stateless objects or immutable variables (`final`).
    * Synchronize access to mutable state.
    * Don't leak references of mutable objects.

### 8. Synchronized Collections (`sychronizedcollections`)
* **`ListDemo`**: Demonstrates that standard `ArrayList` is not safe for concurrent use. It introduces `Collections.synchronizedList()` as a wrapper that synchronizes all methods (add, get, remove) to prevent data corruption.