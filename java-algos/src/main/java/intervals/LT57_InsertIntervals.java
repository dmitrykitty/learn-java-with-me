package intervals;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi]
 * represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals
 * still does not have any overlapping intervals (merge overlapping intervals if necessary).

 * Return intervals after the insertion.
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.

 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]

 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class LT57_InsertIntervals {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> merged = new ArrayList<>();

        int startNew = newInterval[0];
        boolean newAdded = false;

        if(intervals.length == 0 || startNew <= intervals[0][0]){
            merged.add(newInterval);
            newAdded = true;
        } else {
            merged.add(intervals[0]);
        }


        for(int i = 1; i < intervals.length; i++){
            int[] first = merged.getLast();
            int[] second;

            if(!newAdded && newInterval[0] <= intervals[i][0]){
                second = newInterval;
                i--;
                newAdded = true;
            } else {
                second = intervals[i];
            }

            if(second[0] <= first[1]){
                first[1] = Math.max(first[1], second[1]);
            } else {
                merged.add(second);
            }
        }

        if(!newAdded){
            int[] first = merged.getLast();
            if(newInterval[0] <= first[1]){
                first[1] = Math.max(first[1], newInterval[1]);
            }
            else {
                merged.add(newInterval);
            }
        }

        return merged.toArray(new int[merged.size()][]);

    }

    public int[][] insertOptimized(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();

        int i = 0;
        int n = intervals.length;
        while(i < n &&  intervals[i][0] <= newInterval[0]){
            result.add(intervals[i++]);
        }

        int[] last;
        if(result.isEmpty() || newInterval[0] > result.getLast()[1]){
            result.add(newInterval);
        } else {
            last = result.getLast();
            last[1] = Math.max(last[1], newInterval[1]);
        }

        last = result.getLast();
        while(i < n && intervals[i][0] <= last[1]){
            last[1] = Math.max(last[1], intervals[i++][1]);
        }

        while(i < n){
            result.add(intervals[i++]);
        }

        return result.toArray(new int[result.size()][]);
    }
}
