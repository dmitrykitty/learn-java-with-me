package heap;

import java.util.*;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements.
 * You may return the answer in any order.

 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]

 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]

 * Example 3:
 * Input: nums = [1,2,1,2,1,2,3,1,3,2], k = 2
 * Output: [1,2]
 */
public class LT347_TopKFrequentElements {
    private static record Node(int val, int freq){}

    public int[] topKFrequent(int[] nums, int k) {
        Queue<Node> minHeap = new PriorityQueue<>(Comparator.comparing(Node::freq));
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int num : nums){
            freqMap.merge(num, 1, Integer::sum);
        }

        for(Map.Entry<Integer, Integer> entry: freqMap.entrySet()) {
            minHeap.add(new Node(entry.getKey(), entry.getValue()));
            if(minHeap.size() > k){
                minHeap.poll();
            }
        }
        int[] result = new int[k];
        for(int i = 0; i < k; i++){
            result[i] = minHeap.poll().val;
        }
        return result;
    }


    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.merge(num, 1, Integer::sum);
        }
        Queue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(freqMap::get));

        for (int num : freqMap.keySet()) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.stream().mapToInt(Integer::intValue).toArray();
    }
}
