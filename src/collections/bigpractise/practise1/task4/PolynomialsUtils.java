package collections.bigpractise.practise1.task4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PolynomialsUtils {
    private PolynomialsUtils() {
    }

    public static Map<Integer, Integer> add(Map<Integer, Integer> pl1, Map<Integer, Integer> pl2) {
        HashMap<Integer, Integer> result = new HashMap<>(pl1);
        for (Map.Entry<Integer, Integer> entry : pl2.entrySet()) {
            result.put(entry.getKey(), entry.getValue() + pl1.getOrDefault(entry.getKey(), 0));
        }
        return result;
    }

    public static String polymomialtoString(Map<Integer, Integer> map){
        Map<Integer, Integer> sortedMap = new TreeMap<>(Comparator.reverseOrder());
        sortedMap.putAll(map);

        StringBuilder result = new StringBuilder();
        for(Map.Entry<Integer, Integer> entry : sortedMap.entrySet()){
            int i = entry.getKey();
            if(i == 0){
                result.append(entry.getValue());
            } else if(i == 1){
                result.append(entry.getValue() + "x + ");
            } else {
                result.append(entry.getValue() + "x^" + entry.getKey() + " + ");
            }
        }
        return result.toString();
    }
}

