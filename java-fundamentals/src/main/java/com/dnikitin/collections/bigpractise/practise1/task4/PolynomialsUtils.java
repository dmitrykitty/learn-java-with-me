package com.dnikitin.collections.bigpractise.practise1.task4;

import java.util.*;

public class PolynomialsUtils {
    private PolynomialsUtils() {
    }

    public static Map<Integer, Integer> add(Map<Integer, Integer> pl1, Map<Integer, Integer> pl2) {
        HashMap<Integer, Integer> result = new HashMap<>(pl1);
        for (Map.Entry<Integer, Integer> entry : pl2.entrySet()) {
            result.put(entry.getKey(), entry.getValue() + pl1.getOrDefault(entry.getKey(), 0));
            //result.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
        return result;
    }

    public static String polymomialtoString(Map<Integer, Integer> map) {
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(Comparator.reverseOrder());
        sortedMap.putAll(map);

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            if (Objects.equals(entry.getKey(), sortedMap.firstEntry().getKey())) {
                result.append((Math.abs(entry.getValue()) == 1 ? "" : entry.getValue()) + "x^" + entry.getKey());
            } else {
                int key = entry.getKey();
                int value = entry.getValue();
                result.append(value > 0 ? " + " : " - ");

                if (value != 1 && value != -1) {
                    result.append(Math.abs(value));
                }
                result.append(key == 0 ? "" : (key == 1 ? "x" : "x^" + key));
            }
        }
        return result.toString();
    }

    public static String polymomialtoString2(Map<Integer, Integer> map) {
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(Comparator.reverseOrder());
        sortedMap.putAll(map);

        List<String> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            StringBuilder sb = new StringBuilder();
            if (entry.getValue() > 0) {
                if (entry.getValue() != 1) {
                    sb.append(entry.getValue());
                }
            } else {
                sb.append("(-");
                if (entry.getValue() != -1) {
                    sb.append(Math.abs(entry.getValue()));
                }
            }
            sb.append(entry.getKey() == 0 ? "" : (entry.getKey() == 1 ? "x" : "x^" + entry.getKey()));
            if (entry.getValue() < 0) {
                sb.append(")");
            }
            result.add(sb.toString());
        }
        return String.join(" + ", result);
    }

    public static String polymomialtoString3(Map<Integer, Integer> map) {
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(Comparator.reverseOrder());
        sortedMap.putAll(map);

        StringBuilder result = new StringBuilder();
        boolean isFirst = true;

        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();

            if (value == 0) {
                continue;
            }

            if(isFirst){
                isFirst = false;
                if(value < 0){
                    result.append("-");
                }
            } else {
                result.append(value > 0 ? " + " : " - ");
            }

            int absValue = Math.abs(value);

            if(absValue != 1 || key == 0){
                result.append(absValue);
            }

            //x or x^_
            if(key > 0){
                result.append("x");
                if (key > 1) {
                    result.append("^").append(key);
                }
            }
        }
        return result.toString();
    }

}

