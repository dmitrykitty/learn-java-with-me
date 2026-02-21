package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given a 0-indexed integer array piles, where piles[i] represents the number of stones in the ith pile,
 * and an integer k. You should apply the following operation exactly k times:
 * Choose any piles[i] and remove floor(piles[i] / 2) stones from it.
 * Notice that you can apply the operation on the same pile more than once.
 * Return the minimum possible total number of stones remaining after applying the k operations.
 * floor(x) is the largest integer that is smaller than or equal to x (i.e., rounds x down).

 * Example 1:
 * Input: piles = [5,4,9], k = 2
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [5,4,5].
 * - Apply the operation on pile 0. The resulting piles are [3,4,5].
 * The total number of stones in [3,4,5] is 12.

 * Example 2:
 * Input: piles = [4,3,6,7], k = 3
 * Output: 12
 * Explanation: Steps of a possible scenario are:
 * - Apply the operation on pile 2. The resulting piles are [4,3,3,7].
 * - Apply the operation on pile 3. The resulting piles are [4,3,3,4].
 * - Apply the operation on pile 0. The resulting piles are [2,3,3,4].
 * The total number of stones in [2,3,3,4] is 12.
 */
public class LT1962_RemoveStonesToMinimizeTheTotal {
    //O(logn * (n + k))
    //mem O(n)
    public int minStoneSum(int[] piles, int k) {
        Queue<Integer> stones = new PriorityQueue<>(Comparator.reverseOrder());
        int result = 0;

        //O(n * logn)
        for (int pile : piles) {
            stones.add(pile);
            result += pile;
        }

        //O(k * logn)
        for (int i = 0; i < k; i++) {
            int maxStones = stones.poll();
            int stonesToRemove = maxStones / 2;

            stones.add(maxStones - stonesToRemove);
            result -= stonesToRemove;

        }
        return result;
    }

    //O(n + maxN)
    //mem O(maxN)
    public int minStoneSumFrequencyArray(int[] piles, int k) {
        int result = 0;
        int maxAmount = 0;
        //O(n)
        for(int pile : piles){
            result += pile;
            if(maxAmount < pile)  maxAmount = pile;
        }

        //mem O(maxN)
        int[] stones = new int[maxAmount + 1];
        for (int pile : piles) {
            stones[pile]++;
        }
        //O(maxN)
        for(int i = 0; i < k; i++){
            int stonesToRemove = maxAmount / 2;
            result -= stonesToRemove;
            stones[maxAmount - stonesToRemove]++;
            stones[maxAmount]--;

            while(stones[maxAmount] == 0){
                maxAmount--;
            }
        }
        return result;
    }

}
