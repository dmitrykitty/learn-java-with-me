package slidingwindow;

/**
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a subarray whose sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.

 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.

 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1

 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 */
public class LT209_MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int begin = 0;
        int windSum = 0;
        int n = nums.length;
        int result = Integer.MAX_VALUE;

        for(int end = 0; end < n; end++){
            windSum += nums[end];
            while(windSum >= target){
                int currentWindLength = end - begin + 1;
                result = Math.min(currentWindLength, result);
                windSum -= nums[begin];
                begin++;
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
