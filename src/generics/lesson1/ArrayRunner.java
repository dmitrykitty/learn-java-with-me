package generics.lesson1;

import oop.battle_simulation.Archer;
import oop.battle_simulation.Hero;
import oop.battle_simulation.Mage;

public class ArrayRunner {
    public static void main(String[] args) {
        //jezeli nie wskazac zaden typ - będzie object
        MyArray<Hero> ar = new MyArray<>(10);
        ar.add(new Archer("Archer", 100, 20));
        ar.add(new Archer("Archer2", 100, 20));
        ar.add(new Mage("Mage", 100, 20));

        System.out.println(ar.get(0));
    }
}
