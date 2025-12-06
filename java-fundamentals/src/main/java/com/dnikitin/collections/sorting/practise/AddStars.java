package com.dnikitin.collections.sorting.practise;

import java.util.Iterator;
import java.util.List;

public class AddStars {

    public static void markLength4(List<String> list){
        for(int i = list.size() - 1; i >= 0; i--){
            if(list.get(i).length() == 4){
                list.add(i, "****");
            }
        }

    }
}
