package intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LT52_MergeIntervals {
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> mergedIntervals = new ArrayList<>();
        mergedIntervals.add(intervals[0]);

        for(int i = 1; i < intervals.length; i++){
            int[] first = mergedIntervals.getLast();
            int[] second = intervals[i];

            if(first[1] >= second[0]){
                first[1] = Math.max(first[1], second[1]);
            } else {
                mergedIntervals.add(second);
            }
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }
}
