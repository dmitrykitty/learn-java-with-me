package collections.sorting.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AddStarsRunner {
    static void main() {

        List<String> list = new LinkedList<>(Arrays.asList("this", "is", "lots", "of", "fun", "for", "every", "Java", "programmer"));

        AddStars.markLength4(list);
        System.out.println(list);

    }


}
