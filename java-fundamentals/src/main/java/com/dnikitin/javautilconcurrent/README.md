# Java Concurrency (`java.util.concurrent`)

## PART I: Comprehensive Theoretical Introduction
The `java.util.concurrent` package (introduced in Java 5) provides a high-level toolkit for building reliable, scalable concurrent programs. It abstracts the hardest parts of thread management, coordination, and data sharing, so you can focus on the problem instead of low-level plumbing like manual `wait()`/`notify()` and coarse `synchronized` blocks.

### 1. The Executor Framework
Replaces ad hoc `new Thread(...).start()` with reusable thread pools and clear task lifecycle management.
- `ExecutorService`: Submits tasks (`submit`, `invokeAll`), manages shutdown, and returns `Future`s.
- `Executors`: Factory for common pool types: `newFixedThreadPool`, `newCachedThreadPool`, `newSingleThreadExecutor`.
- `ScheduledExecutorService`: Run tasks after delays or on a schedule.
- `Future` and `CompletableFuture`: Represent async results; `CompletableFuture` supports composition (`thenApply`, `allOf`) and non-blocking pipelines.

### 2. Concurrent Collections
Highly optimized, thread-safe collections that avoid global locking.
- `ConcurrentHashMap`: Fine-grained locking/striped bins for scalable concurrent updates and reads.
- `CopyOnWriteArrayList` / `CopyOnWriteArraySet`: Snapshot semantics great for read-mostly workloads.
- `BlockingQueue` family: `ArrayBlockingQueue`, `LinkedBlockingQueue`, `PriorityBlockingQueue`, `DelayQueue` — ideal for producer-consumer pipelines.

### 3. Synchronizers
Coordination primitives that model higher-level concurrent workflows.
- `CountDownLatch`: One-shot gate: wait until N events completed.
- `CyclicBarrier`: Reusable barrier: all parties wait, then proceed together.
- `Semaphore`: Limit concurrency to a fixed number of permits (e.g., DB connections).
- `Phaser`: Flexible phased coordination across dynamic participants.

### 4. Explicit Locks (`java.util.concurrent.locks`)
More control than `synchronized` with features like try-lock and fairness.
- `ReentrantLock`: Mutual exclusion with `lockInterruptibly`, `tryLock`, and fairness.
- `ReadWriteLock`: Many readers or one writer; improves throughput for read-heavy data.
- `Condition`: Precise waiting/notification groups instead of a single object monitor queue.

### 5. Atomics and Adders (`java.util.concurrent.atomic`)
Lock-free primitives built on CAS for single-variable concurrency.
- `AtomicInteger`, `AtomicLong`, `AtomicReference`, `AtomicBoolean`.
- `LongAdder`/`LongAccumulator`: High-contention counters with striped cells for better scalability than atomics.

### 6. Fork/Join and Parallelism
- `ForkJoinPool` and `RecursiveTask/Action`: Work-stealing for fine-grained parallel tasks (e.g., divide-and-conquer algorithms).
- Streams parallelism uses a common `ForkJoinPool` (be cautious with blocking operations).

---

## PART II: Package Content & Solutions
This package accompanies the lower-level multithreading examples by showcasing modern, higher-level concurrency tools. Typical problems demonstrated in this module include:

1. Replacing manual producer-consumer with `BlockingQueue` to remove fragile `wait/notify` code and spurious wake-up guards.
2. Offloading CPU-bound and IO-bound tasks to appropriate `ExecutorService` pools; controlling shutdown and back-pressure.
3. Coordinating task phases with `CountDownLatch` and `CyclicBarrier` to ensure correct startup sequences and batch processing.
4. Using `ConcurrentHashMap` to safely share caches between threads without locking the entire structure.
5. Composing asynchronous workflows with `CompletableFuture` for non-blocking pipelines, timeouts, and exception handling.

Note: Compare these solutions with the raw monitor-based ones in the `multithreading` package to see how `java.util.concurrent` reduces boilerplate and pitfalls while improving scalability.