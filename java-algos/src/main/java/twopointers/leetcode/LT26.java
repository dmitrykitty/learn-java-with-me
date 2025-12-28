package twopointers.leetcode;

/**
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that
 * each unique element appears only once. The relative order of the elements should be kept the same.
 * Consider the number of unique elements in nums to be k. After removing duplicates,
 * return the number of unique elements k.
 * The first k elements of nums should contain the unique numbers in sorted order.
 * The remaining elements beyond index k - 1 can be ignored.
 */
public class LT26 {
    static void main() {
        int i = removeDuplicates(new int[]{1, 1, 2, 2, 3});
    }

    /**
     * 1st attepmt
     * implementation of the task based on "two pointers solution"
     *
     * @param nums the array of the numbers with duplicates
     * @return tne new length of nums-array where no duplicates located
     */
    public static int removeDuplicates(int[] nums) {
        int result = 0;
        int n = nums.length;
        int l = 0;
        int r = 0;
        while (r < n) {
            nums[result] = nums[r];
            l = r;
            result++;
            while (r < n && nums[l] == nums[r]) {
                r++;
            }

        }
        return result;
    }

    /**
     * 2nd attepmt
     * implementation of the task based on "two pointers solution"
     *
     * @param nums the array of the numbers with duplicates
     * @return tne new length of nums-array where no duplicates located
     */
    public int removeDuplicates2(int[] nums) {
        int n = nums.length;
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != nums[k]) {
                k++;
                nums[k] = nums[i];
            }
        }
        return ++k;
    }
}
