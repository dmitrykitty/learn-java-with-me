package backtracking;

import java.lang.reflect.Array;
import java.util.*;

public class LT46_Permutations {
    /**
     * need the most abount of memory time O(n * n!)
     */
    public List<List<Integer>> permute1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack1(result, nums, new ArrayList<>(), new HashSet<>());

        return result;

    }

    private void backtrack1(List<List<Integer>> result, int[] nums, List<Integer> acc, Set<Integer> used) {
        if (acc.size() == nums.length) {
            result.add(new ArrayList<>(acc));
            return;
        }

        for (int num : nums) {
            if (!used.contains(num)) {
                acc.add(num);
                used.add(num);
                backtrack1(result, nums, acc, used);
                acc.removeLast();
                used.remove(num);
            }
        }
    }
    //================================================================================


    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack2(result, nums, new ArrayList<>(), new boolean[nums.length]);

        return result;

    }

    /**
     * @param used boolean array of used indexes
     */
    private void backtrack2(List<List<Integer>> result, int[] nums, List<Integer> acc, boolean[] used) {
        if (acc.size() == nums.length) {
            result.add(new ArrayList<>(acc));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                acc.add(nums[i]);
                used[i] = true;
                backtrack2(result, nums, acc, used);
                acc.removeLast();
                used[i] = false;
            }
        }
    }


    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack3(0, nums, result);

        return result;

    }

    private void backtrack3(int index, int[] nums, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> current = new ArrayList<>();
            for (int num : nums) current.add(num);
            result.add(current);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);      //(Make Choice)
            backtrack3(index + 1, nums, result);
            swap(nums, index, i);
        }
    }


    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
