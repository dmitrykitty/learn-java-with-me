package backtracking;

import java.util.ArrayList;
import java.util.List;

public class LT216_CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> acc = new ArrayList<>();
        if(n < k) return result;

        backtrack(result, acc, k, n, 1);
        return result;

    }

    private void backtrack(List<List<Integer>> result, List<Integer> acc, int k, int target, int prev){
        if (acc.size() == k) {
            if (target == 0) {
                result.add(new ArrayList<>(acc));
            }
            return;
        }

        int diff = Math.min(target, 9);
        for(int i = prev; i <= diff; i++){
            acc.add(i);
            backtrack(result, acc, k, target - i, i + 1);
            acc.removeLast();
        }
    }

    static void main() {
        LT216_CombinationSumIII c = new LT216_CombinationSumIII();
        List<List<Integer>> result = c.combinationSum3(2, 18);
    }
}
