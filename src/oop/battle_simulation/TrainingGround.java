package oop.battle_simulation;

public class TrainingGround {
    static void main() {

        Hero warr1 = new Warrior("Kristinius", 100, 20);
        Hero mage1 = new Mage("Kristibalde", 80, 30);
        Hero arch1 = new Archer("Kristik", 50, 10);
        Enemy enemy1 = new Enemy(200, 10);

        attack(enemy1, warr1, mage1, arch1);
    }

    public static void attack(Enemy enemy, Hero... heroes){
        while(enemy.isAlive())
            for (Hero hero : heroes) {
                if(enemy.isAlive()) {
                    hero.attackEnemy(enemy);
                    System.out.println("--------------------------------");
                } else {
                    break;
                }
            }
    }
}
