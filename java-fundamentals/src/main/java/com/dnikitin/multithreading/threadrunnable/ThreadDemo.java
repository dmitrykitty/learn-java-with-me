package com.dnikitin.multithreading.threadrunnable;

public class ThreadDemo {
    static void main(){
        SimpleThread simpleThread = new SimpleThread();
        System.out.println(simpleThread.getState().name()); //NEW
        //new - kiedy tylko jest stwrorzony
        //waiting - oczekuje na wykonanie (np join u main)
        //terminated - wykonany
        //blocked - jeden thread blokujer inny
        //timed_waiting - kiedy np do joina przekazuje sie jakis czas


        //nie mozemy wywołas run bez tworzsenia Thread
        var threadRunnable = new Thread(new SimpleRunnable());
        //mozna wskazac tez var

        //nawet mozna przekaz tam labde, bo przyjmuje Runnable
        Thread threadLambda = new Thread(() -> System.out.println("Hello from lambda: " + Thread.currentThread().getName()));

        threadRunnable.start();

        threadLambda.start();

        simpleThread.start(); //Hello Thread-0
        //tworzenie nowego Thread i wywołanie w nim funkcji run()
        System.out.println(simpleThread.getState().name()); //RUNNABLE
        try {
            simpleThread.join(100L); //jezeli simpleThread nie zdąży wykonac sie w te 100 mlsec - zacznie sie kolejny Thread
            threadLambda.join();
            threadRunnable.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Thread w ktorym wywołana będzie czekła na zakonczenie działania threadu od ktorego wywołano

        //Thread.currentThread() -> zwraca strumien ktory teraz wykonuje sie
        System.out.println(Thread.currentThread().getName()); //main
        System.out.println(simpleThread.getState().name()); //TERMINATED


    }
}
