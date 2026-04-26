package dp;

public class LT213_HouseRobberII {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1){
            return nums[0];
        }

        return Math.max(
                findMax(nums, 0, n - 1),
                findMax(nums, 1, n));
    }

    private int findMax(int[] nums, int left, int right){
        int a = 0;
        int b = nums[left];

        for(int i = left + 1; i < right; i++){
            int temp = Math.max(b, nums[i] + a);
            a = b;
            b = temp;
        }

        return b;
    }
}
