package arrays;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class LT128_LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);

        int maxLength = 1;
        int curLength = 1;
        int prev = nums[0];

        for(int i = 1; i < nums.length; i++){
            if(nums[i] == prev) continue;

            if(nums[i] - prev == 1){
                curLength++;
            }else{
                maxLength = Math.max(curLength, maxLength);
                curLength = 1;
            }
            prev = nums[i];

        }
        return Math.max(maxLength, curLength);
    }
}
