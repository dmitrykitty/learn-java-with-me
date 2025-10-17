package oop.lesson1.task1;

public class Computer {
    private Ram ram;
    private Ssd ssd;

    public Computer(Ram ram, Ssd ssd){
        this.ram = ram;
        this.ssd = ssd;
    }

    public Ram getRam() {
        return ram;
    }
    public Ssd getSsd() {
        return ssd;
    }

    public void printState(){
        System.out.println("Ram: " + ram.getSize() + " ssd: " + ssd.getSize());
    }
}
