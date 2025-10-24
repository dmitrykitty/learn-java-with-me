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
}
