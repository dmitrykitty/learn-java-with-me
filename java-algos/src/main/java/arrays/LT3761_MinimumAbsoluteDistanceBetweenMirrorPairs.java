package arrays;

import java.util.HashMap;
import java.util.Map;

public class LT3761_MinimumAbsoluteDistanceBetweenMirrorPairs {
    public int minMirrorPairDistance(int[] nums) {
        //num : index
        Map<Integer, Integer> map = new HashMap<>();
        int minDif = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++){
            int cur = nums[i];
            if(map.containsKey(cur)){
                minDif = Math.min(minDif, i - map.get(cur));
            }
            int reversed = reverse(cur);
            map.put(reversed, i);
        }
        return minDif == Integer.MAX_VALUE ? -1 : minDif;
    }

    private int reverse(int num){

        int res = 0;
        while(num > 0){
            res *= 10;
            res += num % 10;
            num /= 10;
        }
        return res;
    }
}
