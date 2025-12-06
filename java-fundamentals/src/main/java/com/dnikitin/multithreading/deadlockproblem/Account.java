package com.dnikitin.multithreading.deadlockproblem;

public class Account {

    private static int generator = 1;
    private final  int id;
    private int money;

    public Account(int money){
        this.money = money;
        id = generator++;
    }

    public void addMoney(int money){
        this.money += money;
    }

    public boolean takeMoney(int money){
        if(this.money >= money){
            this.money -= money;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", money=" + money +
                '}';
    }
}
