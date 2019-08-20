package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-08-17.
 * 312. 戳气球
 * <p>
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * <p>
 * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 * <p>
 * 求所能获得硬币的最大数量。
 * <p>
 * 说明:
 * <p>
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 * <p>
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _312_maxCoins {
    public static void main(String[] args) {
        _312_maxCoins maxCoins = new _312_maxCoins();
        System.out.println(maxCoins.maxCoins(new int[]{3, 1, 5, 8}));
    }

    /**
     * 解题思路：如何使最终结果最大化？动态规划保存结果
     * 1.将问题拆解成求i->j的最大值，最大的i=0,j=n
     * 2.从i->j中找一个k，拆分求解，i->k,k,k->j三个值之和的最大值
     * 3.i->k和k->j代表k的左边和右边全部戳破求解的最大值
     * 4.左右全部戳破后，k的值为num[i]*num[k]*num[j]
     * 5.动态转移方程：dp[i][j]=Math.max(dp[i][j],dp[i][k]+dp[k][j]+num[i]*num[k]*num[j]);
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        //dp[i][j]代表i->j的最大值
        int[][] dp = new int[nums.length + 2][nums.length + 2];
        //左右+1方便操作。 nums[-1] = nums[n] = 1
        int[] newNums = new int[nums.length + 2];
        for (int i = 1; i < newNums.length - 1; i++) {
            newNums[i] = nums[i - 1];
        }
        newNums[0] = 1;
        newNums[newNums.length - 1] = 1;
        //从2开始，保证最少3个
        for (int j = 2; j < newNums.length; j++) {
            //遍历所有的可能性，0-2...0-n,1-3...1-n,...
            for (int i = 0; i < newNums.length - j; i++) {
                for (int k = i + 1; k < i + j; k++) {
                    dp[i][i + j] = Math.max(dp[i][i + j], dp[i][k] + dp[k][i + j] + newNums[i] * newNums[k] * newNums[i + j]);
                }
            }
        }
        return dp[0][newNums.length - 1];
    }
}
