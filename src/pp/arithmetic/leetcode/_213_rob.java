package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-05-09.
 * 213. 打家劫舍 II
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * @see <a href="https://leetcode-cn.com/problems/house-robber-ii/">house-robber-ii</a>
 */
public class _213_rob {
    public static void main(String[] args) {
        _213_rob rob = new _213_rob();
        System.out.println(rob.rob(new int[]{2, 3, 2}));
        System.out.println(rob.rob(new int[]{1, 2, 3, 1}));
    }

    /**
     * 解题思路：
     * 难点->最后一个既然是和第一个相连的，不然一个动态规划等式就能解决了
     * dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
     * 突破这个难点，使用两个规划数组，一个从0开始，n-1结束，另一个从1开始，n结束
     * 求出两个数组的最大值
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) return 0;
        if (length == 1) return nums[0];
        if (length == 2) return Math.max(nums[0], nums[1]);
        //0->n-1
        int[] dp = new int[length - 1];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        //1->n
        int[] dp1 = new int[length - 1];
        dp1[0] = nums[1];
        dp1[1] = Math.max(nums[1], nums[2]);
        for (int i = 3; i < length; i++) {
            dp1[i - 1] = Math.max(dp1[i - 3] + nums[i], dp1[i - 2]);
        }

        return Math.max(dp[length - 2], dp1[length - 2]);
    }
}
