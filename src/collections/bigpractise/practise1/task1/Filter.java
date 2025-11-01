package collections.bigpractise.practise1.task1;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    private Filter() {}

    private static boolean isOdd(int n){
        return n % 2 != 0;
    }

    public static List<Integer> getOddArray(List<Integer> list){
        List<Integer> result = new ArrayList<>(list);
        result.removeIf(Filter::isOdd);
        return result;

    }
}
