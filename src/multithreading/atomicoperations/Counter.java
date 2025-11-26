package multithreading.atomicoperations;

public class Counter {
    private int count;
    private static String description;

    public static void init() {
        //trzeba sychronizowac static, zeby rozne wątki nie tworzyli jednocześnie kilka obiektow static
        synchronized (Counter.class) {
            if (description == null) {
                description = "Bebebe";
            }
        }
    }

    //synchronaized na poziomie metody
    public synchronized void increment() {
        //w jednym odcinku czasu tylko jeden tread moze wywołac ten metod
        count++;
    }


    public void decrement() {
        //synchronized na poziomie blocku
        //obiekt do synchronizacji
        //kazdy obiekt posiada swoj monitor
        //kiedy wątek jest wewątrz sychr. blocku lub metody - fla jest true i inn6y wątek nie moze pracowac z tym kodem
        synchronized (this) {
            //flaga true, jezeli jakis biekt wykonywuje ten block kodu
            count--;
        }
    }

    public int getCount() {
        return count;
    }
}
