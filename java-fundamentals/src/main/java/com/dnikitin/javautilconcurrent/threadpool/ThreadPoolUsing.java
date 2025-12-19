package com.dnikitin.javautilconcurrent.threadpool;

import java.util.concurrent.*;

public class ThreadPoolUsing {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1. CREATING A THREAD POOL
        // 'newFixedThreadPool(5)' creates a pool with exactly 5 worker threads.
        // If all threads are busy, new tasks will queue up in an unbounded LinkedBlockingQueue.
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        // 2. EXECUTE (Fire and Forget)
        // Takes a Runnable. Returns void. We cannot know if it succeeded or failed (exception is logged but not thrown to main).
        threadPool.execute(() -> System.out.println("I love you"));

        // 3. SUBMIT (Task with Result)
        // Takes a Runnable or Callable<T>. Returns a Future<T>.
        // Callable is like Runnable but:
        // a) It returns a value.
        // b) It can throw a checked Exception.
        Future<Integer> submit = threadPool.submit(() -> {
            Thread.sleep(2000);
            System.out.println("It's Callable");
            return 1;
        });

        // 4. COMPLETABLE FUTURE & ASYNC POOL
        // You asked about "default thread pool".
        // CompletableFuture.supplyAsync(() -> ...) runs tasks asynchronously.
        // If you DO NOT specify an Executor, it uses the global 'ForkJoinPool.commonPool()'.
        // This is a special pool available to the entire JVM, optimized for CPU-intensive tasks.
        CompletableFuture.supplyAsync(() -> "Hello from ForkJoinPool")
                .thenAccept(System.out::println);

        // 5. FUTURE.GET() (Blocking Operation)
        // This line BLOCKS the 'main' thread until the task is finished.
        // If the task throws an exception, .get() will throw ExecutionException.
        // Ideally, use timeouts: submit.get(1, TimeUnit.SECONDS) to avoid hanging forever.
        System.out.println("Future result " + submit.get());

        // 6. SHUTDOWN SEQUENCE
        // shutdown(): "I stop accepting NEW tasks, but I will finish existing ones (running & queued)."
        threadPool.shutdown();

        // shutdownNow(): "I stop accepting tasks AND I try to interrupt running ones. Returns list of not-started tasks."
        // threadPool.shutdownNow();

        // 7. AWAIT TERMINATION
        // This blocks 'main' thread, waiting for the pool to actually close (all tasks done).
        // It returns true if pool terminated, false if timeout elapsed.
        try {
            if (!threadPool.awaitTermination(1, TimeUnit.HOURS)) {
                System.out.println("Pool did not terminate in time!");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main end");

        // 8. SCHEDULED THREAD POOL
        // Useful for recurring tasks or delayed execution.
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);

        // Run once after 3 seconds delay
        service.schedule(() -> System.out.println("Schedule Task"), 3L, TimeUnit.SECONDS);

        // Run repeatedly:
        // - Initial delay: 3s
        // - Period: 2s (Time between START of execution 1 and START of execution 2)
        // Note: If task takes longer than 2s, the next one starts immediately after previous finishes (no overlap).
        // WARNING: If task throws an Exception, the scheduler STOPS strictly. Future executions are suppressed.
        service.scheduleAtFixedRate(() -> System.out.println("I love Kris"), 3L, 2L, TimeUnit.SECONDS);

        // Remember to close scheduled service too, otherwise app keeps running!
        // service.shutdown();
    }
}
