package com.dnikitin.oop.battle_simulation;

import com.dnikitin.oop.battle_simulation.weapon.Weapon;

public abstract class Hero<T extends Weapon> extends Entity{
    private final String name;
    private T weapon;

    public Hero(String name, int health, int damage) {
        super(health, damage);
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public abstract void attackEnemy(Entity entity);

    @Override
    public void takeDamage(int damage){
        int health = getHealth() > damage ? getHealth() - damage : 0;
        setHealth(health);
        if (health > 0) {
            System.out.println("After " + damage + " taken dmg " + getName() + " has only " + health + " hp left");
        } else {
            System.out.println(getName() + " is dead");
        }
    }

    public T getWeapon() {
        return weapon;
    }

    public void setWeapon(T weapon) {
        this.weapon = weapon;
    }
}
