package oop.lesson1.task1_inheritance;

//wszystko dziedziczy po klasie object Object -> Computer -> Laptop
//od klasy object wszystkie obiekty innych klas dostaja metody hashcode, equals, wait, notify, toString,
public class Laptop extends Computer {
    private int screenSize;

    {
        System.out.println("Laptop block initializer");
    }
    //- najpierwsz wszystkie statyczne init blocks
    //- (są uruchomiane 1 raz przed tworzeniem klasy - dla skomplikowanej inicjalizacji pol statycznych)
    //- pozniej zwykły init block Computer(zedy jednakowy kod w kosntrutorach przenies do init blocku)
    //- pozniej Computer konstruktor
    //- laptop init block
    //- Laptop Construktor

    static {
        System.out.println("Laptop static initializer");
    }

    public Laptop(Ram ram, Ssd ssd, int screenSize) {
        super(ram, ssd); //wywołanie konstruktora base class (tylko jezeli on cos przyjmuje)
        this.screenSize = screenSize;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void open() {
        System.out.println("Open laptop");
    }


}
