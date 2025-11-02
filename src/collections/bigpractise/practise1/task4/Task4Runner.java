package collections.bigpractise.practise1.task4;
import java.util.Map;

public class Task4Runner {
    static void main() {

        Map<Integer, Integer> polynomial1 = Map.of(
                5, -1,
                4, 2,
                2, 3);
        Map<Integer, Integer> polynomial2 = Map.of(
                4, -3,
                3, 1,
                2, 4,
                1, 2,
                0, 1);

        Map<Integer, Integer> sum = PolynomialsUtils.add(polynomial1, polynomial2);
        System.out.println(PolynomialsUtils.polymomialtoString(sum));
        System.out.println(PolynomialsUtils.polymomialtoString2(sum));
        System.out.println(PolynomialsUtils.polymomialtoString3(sum));

    }
}
