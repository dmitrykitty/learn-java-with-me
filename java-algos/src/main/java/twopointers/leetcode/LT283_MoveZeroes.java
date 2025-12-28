package twopointers.leetcode;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]

 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 */
public class LT283_MoveZeroes {
    static void main() {
        moveZeroes(new int[]{1, 0, 1});
    }

    /**
     * solution based on two pointers, but too complicated
     * @param nums array of numbers
     */
    private static void moveZeroes(int[] nums) {
        int n = nums.length;
        if (n == 1) return;

        int read = 0;
        int write = 0;

        while (read < n && write < n) {
            while (write < n && nums[write] != 0) {
                write++;
            }
            while (read < n && nums[read] == 0) {
                read++;
            }

            if (read < n && write < read) {
                nums[write] = nums[read];
                nums[read] = 0;
                write++;
            }
            read++;

        }
        //     *
        // [1, 0]
        //        *
    }

    //we swap each time when nums[i] != 0 even if nums[i] == nums[w]
    private static void moveZeroes2(int[] nums) {
        int n = nums.length;
        int w = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[w];
                nums[w] = temp;

                w++;
            }
        }
    }
}
