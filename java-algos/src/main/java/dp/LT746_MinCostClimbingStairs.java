package dp;

import java.util.Arrays;

public class LT746_MinCostClimbingStairs {
    public int minCostClimbingStairsMem(int[] cost) {
        int n = cost.length;
        int[] mem = new int[n + 1];
        Arrays.fill(mem, -1);

        return inner(mem, cost, n);
    }

    private int inner(int[] mem, int[] cost, int n){
        if(n <= 1) return mem[n] = 0;

        if(mem[n] != -1) return mem[n];

        return mem[n] = Math.min(inner(mem, cost, n - 1) + cost[n - 1], inner(mem, cost, n - 2) + cost[n - 2]);
    }

    public int minCostClimbingStairsTab(int[] cost) {
        int n = cost.length;
        if(n <= 1) return 0;

        int[] mem = new int[n + 1];
        mem[0] = 0;
        mem[1] = 0;

        for(int i = 2; i <= n; i++){
            mem[i] = Math.min(mem[i - 1] + cost[i - 1], mem[i - 2] + cost[i - 2]);
        }
        return mem[n];
    }

    public int minCostClimbingStairsOpt(int[] cost) {
        int n = cost.length;
        if(n <= 1) return 0;

        int a = 0;
        int b = 0;

        for(int i = 2; i <= n; i++){
            int temp = Math.min(b + cost[i - 1], a + cost[i - 2]);
            a = b;
            b = temp;
        }
        return b;
    }
}
