package prefixsum;

public class LT724_FindPivotIndex {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = nums[0];
        for(int i = 1; i < n; i++){
            prefix[i] = prefix[i - 1] + nums[i];
        }

        int leftSum = 0;
        int fullSum = prefix[n - 1];

        for(int i = 0; i < n; i++){
            if(i != 0){
                leftSum = prefix[i - 1];
            }
            if(leftSum == fullSum - prefix[i]){
                return i;
            }

        }
        return -1;
    }
}
