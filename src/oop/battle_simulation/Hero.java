package oop.battle_simulation;

public abstract class Hero extends Entity{
    private String name;

    public Hero(String name, int health, int damage) {
        super(health, damage);
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public abstract void attackEnemy(Enemy enemy);
}
