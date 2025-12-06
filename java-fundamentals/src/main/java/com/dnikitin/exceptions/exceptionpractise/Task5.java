package com.dnikitin.exceptions.exceptionpractise;

public class Task5 {
    static void main() {
        try {
            catchException();
        } catch (MyException e) {
            System.err.println("Catch in main");
            e.printStackTrace();
        }

    }
    public static void catchException(){
        try {
            unsafe();
        } catch (RuntimeException e) {
            System.err.println("Catch in catchException");
            throw new MyException(e);
        }
    }
    public static void unsafe(){
        throw new RuntimeException("Runtime");
    }
}
