package com.dnikitin.oop.battle_simulation;

public class Enemy extends Entity {

    public Enemy(int health, int damage) {
        super(health, damage);
    }

    @Override
    public void takeDamage(int damage) {
        int health = getHealth() > damage ? getHealth() - damage : 0;
        setHealth(health);
        if (health > 0) {
            System.out.println("After " + damage + " taken dmg enemy has only " + health + " hp left");
        } else {
            System.out.println("Enemy is dead");
        }
    }
}
