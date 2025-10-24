package oop.battle_simulation;

public class Archer extends Hero {
    public Archer(String name, int health, int damage) {
        super(name, health, damage);
    }

    @Override
    public void attackEnemy(Entity enemy) {
        System.out.println(getName() + " shoots with bow for " + getDamage() + " damage");
        enemy.takeDamage(getDamage());
    }
}
