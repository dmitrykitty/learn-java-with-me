package dp;

import java.util.Arrays;

public class LT198_HouseRobber {
    public int robMem(int[] nums) {
        int n = nums.length;
        int[] mem = new int[n];
        Arrays.fill(mem, -1);

        return inner(mem, nums, n - 1);
    }

    private int inner(int[] mem, int[] nums, int n){
        if(n < 0) return 0;
        if(n == 0) return mem[n] = nums[n];

        if(mem[n] != -1) return mem[n];

        int pick = inner(mem, nums, n - 2) + nums[n];
        int nopick = inner(mem, nums, n - 1);

        return mem[n] = Math.max(pick, nopick);
    }

    public int robTab(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];

        for(int i = 1; i < n; i++){
            int pick = nums[i];
            if(i > 1) pick += dp[i - 2];

            int nopick = dp[i - 1];

            dp[i] = Math.max(pick, nopick);
        }

        return dp[n - 1];
    }

    public int robOpt(int[] nums) {
        int n = nums.length;
        int prev2 = 0; //dp[i - 2]
        int prev1 = nums[0]; //dp[i - 1]

        for(int i = 1; i < n; i++){
            int pick = prev2 + nums[i];
            int noPick = prev1;

            int curr = Math.max(pick, noPick);

            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
