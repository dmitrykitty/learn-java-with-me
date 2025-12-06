package com.dnikitin.collections.bigpractise.practise1.task3;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapUtils {
    private MapUtils() {}

    public static boolean isUniqueValues(Map<String, String> map){
        Set<String> values = new HashSet<>(map.values());
        return values.size() == map.size();
    }
}
