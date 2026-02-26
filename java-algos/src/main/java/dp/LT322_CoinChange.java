package dp;

public class LT322_CoinChange {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;

        int[] dp = new int[amount + 1];
        int max = amount + 1;

        for(int i = 0; i <= amount; i++){
            dp[i] = max;
        }

        dp[0] = 0;

        for(int i = 1; i <= amount; i++){
            int minValue = dp[i];

            for(int coin: coins){
                int prev = i - coin;
                if(prev >= 0){
                    minValue = Math.min(minValue, dp[prev] + 1);
                }
            }
            dp[i] = minValue;
        }

        return dp[amount] == max ? -1 : dp[amount];
    }
}
