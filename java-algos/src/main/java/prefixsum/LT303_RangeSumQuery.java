package prefixsum;

public class LT303_RangeSumQuery {
    private final int[] prefix;

    public LT303_RangeSumQuery(int[] nums) {
        prefix = new int[nums.length];
        prefix[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            prefix[i] += prefix[i - 1] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        int rightSum = prefix[right];
        int leftSum = left > 0 ? prefix[left - 1] : 0;
        return rightSum - leftSum;
    }
}
