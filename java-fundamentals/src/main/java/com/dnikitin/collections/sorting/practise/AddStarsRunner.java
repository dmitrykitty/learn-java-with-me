package com.dnikitin.collections.sorting.practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AddStarsRunner {
    static void main() {



        List<String> list = new LinkedList<>(Arrays.asList("this", "is", "lots", "of", "fun", "for", "every", "Java", "programmer"));

        long startTime = System.nanoTime();
        AddStars.markLength4(list);
        System.out.println(list);

        long endTime = System.nanoTime();
        System.out.println("Time with LinkedList: " + (endTime - startTime));

        List<String> list2 = new ArrayList<>(Arrays.asList("this", "is", "lots", "of", "fun", "for", "every", "Java", "programmer"));
        startTime = System.nanoTime();
        AddStars.markLength4(list2);
        System.out.println(list2);
        endTime = System.nanoTime();
        System.out.println("Time with ArrayList: " + (endTime - startTime));

    }
}
