package com.dnikitin.oop.lesson1.task2;

public class Bankomat {
    private int count10;
    private int count20;
    private int count50;
    private long balance;

    public Bankomat(int count10, int count20, int count50) {
        this.count10 = count10;
        this.count20 = count20;
        this.count50 = count50;
        balance = count10 * 10L + count20 * 20L + count50 * 50L;
    }

    public void addMoney(long money){
        balance += money;
        while(money > 50) {
            money -= 50;
            count50++;
        }
        while(money > 20) {
            money -= 20;
            count20++;
        }
        while(money > 10) {
            money -= 10;
            count10++;
        }
    }

    public void addBanknote(int count10, int count20, int count50){
        this.count10 += count10;
        this.count20 += count20;
        this.count50 += count50;
        balance += count10 * 10L + count20 * 20L + count50 * 50L;
    }



    public boolean getMoney(int money){
        if(balance >= money){
            int oldCount50 = count50;
            while(money >= 50) {
                money -= 50;
                count50--;
            }
            int oldCount20 = count20;
            while(money >= 20) {
                money -= 20;
                count20--;
            }
            int oldCount10 = count10;
            while(money >= 10) {
                money -= 10;
                count10--;
            }
            System.out.println("Gotten:\n50$: " + (oldCount50 - count50) + "\n20$: " + (oldCount20 - count20) +
                    "\n10$: " + (oldCount10 - count10));
            return true;
        }
        return false;
    }
}
