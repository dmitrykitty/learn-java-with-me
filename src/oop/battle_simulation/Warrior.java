package oop.battle_simulation;

public class Warrior extends Hero {

    public Warrior(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public void attackEnemy(Entity enemy) {
        System.out.println(getName() + " stabbed with sword for " + getDamage() + " damage");
        enemy.takeDamage(getDamage());
    }

}
