package slidingwindow;

/**
 * You are given an integer array nums consisting of n elements, and an integer k.
 * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
 * Any answer with a calculation error less than 10-5 will be accepted.
 * <p>
 * Example 1:
 * Input: nums = [1,12,-5,-6,50,3], k = 4
 * Output: 12.75000
 * Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
 * <p>
 * Example 2:
 * Input: nums = [5], k = 1
 * Output: 5.00000
 */
public class LT643_MaximumAverageSubarrayI {

    //    int begin = 0;
    //    int window_state
    //
    //    for(int end = 0; end < n; end++){
    //        end - begin + 1 //window size
    //        if(window condition){
    //            begin += 1; //shrink window
    //        }
    //    }

    //it worrrks, but we can simplify it
    public double findMaxAverage(int[] nums, int k) {
        int currentSum = 0;
        int n = nums.length;

        int begin = 0;
        int end = k - 1;

        for (int i = 0; i <= end; i++) {
            currentSum += nums[i];
        }

        int maxSum = currentSum;
        while (end < n - 1) {
            currentSum -= nums[begin];
            begin++;
            end++;
            currentSum += nums[end];

            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return (double) maxSum / k;
    }

    public double findMaxAverage2(int[] nums, int k) {
        int begin = 0;
        int winSum = 0;
        int n = nums.length;
        int result = Integer.MIN_VALUE;

        for (int end = 0; end < n; end++) {
            winSum += nums[end];
            if(end - begin + 1 == k){
                result = Math.max(result, winSum);
                winSum -= nums[begin];
                begin++;
            }
        }
        return (double) result / k;
    }
}

