package dp;

import java.util.Arrays;

public class LT70_ClimbingStairs {
    public int climbStairsTab(int n) {
        if(n == 1 || n == 0) return 1;
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public int climbStairsMem(int n) {
        int[] mem = new int[n + 1];
        Arrays.fill(mem, Integer.MAX_VALUE);

        return inner(mem, n);
    }

    private int inner(int[] mem, int n){
        if(n <= 1)return mem[n] = 1;

        if(mem[n] != Integer.MAX_VALUE){
            return mem[n];
        }

        return mem[n] = inner(mem, n - 1) + inner(mem, n - 2);
    }
}
