package slidingwindow;

/**
 * Given a binary array nums and an integer k,
 * return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

 * Example 1:
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

 * Example 2:
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 */
public class LT1004_MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int begin = 0;

        int result = Integer.MIN_VALUE;
        int windState = 0; //Amount of zeros

        for(int end = 0; end < n; end++){
            //step 1: move end and check if nums[end] == 0 -> increment amount of zeros inside window
            if(nums[end] == 0){
                windState++;
            }
            //step 2: if amount of zeroz > k -> shrink window by moving begin.
            // If nums[begin] == 0 -> decriment amount of zeros
            while(windState > k){
                if(nums[begin] == 0){
                    windState--;
                }
                begin++;
            }
            //step 3: check if current length is max
            result = Math.max(result, end - begin + 1);
        }
        return result;
    }
}
