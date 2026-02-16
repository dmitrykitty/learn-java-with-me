package backtracking;

import java.util.ArrayList;
import java.util.List;

public class LT77_Combinations {
    public List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = i + 1;
        }

        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<Integer>(), nums, k, 0);

        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> acc, int[] nums, int k, int currentIndex){
        if(acc.size() == k){
            result.add(new ArrayList<>(acc));
            return;
        }

        for(int i = currentIndex; i < nums.length; i++){
            acc.add(nums[i]);
            backtrack(result, acc, nums, k, i + 1);
            acc.removeLast();
        }
    }

    //=============================================================================

    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack2(result, new ArrayList<Integer>(), 1, k, n);
        return result;
    }

    private void backtrack2(List<List<Integer>> result, List<Integer> acc, int start, int k, int n){
        if(acc.size() == k){
            result.add(new ArrayList<>(acc));
            return;
        }

        for(int i = start; i <= n; i++){
            //available elements [i, i+1, i+2, ... , n] -> n - i + 1
            //needed to add k - acc.size()
            //if we don't have enought available elements -> skip
            //continue only when n - i + 1 >= k - acc.size()
            if(n >= k - acc.size() + i - 1){
                acc.add(i);
                backtrack2(result, acc, i + 1, k, n);
                acc.removeLast();
            }
        }
    }
}
