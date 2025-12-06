package com.dnikitin.javautilconcurrent.lock;

public class DeadLockSolving {
    static void main() {
        Account account1 = new Account(200_000);
        Account account2 = new Account(200_000);

        AccountThread thread1 = new AccountThread(account1, account2);
        AccountThread thread2 = new AccountThread(account2, account1);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(account1);
        System.out.println(account2);
    }
}
