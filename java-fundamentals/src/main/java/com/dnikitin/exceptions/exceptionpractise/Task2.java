package com.dnikitin.exceptions.exceptionpractise;

public class Task2 {
    static void main() {
        int[] array = {1, 2, 3, 4, 5};
        try {
            System.out.println(array[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Catch");
            e.printStackTrace();
        }
    }
}
