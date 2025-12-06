package com.dnikitin.oop.battle_simulation;

public class TrainingGround {
    static void main() {

        Hero warr1 = new Warrior("Kristinius", 100, 20);
        Hero mage1 = new Mage("Kristibalde", 80, 30);
        Hero arch1 = new Archer("Kristik", 50, 10);
        Enemy enemy1 = new Enemy(200, 10);

        attack(enemy1, warr1, mage1, arch1);
    }

    public static void attack(Enemy enemy, Hero... heroes) {
        int amount_of_alive_heroes = heroes.length;
        while (enemy.isAlive()) {
            for (Hero hero : heroes) {
                if (enemy.isAlive() && hero.isAlive()) {
                    hero.attackEnemy(enemy);
                    if (enemy.isAlive()) {
                        enemy.attackEnemy(hero);
                    }
                    System.out.println("--------------------------------");
                } else {
                    if (!hero.isAlive()) {
                        amount_of_alive_heroes--;
                    }
                }
            }
            if (amount_of_alive_heroes == 0) {
                break;
            }
        }

        if (amount_of_alive_heroes == 0) {
            System.out.println("Game over!!!\nAll heroes are dead!");
        } else {
            System.out.println("Game over!!!\nHeroes won");
        }



    }
}
