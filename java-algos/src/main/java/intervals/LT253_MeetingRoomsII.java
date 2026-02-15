package intervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an array of meeting time intervals internals where intervals[i] = [start_i, end_i].
 * Return the minimum number of conference rooms required.
 */
public class LT253_MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[0]));

        int rooms = 1;
        Queue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            int[] first = intervals[i - 1];
            int[] last = intervals[i];

            if (first[1] > last[0] && (minHeap.isEmpty() || minHeap.peek() > last[0])) {
                rooms++;
            } else {
                minHeap.poll();
            }

            minHeap.add(last[1]);
        }
        return rooms;
    }

    public int minMeetingRoomsOpt(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[0]));

        Queue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            int[] last = intervals[i];

            if (minHeap.peek() <= last[0]){
                minHeap.poll();
            }
            minHeap.add(last[1]);
        }

        return minHeap.size();
    }
}
