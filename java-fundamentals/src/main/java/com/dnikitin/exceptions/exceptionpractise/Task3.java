package com.dnikitin.exceptions.exceptionpractise;

public class Task3 {
    static void main() {
        try {
            System.out.println(method(10, 0));
        } catch (MyException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            System.err.println("Catch");
            e.printStackTrace();
        }

    }

    public static double method(int a, int b) throws MyException {
        if (b == 0) {
            throw new MyException(new RuntimeException("RT: Division by zero"));
        }
        return (double) a / (double) b;
    }
}
