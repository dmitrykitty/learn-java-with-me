package collections.bigpractise.practise1.task1;

import java.util.ArrayList;
import java.util.List;

public class Task1Runner {
    static void main() {

        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        List<Integer> oddNumbers = Filter.getOddArray(integers);
        System.out.println(oddNumbers);
    }
}
