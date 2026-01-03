package slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an integer array nums and an integer k,
 * return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 <p>
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 * <p>
 * Example 2:
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 * <p>
 * Example 3:
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 */
public class LT219_ContainsDuplicateII {


    //this solution beats only 10% so I decided to find another one using Set because we need to check only two numbers
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int begin = 0;
        Map<Integer, Integer> windState = new HashMap<>(); //amount of unique numbers inside the window

        for(int end = 0; end < nums.length; end++){
            windState.put(nums[end], windState.getOrDefault(nums[end], 0) + 1);
            if(end - begin > k){
                windState.put(nums[begin], windState.get(nums[begin]) - 1);
                if(windState.get(nums[begin]) == 0){
                    windState.remove(nums[begin]);
                }
                begin++;
            }

            if(windState.size() < end - begin + 1){
                return true;
            }
        }
        return false;
    }

    //this solution beats already 93%
    public boolean containsNearbyDuplicateWithSet(int[] nums, int k) {
        if(nums.length < 2 || k == 0) return false;

        int begin = 0;
        Set<Integer> windState = new HashSet<>(); //amount of unique numbers inside the window

        for(int end = 0; end < nums.length; end++){
            windState.add(nums[end]);
            if(end - begin > k){
                if(nums[end] != nums[begin]){
                    windState.remove(nums[begin]);
                }
                begin++;
            }
            if(windState.size() < end - begin + 1){
                return true;
            }
        }
        return false;
    }
}
