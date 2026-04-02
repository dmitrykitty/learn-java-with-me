package arrays;

public class LT121_BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        //O(n)
        //O(1)
        int maxProfit = 0;
        int bestBuyPrice = Integer.MAX_VALUE;
        int n = prices.length;

        for(int i = 0; i < n; i++){
            if(bestBuyPrice > prices[i]){
                bestBuyPrice = prices[i];
            }else {
                maxProfit = Math.max(prices[i] - bestBuyPrice, maxProfit);
            }

        }
        return maxProfit;
    }
}
// bp = 1
//  |
// [7,1,5,3,6,4]

// prof = 0

