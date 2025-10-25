package oop.battle_simulation;

import oop.battle_simulation.weapon.MagicWeapon;

public class Mage<T extends MagicWeapon> extends Hero<T> {

    public Mage(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public void attackEnemy(Entity enemy){
        System.out.println(getName() + " casts fireball for " + getDamage() + " damage");
        enemy.takeDamage(getDamage());
    }
}
