package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2018/9/24.
 * 53.最大子序和
 * <p>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * @see <a href="https://leetcode-cn.com/problems/maximum-subarray/description/">maximum-subarray</a>
 */
public class _53_maxSubArray {
    public static void main(String[] args) {
        int i = maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(i);
        int i1 = maxSubArray(new int[]{-2, 1});
        System.out.println(i1);
        Util.printDivideLine();
        //更优解法
        int i2 = maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(i2);
    }

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int[] dp2 = new int[nums.length];
        dp[0] = nums[0];
        dp2[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(Math.max(dp2[i - 1] + nums[i], dp[i - 1]), nums[i]);
            dp2[i] = Math.max(dp2[i - 1] + nums[i], nums[i]);
        }
        return dp[nums.length - 1];
    }

    /**
     * 时间复杂度一样，空间复杂度O(1)
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {

        int sum = Integer.MIN_VALUE;;

        int total_sum = 0;
        int i = 0;
        while(i < nums.length){
            total_sum += nums[i];
            sum = Math.max(sum, total_sum);
            if(total_sum < 0){
                total_sum = 0 ;
            }

            i++;
        }

        return sum;
    }
}
