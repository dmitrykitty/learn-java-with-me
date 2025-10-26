package generics.lesson1;

import oop.battle_simulation.Archer;
import oop.battle_simulation.Hero;
import oop.battle_simulation.Mage;

public class ArrayRunner {
    public static void main(String[] args) {
        //jezeli nie wskazac zaden typ - będzie object
        MyArray<Integer> ar = new MyArray<>(10);
        ar.add(5);
        ar.add(1);
        ar.add(3);
        ar.add(2);

        for (Integer i : ar) {
            System.out.println(i);
        }

    }
}
