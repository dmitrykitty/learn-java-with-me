package oop.lesson2_inheritance_polimorfizm.task1;


public abstract class Computer {

    private Ssd ssd;
    private Ram ram;

    public Computer(Ram ram, Ssd ssd){
        this.ram = ram;
        this.ssd = ssd;
    }

    public abstract void load();

    public void printState(){
        System.out.println("Ssd: " + ssd.getSize() + " Ram: " + ram.getSize());
    }

    public Ssd getSsd() {
        return ssd;
    }

    public Ram getRam() {
        return ram;
    }
}
