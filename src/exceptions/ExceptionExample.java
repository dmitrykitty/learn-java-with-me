package exceptions;

import java.io.FileNotFoundException;

public class ExceptionExample {

    public static void main(String[] args) {
        System.out.println("before unsafe");
        try {
            unsafe(-1);
        } catch (FileNotFoundException | ArithmeticException e) { //collapse few exceptions types
            // lub wybrac wspolnego rodzica Exception e
            e.printStackTrace();
        } finally {
            //wykonuje sie zawsze
            System.out.println("finally");
        }
        System.out.println("after unsafe");
        //kontynuacja programu

    }


    public static void unsafe(int n) throws FileNotFoundException, ArithmeticException {
        System.out.println("unsafe start");
        //trzeba zaznaczyc, ze metoda throws exception
        if (n > 0) {
            throw new FileNotFoundException();
        }
        System.out.println("unsafe end");
    }
}