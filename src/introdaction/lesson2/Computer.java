package introdaction.lesson2;

public class Computer { //PUBLIC DOSTĘPNY WE WSZYSTKICH PAKIETACH (dla klas tylko public lub package private)
    private int ssd = 500;
    private int ram = 8;
    private MotherBoard mb;

    public Computer(){ //default constructor
        System.out.println("Constructor");
    }
    public Computer(MotherBoard mb, int ram, int ssd){
        this.mb = mb;
        this.ram = ram;
        this.ssd = ssd;
    }

    Computer(int ssd, int ram){ //package private dostepne tylko w tym pakiecie
        this.ssd = ssd;
        this.ram = ram;
    }
    private Computer(int ssd){ //private dostepny tylko w tej klasie
        this.ssd = ssd;
    }

    protected void start(){ //dostepne wewątrz pakietu oraz dla klas pochodnych
        System.out.println("Computer");
    }

    void printState(){
        System.out.println("Ram: " + ram + " ssd: " + ssd + " mother board: " + mb.getType());
    }
}
