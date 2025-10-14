package strings.lesson14;

public class StringBuilderAndStringBuffer {

    static void main() {
        //StringBuilder - dynamiczny wektor
        String value = "";
        long startTime = System.currentTimeMillis();

        for(int i = 0; i < 100_000; i++){
            value += i;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time with String: " + (endTime - startTime));//2178

        StringBuilder value2 = new StringBuilder();
        startTime = System.currentTimeMillis();

        for(int i = 0; i < 100_000; i++){
            value2.append(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("Time with StringBuilder: " + (endTime - startTime));//3
    }


}
