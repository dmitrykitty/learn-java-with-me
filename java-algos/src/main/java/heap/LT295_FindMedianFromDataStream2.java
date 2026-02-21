package heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
/**
 * Let's put new elements only to the maxHeap. So if max_heap.size() != max_heap.size() -> median inside max_heap
 * If size1 - size2 > 1, we move max element from max heap to min heap
 * How to balance it?
 * To keep only smaller elements inside max heap we add firtly each element to the min heap, and later max heap.add(min heap.pop())
 * [5, 2, 3, 1, 4]

 * min heap <- 5
 * max heap <- min heap.pop()
 * max heap  |  min heap
 *        5  |

 * min heap <- 2
 * max heap <- min heap.pop()
 * max heap  |  min heap  (size dif > 1)
 *     5, 2  |
 * min heap <- max heap.pop()
 * max heap  |  min heap
 *        2  |  5

 * min heap <- 3
 * max heap <- min heap.pop()
 * max heap  |  min heap
 *     2, 3  |  5

 * min heap <- 1
 * max heap <- min heap.pop()
 * max heap  |  min heap
 *  1, 2, 3  |  5 (size dif > 1)
 * min heap <- max heap.pop()
 * max heap  |  min heap
 *     1, 2  |  3, 5

 * min heap <- 4
 * max heap <- min heap.pop()
 * max heap  |  min heap
 *  1, 2, 3  |  4, 5
 */
public class LT295_FindMedianFromDataStream2 {
    Queue<Integer> minHeap;
    Queue<Integer> maxHeap;

    public LT295_FindMedianFromDataStream2() {
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        minHeap.add(num);
        maxHeap.add(minHeap.poll());

        if(maxHeap.size() - minHeap.size() > 1) {
            minHeap.add(maxHeap.poll());
        }
    }

    public double findMedian() {
        if(maxHeap.size() != minHeap.size()) {
            return maxHeap.peek();
        }

        return (minHeap.peek() +  maxHeap.peek()) / 2.0;
    }
}
