package com.dnikitin.exceptions.exceptionpractise;

public class Task1 {
    public static void main(String[] args) {
        String value = null;
        try {
            value.length();
            //nul pointer exception
        } catch (NullPointerException e) {
            System.err.println("Catch");
            e.printStackTrace();
        }


    }
}

