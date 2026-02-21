package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value,
 * and the median is the mean of the two middle values.
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 * Implement the MedianFinder class:
 * MedianFinder() initializes the MedianFinder object.
 * void addNum(int num) adds the integer num from the data stream to the data structure.
 * double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

 * Example 1:
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]

 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 */
public class LT295_FindMedianFromDataStream {
    Queue<Integer> minHeap;
    Queue<Integer> maxHeap;

    public LT295_FindMedianFromDataStream() {
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    //O(logN)
    public void addNum(int num) {
        if (minHeap.isEmpty() || maxHeap.isEmpty()) {
            if (minHeap.isEmpty()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
            balanceHeaps();
            return;
        }

        if (num > minHeap.peek()) {
            minHeap.add(num);
        } else {
            maxHeap.add(num);
        }

        balanceHeaps();
    }

    //O(1)
    public double findMedian() {
        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        }

        if (minHeap.size() < maxHeap.size()) {
            return maxHeap.peek();
        }

        return (maxHeap.peek() + minHeap.peek()) / 2.0;

    }

    private void balanceHeaps() {
        if (Math.abs(minHeap.size() - maxHeap.size()) < 2) {
            return;
        }

        if (minHeap.size() > maxHeap.size()) {
            int num = minHeap.poll();
            maxHeap.add(num);
        } else {
            int num = maxHeap.poll();
            minHeap.add(num);
        }
    }
}

