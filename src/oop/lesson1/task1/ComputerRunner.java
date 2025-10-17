package oop.lesson1.task1;

public class ComputerRunner {
    void main(){
        Computer c = new Computer(new Ram(1024), new Ssd(1024));
        c.printState();

    }
}
