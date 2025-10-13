package introdaction.lesson2;

public class ComputerRunner {
    void main(){
        Computer c = new Computer(); //constructor
        c.start();
        c.printState();

        Computer c2 = new Computer(new MotherBoard("Agenta"), 1000, 16);
        c2.printState();
    }
}
