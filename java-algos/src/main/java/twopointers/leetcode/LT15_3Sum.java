package twopointers.leetcode;

import java.util.*;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k,
 * and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * Notice that the solution set must not contain duplicate triplets.
 */
public class LT15_3Sum {
    static void main() {
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    /**
     *
     * @param nums non-sorted arrays of integers
     * @return unique triplets of the elements of nums which sum = 0
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();
        //if target i  == target i + 1
        for (int i = nums.length - 1; i > 1; i--) {
            if (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                continue;
            }
            int target = nums[i];
            int l = 0;
            int r = i - 1;
            while (l < r) {
                int sum = nums[l] + nums[r] + target;
                if (sum > 0) {
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    result.add(List.of(nums[l], nums[r], target));
                    r--;
                    l++;
                }
            }
        }
        return new ArrayList<>(result);
    }
}
