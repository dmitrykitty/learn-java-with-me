package com.dnikitin.functionalprogramming.lambdafunctions;

import java.util.Comparator;

public class LambdaExample {
    static void main() {
        Comparator<Integer> cp = new IntegerComparator();
        //------> klasy Anonimowe
        cp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        System.out.println(cp.compare(1, 2));
        //------> lambda functions

        /*
        Comparator<Integer> cp = new IntegerComparator();
        ->
        Comparator<Integer> cp = (Integer o1, Integer o2) {return Integer.compare(o1, o2)} ;
        ->
        Comparator<Integer> cp = (o1, o2) -> Integer.compare(o1, o2);
         */

        cp = (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(cp.compare(1, 2));

        //------> reference to existing method
        cp = Integer::compare;
        System.out.println(cp.compare(1, 2));
    }
    /*
    Functional interface:
    Interfejs funkcyjny to interfejs, który zawiera dokładnie jedną metodę ABSTRACT
    1)Predicate<T> : boolean test(T t) : Testuje (Predykat). Bierze obiekt, zwraca true/false. :	s -> s.length() > 10
    2)Function<T, R> : R apply(T t) :	Transformuje (Funkcja). Bierze obiekt typu T, zwraca obiekt typu R. : s -> s.length()
    3)Consumer<T> : void accept(T t) : Konsumuje. Bierze obiekt i wykonuje na nim akcję (nic nie zwraca). :	s -> System.out.println(s)
    4)Supplier<T>	: T get() :	Dostarcza. Nie bierze nic, zwraca nowy obiekt. : () -> new ArrayList<>()
     */


    static class IntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    }
}
