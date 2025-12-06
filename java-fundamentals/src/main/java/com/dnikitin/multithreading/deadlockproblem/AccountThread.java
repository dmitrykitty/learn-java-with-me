package com.dnikitin.multithreading.deadlockproblem;

public class AccountThread extends Thread{

    private final Account accountFrom;
    private final Account accountTo;

    public AccountThread(Account accountFrom, Account accountTo) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
    }

    @Override
    public void run(){
        // 1. ACQUIRE FIRST LOCK (The "Hold" part)
        // Thread-1 locks Account A.
        // Thread-2 locks Account B (because of the reverse order in main).
        synchronized (accountFrom){
            System.out.println(getName() + " :accountFrom");
            try {
                // 2. ARTIFICIAL DELAY
                // We sleep holding the first lock. This forces a "Context Switch".
                // While Thread-1 sleeps holding Account A, Thread-2 wakes up and grabs Account B.
                // This maximizes the chance of a Deadlock.
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 3. ATTEMPT TO ACQUIRE SECOND LOCK (The "Wait" part)
            // Thread-1 tries to lock Account B -> BUT Account B is held by Thread-2.
            // Thread-1 goes into BLOCKED state waiting for Thread-2 to release it.
            //
            // Thread-2 tries to lock Account A -> BUT Account A is held by Thread-1.
            // Thread-2 goes into BLOCKED state waiting for Thread-1 to release it.
            //
            // RESULT: CIRCULAR DEPENDENCY. Neither can proceed, neither can release the first lock.
            synchronized (accountTo){
                // This line will NEVER be reached in case of a Deadlock
                System.out.println(getName() + " :accountTo");
                for (int i = 0; i < 200000; i++) {
                    if(accountFrom.takeMoney(10)){
                        accountTo.addMoney(10);
                    }
                }
            }
        }
    }
}
