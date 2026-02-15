package intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of meeting time intervals where intervals[i] = [start, end].
 * Determine if person could attend all meetings
 */
public class LT252_MeetingRoom {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        for(int i = 0; i < intervals.length - 1; i++){
            int endFirst = intervals[i][1];
            int startSecond = intervals[i + 1][0];

            if(startSecond < endFirst){
                return false;
            }
        }
        return true;
    }
}
