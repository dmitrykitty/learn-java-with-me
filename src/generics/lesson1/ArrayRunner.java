package generics.lesson1;

public class ArrayRunner {
    public static void main(String[] args) {
        MyArray ar = new MyArray(10);
        ar.add("5");
        ar.add("2");
        ar.add("3");

        System.out.println(ar.get(0));
    }
}
