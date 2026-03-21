package dp;

import java.util.Arrays;

public class FrogJump {
    /*
                    n -> min (energy(n-1) + |h[n] - h[n-1]|, energy(n-2) + |h[n] - h[n-2]|)
     */
    public int frogJumpMem(int n, int[] heights) {
        int[] mem = new int[n];
        Arrays.fill(mem, -1);
        return inner(mem, heights, n - 1);

    }

    private int inner(int[] mem, int[] height, int n) {
        if (n == 0) return mem[n] = 0;


        if (mem[n] != -1) return mem[n];
        int left = inner(mem, height, n - 1) + Math.abs(height[n] - height[n - 1]);
        //to remove one step
        int right = Integer.MAX_VALUE;

        if(n > 1){
            right = inner(mem, height, n - 2) + Math.abs(height[n] - height[n - 2]);
        }
        return mem[n] = Math.min(left, right);

    }

    public int frogJumpTab(int n, int[] heights) {
        if(n <= 1) return 0;

        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = Math.abs(heights[1] - heights[0]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(
                    dp[i - 1] + Math.abs(heights[i] - heights[i - 1]),
                    dp[i - 2] + Math.abs(heights[i] - heights[i - 2]));
        }

        return dp[n - 1];
    }

    public int frogJumpOpt(int n, int[] heights) {
        if(n <= 1) return 0;

        int a = 0;
        int b = Math.abs(heights[1] - heights[0]);

        for (int i = 2; i < n; i++) {
            int temp = Math.min(
                    a + Math.abs(heights[i] - heights[i - 2]),
                    b + Math.abs(heights[i] - heights[i - 1]));
            a = b;
            b = temp;
        }
        return b;
    }

    public int frogJumpWithKSteps(int n, int[] heights, int k) {
        if(n <= 1) return 0;

        int[] dp =  new int[n];
        dp[0] = 0;

        for(int i = 1; i < Math.min(k, n - 1); i++) {
            dp[i] = Math.abs(heights[i] - heights[0]);
        }

        if(n - 1 <= k){
            return dp[n - 1];
        }

        for (int i = 2; i < n; i++) {
            int start = Math.max(0, i - k);
            int curMin = Integer.MAX_VALUE;

            for(int j = start; j < i; j++) {
                curMin = Math.min(curMin, dp[j] + Math.abs(heights[i] - heights[j]));
            }

            dp[i] = curMin;
        }

        return dp[n - 1];
    }

    public int frogJumpWithKStepsOpt(int n, int[] heights, int k) {
        if(n <= 1) return 0;

        int[] dp =  new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int start = Math.max(0, i - k);

            for(int j = start; j < i; j++) {
                int cost = dp[j] + Math.abs(heights[i] - heights[j]);
                dp[i] =  Math.min(cost, dp[i]);
            }
        }

        return dp[n - 1];
    }

    static void main() {
        FrogJump fj = new FrogJump();
        System.out.println(fj.frogJumpMem(4, new int[]{10, 20, 30, 10}));

        System.out.println(fj.frogJumpTab(4, new int[]{10, 20, 30, 10}));

        System.out.println(fj.frogJumpOpt(4, new int[]{10, 20, 30, 10}));
    }
}
