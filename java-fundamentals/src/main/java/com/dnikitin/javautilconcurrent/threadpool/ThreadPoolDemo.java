package com.dnikitin.javautilconcurrent.threadpool;

import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {

        // 1. SingleThreadExecutor
        // Posiada TYLKO JEDEN wątek. Zadania są wykonywane sekwencyjnie (jedno po drugim).
        // Gwarantuje kolejność wykonania zgodną z kolejnością zgłoszeń.
        // Jeśli wątek padnie (wyjątek), tworzony jest nowy, by kontynuować.
        Executors.newSingleThreadExecutor();

        // 2. FixedThreadPool
        // Pula o STAŁEJ liczbie wątków (tu: 5).
        // Jeśli wszystkie 5 pracuje, kolejne zadania czekają w kolejce.
        // Dobre, gdy chcemy ograniczyć obciążenie procesora (np. max 5 wątków naraz).
        Executors.newFixedThreadPool(5);

        // 3. CachedThreadPool
        // Pula ELASTYCZNA. Nie ma górnego limitu wątków.
        // Tworzy nowe wątki, jeśli są potrzebne, ale REUŻYWA te, które są wolne.
        // Wątki bezrobotne przez 60 sekund są usuwane.
        // Idealne do dużej liczby krótkich zadań.
        Executors.newCachedThreadPool();

        // 4. ScheduledThreadPool
        // Pula przeznaczona do zadań PLANOWANYCH w czasie.
        // Pozwala uruchomić zadanie z opóźnieniem (delay) lub cyklicznie (np. co 10 sekund).
        Executors.newScheduledThreadPool(3);

        // 5. WorkStealingPool (Java 8+)
        // Oparta na ForkJoinPool.
        // Każdy wątek ma swoją własną kolejkę zadań.
        // Jeśli jeden wątek skończy swoją pracę, "kradnie" zadania z kolejek innych, zapracowanych wątków.
        // Wykorzystuje wszystkie dostępne rdzenie procesora (chyba że podasz inaczej).
        Executors.newWorkStealingPool();
    }
}

