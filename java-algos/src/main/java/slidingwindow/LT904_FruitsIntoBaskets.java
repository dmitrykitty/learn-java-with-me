package slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * You are visiting a farm that has a single row of fruit trees arranged from left to right.
 * The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 * You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
 * You only have two baskets, and each basket can only hold a single type of fruit.
 * There is no limit on the amount of fruit each basket can hold.
 * Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree)
 * while moving to the right. The picked fruits must fit in one of your baskets.
 * Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
 * Given the integer array fruits, return the maximum number of fruits you can pick.

 * Example 1:
 * Input: fruits = [1,2,1]
 * Output: 3
 * Explanation: We can pick from all 3 trees.

 * Example 2:
 * Input: fruits = [0,1,2,2]
 * Output: 3
 * Explanation: We can pick from trees [1,2,2].
 * If we had started at the first tree, we would only pick from trees [0,1].

 * Example 3:
 * Input: fruits = [1,2,3,2,2]
 * Output: 4
 * Explanation: We can pick from trees [2,3,2,2].
 * If we had started at the first tree, we would only pick from trees [1,2].
 */
public class LT904_FruitsIntoBaskets {
    static void main() {
        int i = totalFruit(new int[]{1,2,3,2,2});
    }

    //my first attempt using hashMap. Probably not the best one
    public static int totalFruit(int[] fruits) {
        int n = fruits.length;
        int begin = 0;
        int result = Integer.MIN_VALUE;
        Map<Integer, Integer> windowState = new HashMap<>(); //amount of distinct fruit types (no more than 2)

        for(int end = 0; end < n; end++){
            windowState.put(fruits[end],  windowState.getOrDefault(fruits[end], 0) + 1);
            while(windowState.size() > 2){
                int valueToRemove = fruits[begin];
                while(fruits[begin] == valueToRemove) {
                    begin++;
                    windowState.put(valueToRemove, windowState.get(valueToRemove) - 1);
                }
                if(windowState.get(valueToRemove) == 0){
                    windowState.remove(valueToRemove);
                }
            }
            result = Math.max(result, end - begin + 1);
        }
        return result;
    }

    //a bit shorter and cleaner
    public static int totalFruit2(int[] fruits) {
        int n = fruits.length;
        int begin = 0;
        int result = Integer.MIN_VALUE;
        Map<Integer, Integer> windowState = new HashMap<>(); //amount of distinct fruit types (no more than 2)

        for(int end = 0; end < n; end++){
            windowState.put(fruits[end],  windowState.getOrDefault(fruits[end], 0) + 1);
            while(windowState.size() > 2){
                windowState.put(fruits[begin], windowState.get(fruits[begin]) - 1);
                if(windowState.get(fruits[begin]) == 0) {
                    windowState.remove(fruits[begin]);
                }
                begin++;
            }
            result = Math.max(result, end - begin + 1);
        }
        return result;
    }
}
