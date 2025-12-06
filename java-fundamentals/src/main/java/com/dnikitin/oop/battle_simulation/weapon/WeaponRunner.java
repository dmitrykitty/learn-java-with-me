package com.dnikitin.oop.battle_simulation.weapon;

import com.dnikitin.oop.battle_simulation.Archer;
import com.dnikitin.oop.battle_simulation.Hero;
import com.dnikitin.oop.battle_simulation.Warrior;

public class WeaponRunner {
    static void main() {
        Archer<RangeWeapon> archer1 = new Archer<>("Archer", 100, 20);
        archer1.setWeapon(new Bow());

        Warrior<Sword> warrior1 = new Warrior<>("Warrior", 100, 20);
        warrior1.setWeapon(new Sword());

        printWeaponDamageByHero(archer1);
        printWeaponDamageByHero2(archer1);
    }

    public static <T extends Weapon> void printWeaponDamageByHero(Hero<T> hero) {
        System.out.println(hero.getWeapon().getDamage());
    }

    public static void printWeaponDamageByHero2(Hero<? extends Weapon> hero) {
        //? extends Weapon oznacza, ze oczekuje sie jakis typ, ktory jest Weapon lub dowolnym go Derived.
        // Ograniczenie od góry
        //Producer - pozwala tylko produkowac(czytac) wartosci
        Weapon wp = hero.getWeapon(); //ok
        //hero.setWeapon(new Bow()); //bład - potrzebujemy konkretny typ
        System.out.println(hero.getWeapon().getDamage());
    }

    public static void printWeaponDamageByHero3(Hero<? super Sword> hero) {
        //? super Sword oznacza, ze oczekuje sie jakis typ, ktory jest Sword lub go Base
        //ograniczenie od dolu
        //Consumer - pozwala tylko uzywac(modyfikowac) wartosci
        //Sword wp = hero.getWeapon(); //bład, bo mozemy dostac i coś wyzej
        hero.setWeapon(new Sword()); //ok
        System.out.println(hero.getWeapon().getDamage());

        /*
        Если у нас есть иерархия классов (Integer <- Number <- Object), то поставив ? super Number
        мы можем использовать Number и объекты более верхнего уровня (Object).
        Если же поставим ? extends Number, то Number и объекты ниже в иерархии наследования, т.е. Integer
         */
    }
}
