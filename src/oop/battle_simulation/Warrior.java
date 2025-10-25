package oop.battle_simulation;

import oop.battle_simulation.weapon.MeleeWeapon;

public class Warrior<T extends MeleeWeapon> extends Hero<T> {

    public Warrior(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public void attackEnemy(Entity enemy) {
        System.out.println(getName() + " stabbed with sword for " + getDamage() + " damage");
        enemy.takeDamage(getDamage());
    }

}
