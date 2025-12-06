package com.dnikitin.exceptions.exceptionpractise;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Random;

public class Task6 {

    private static final Random RANDOM = new Random();

    static void main() {
        try {
            methodThrowingRandomException();
        } catch (FileNotFoundException e) {
            System.err.println("Wystąpił błąd pliku (Checked):");
            e.printStackTrace();
        } catch (MyException e) {
            System.err.println("Wystąpił nasz własny błąd (Checked):");
            e.printStackTrace();
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Wystąpił błąd programisty (Unchecked - argument lub null):");
            e.printStackTrace();
        } catch (Exception e) {
            // Łapiemy resztę błędów 'runtime'
            System.err.println("Wystąpił inny błąd wykonania (Unchecked):");
            e.printStackTrace();
        }

    }

    private static void methodThrowingRandomException() throws Exception {
        int i = RANDOM.nextInt(5);
        switch (i) {
            case 0 -> throw new FileNotFoundException("File not found");
            case 1 -> throw new IllegalArgumentException("Illegal argument");
            case 2 -> throw new MyException("My exception");
            case 3 -> throw new NullPointerException("Null pointer");
            default -> throw new RuntimeException("Something went wrong");
        }
    }
}
