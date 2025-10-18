package oop.lesson2_inheritance_polimorfizm.task1;

public class ComputerRunner {
    static void main() {
        Computer laptop = new Laptop(new Ssd(1024), new Ram(32), 1);
        Computer mobile = new Mobile(new Ram(16), new Ssd(512));

        loadComputers(laptop, mobile);
        printComputers(new Computer[]{laptop, mobile});
    }

    public static void loadComputers(Computer... computers) {
        for (Computer c : computers) {
            c.load();
            System.out.println("--------------------------------");
        }
    }

    public static void printComputers(Computer[] computers) {
        for (Computer c : computers) {
            c.printState();
            if (c instanceof Laptop) {
                //jezeli c jest typu laptop to mozemy srzutowac c z Computer na Laptop i wywołac metody Laptopa
                ((Laptop) c).open();
            }
            System.out.println("--------------------------------");
        }
    }
}
