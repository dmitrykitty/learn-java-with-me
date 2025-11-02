package exceptions.exceptionpractise;

public class MyException extends RuntimeException {
    public MyException(String message) {
        super(message);
    }

    public MyException() {
        super();
    }

    public MyException(Exception e){
        super(e);
    }

}
