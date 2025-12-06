package com.dnikitin.oop.battle_simulation;

public abstract class Entity implements Mortal {
    private int health;
    private int damage;

    public Entity(int health, int damage) {

        this.health = health;
        this.damage = damage;
    }


    public void attackEnemy(Entity entity) {
        System.out.println("Enemy attacking for  " + getDamage() + " dmg!");
        entity.takeDamage(getDamage());
    }

    public abstract void takeDamage(int damage);

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
