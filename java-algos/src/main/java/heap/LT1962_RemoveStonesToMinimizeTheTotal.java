package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

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
