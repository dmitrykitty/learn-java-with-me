package com.dnikitin.collections.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratoRunner {
    static void main() {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        List<Integer> lst = new ArrayList<>(integers);

        for (Integer i : integers) {
            System.out.println(i);
        }

        //THE SAME AS
        Iterator<Integer> iterator = integers.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        for(Iterator<Integer> it = lst.iterator(); it.hasNext(); ){
            Integer i = it.next();
            if(i == 3)
                it.remove();
        }
        System.out.println(lst);


    }
}
