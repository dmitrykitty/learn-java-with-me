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
        if (n == 1) return mem[n] = Math.abs(height[n] - height[n - 1]);

        if (mem[n] != -1) return mem[n];
        return mem[n] = Math.min(
                inner(mem, height, n - 1) + Math.abs(height[n] - height[n - 1]),
                inner(mem, height, n - 2) + Math.abs(height[n] - height[n - 2]));

    }

    public int frogJumpTab(int n, int[] heights) {
        if(n <= 1) return 0;
        if(n == 2) return Math.abs(heights[1] - heights[0]);

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

    static void main() {
        FrogJump fj = new FrogJump();
        System.out.println(fj.frogJumpMem(4, new int[]{10, 20, 30, 10}));

        System.out.println(fj.frogJumpTab(4, new int[]{10, 20, 30, 10}));

        System.out.println(fj.frogJumpOpt(4, new int[]{10, 20, 30, 10}));
    }
}
