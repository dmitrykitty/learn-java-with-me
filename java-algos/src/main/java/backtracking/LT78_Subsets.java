package backtracking;

import java.util.ArrayList;
import java.util.List;

public class LT78_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;

    }

    private void backtrack(List<List<Integer>> result, List<Integer> acc, int[] nums, int index){
        result.add(new ArrayList<>(acc));
        for(int i = index; i < nums.length; i++){
            acc.add(nums[i]);
            backtrack(result, acc, nums, i + 1);
            acc.removeLast();
        }
    }

    /**
     *bit mask solution
     */
    public List<List<Integer>> subsetsBinaryMask(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        int numberOfSets = 1 << n; //the same as 2 * 2 * 2 ... n times = 2^n

        for(int mask = 0; mask < numberOfSets; mask++){
            List<Integer> currentSubset = new ArrayList<>();

            for(int i = 0; i < n; i++){
                //if i-bit != 0 (1 & 0 -> 0, 1 & 1 -> 1)
                if((mask & (1 << i)) != 0){
                    currentSubset.add(nums[i]);
                }
            }
            result.add(currentSubset);
        }
        return result;
    }
}
