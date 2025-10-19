package oop.battle_simulation;

public abstract class Entity implements Mortal {
    private int health;
    private int damage;

    public Entity(int health, int damage) {

        this.health = health;
        this.damage = damage;
    }


    public void attackEnemy(Enemy enemy) {
        System.out.println("Attacking for  " + getDamage() + " dmg!");
    }

    public void takeDamage(int damage) {
        health = health > damage ? health - damage : 0;
        if(health > 0) {
            System.out.println("After " + damage + " taken dmg only " + health + " hp left");
        } else {
            System.out.println("Enemy is dead");
        }
    }

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

}
