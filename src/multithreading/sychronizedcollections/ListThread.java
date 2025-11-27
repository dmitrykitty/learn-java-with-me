package multithreading.sychronizedcollections;

import java.util.List;

public class ListThread extends Thread {
    private final List<Integer> list;

    public ListThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        //synchronized (list) {
        //pierwsze rozwiqazanie dodanie synchonized, ale problem, ze w innym thread mozna wywołac metod bez synchronized
        //i dalej będzie problem -> lepiej uzych synchronized collection
            for (int i = 0; i < 500; i++) {
                list.add(i);
            //}
        }
    }
}
