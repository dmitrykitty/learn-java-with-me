package com.dnikitin.oop.battle_simulation;

import com.dnikitin.oop.battle_simulation.weapon.RangeWeapon;

public class Archer<T extends RangeWeapon> extends Hero<T> {
    public Archer(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public void attackEnemy(Entity enemy) {
        System.out.println(getName() + " shoots with bow for " + getDamage() + " damage");
        enemy.takeDamage(getDamage());
    }
}
