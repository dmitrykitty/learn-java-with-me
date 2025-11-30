package javautilconcurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private static int generator = 1;
    //adding LockObject
    private final Lock lock = new ReentrantLock();
    private final int id;
    private int money;

    public Account(int money) {
        this.money = money;
        id = generator++;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public boolean takeMoney(int money) {
        if (this.money >= money) {
            this.money -= money;
            return true;
        }
        return false;
    }

    public Lock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", money=" + money +
                '}';
    }
}
