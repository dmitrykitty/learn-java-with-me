package javautilconcurrent.lock;


import multithreading.consumerproducers.RandomUtil;

public class AccountThread extends Thread {

    private final Account accountFrom;
    private final Account accountTo;

    public AccountThread(Account accountFrom, Account accountTo) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }

    @Override
    public void run() {
        // Initial setup logs (simulating some startup delay)
        System.out.println(getName() + " :accountFrom");
        System.out.println(getName() + " :accountTo");
        for (int i = 0; i < 200000; i++) {
            try {
                // 1. CRITICAL SECTION SAFETY
                // We wrap the critical section in a try-finally block.
                // This ensures that even if an exception (like NullPointerException) occurs
                // during the money transfer, the locks will ALWAYS be released.
                // Without this, the system could hang forever (Deadlock).
                lockAccounts();
                if (accountFrom.takeMoney(10)) {
                    accountTo.addMoney(10);
                }
            } finally {
                // 2. CLEANUP
                // Always release locks, regardless of success or failure.
                unlockAccounts();
            }
        }
    }

    private void unlockAccounts() {
        accountTo.getLock().unlock();
        accountFrom.getLock().unlock();
    }

    /**
     * Tries to acquire locks for both accounts safely.
     * Prevents Deadlock by not holding one lock while waiting forever for the other.
     * Prevents Livelock by using a random sleep duration.
     */
    private void lockAccounts(){
        while(true){
            // 3. NON-BLOCKING ATTEMPT
            // tryLock() returns immediately with 'true' if lock was acquired,
            // or 'false' if it was already taken by another thread.
            boolean tryLockFrom = accountFrom.getLock().tryLock();
            boolean tryLockTo = accountTo.getLock().tryLock();

            // 4. SUCCESS SCENARIO
            // If we managed to grab BOTH locks, we are safe to proceed.
            if(tryLockFrom && tryLockTo){
                break;
            }
            // 5. FAILURE SCENARIO (BACK-OFF STRATEGY)
            // If we only got one lock (or none), we must release whatever we hold.
            // This breaks the "Hold and Wait" condition required for a Deadlock.
            if(tryLockFrom){
                accountFrom.getLock().unlock();
            }
            if(tryLockTo){
                accountTo.getLock().unlock();
            }
            // 6. SOLVING LIVELOCK
            // If two threads constantly try to lock A & B at the exact same time,
            // they might enter a rhythm where they both fail, release, and retry eternally.
            // Adding a RANDOM sleep desynchronizes the threads, allowing one to win.
            try {
                Thread.sleep(RandomUtil.getRandomDelay(1, 10)); // np. 1-10ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
