package oop.lesson2_inheritance_polimorfizm.task1;

public class ComputerRunner2 {
    static void main() {
        Computer laptop = new Laptop(new Ssd(1024), new Ram(32), 1);
        Computer mobile = new Mobile(new Ram(16), new Ssd(512));

        printComputers2(laptop, mobile);

    }

    public static void printComputers2(Printable... computers) {
        //mozemy jako interfejs do metody przekazywac objects naszych klas, bo Computer implements interface Printable
        for (Printable computer : computers) {
            computer.printState();

        }
    }
}
