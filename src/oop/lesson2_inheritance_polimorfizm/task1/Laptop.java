package oop.lesson2_inheritance_polimorfizm.task1;

public class Laptop extends Computer{
    private int weight;

    public Laptop(Ssd ssd, Ram ram, int weight){
        super(ram, ssd);
        this.weight = weight;
    }

    @Override
    public void load() {
        open();
        System.out.println("Loading...");
    }
    @Override
    public void printState(){
        super.printState();
        System.out.println("Weight: " + weight);
    }

    public void open(){
        System.out.println("Laptop is opened");
    }

    public int getWeight() {
        return weight;
    }
}
