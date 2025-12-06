package com.dnikitin.collections.bigpractise.practise1.task2;

import java.util.*;

public class ArrayUtils {
    private ArrayUtils() {}

    public static int countUniqueNumbers(List<Integer> numbers){
        return new HashSet<>(numbers).size();
    }

    public static int getNotRepeatedNumbersCount(List<Integer> numbers){
        Map<Integer, Boolean> map = new HashMap<>();
        for(int number : numbers){
            if(map.containsKey(number)){
                map.put(number, false);
            }else{
                map.put(number, true);
            }
        }
        int count = 0;
        for(Boolean value : map.values()){
            if(value){
                count++;
            }
        }
        return count;
    }


}
