package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-09-02.
 * 494. 目标和
 * <p>
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * <p>
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * 一共有5种方法让最终目标和为3。
 * 注意:
 * <p>
 * 数组的长度不会超过20，并且数组中的值全为正数。
 * 初始的数组的和不会超过1000。
 * 保证返回的最终结果为32位整数。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _494_findTargetSumWays {

    public static void main(String[] args) {
        _494_findTargetSumWays findTargetSumWays = new _494_findTargetSumWays();
        System.out.println(findTargetSumWays.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(findTargetSumWays.findTargetSumWays1(new int[]{1, 1, 1, 1, 1}, 3));
    }

    /**
     * 解题思路：
     * 暴力解法：每一位有两种操作（+、-），深度遍历整个数组，得到满足条件的个数，时间复杂度O(2^n) {@link _494_findTargetSumWays#findTargetSumWays1(int[], int)}
     *
     * 优化解法：0-1背包问题改版
     * 1、求出背包中要取的总数和:
     *  假设取正数和P，负数和N，P-N = target
     *  两边同时加上 P+N ==> P+N+P-N = target + P+N（其中P+N=nums的总和）
     *  2*P = target+sum ==> P = (target+sum)/2（P就是要取得总和）
     * 2、dp[i]代表合成i有多少种方法，动态转移方程dp[i] += dp[i - num];
     *  dp[i]的总和 == 除了i以外所有可能性总和，举例：[n1,n2,n3]，dp[i]=dp[i-n1]+dp[i-n2]+dp[i-n3]
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;

        for(int num : nums) {
            sum += num;
        }

        if(Math.abs(target) > sum || (sum + target) % 2 != 0) {
            return 0;
        }

        //1
        int P = (sum + target) / 2;
        int[] dp = new int[P + 1];
        dp[0] = 1;

        //2、
        for (int num : nums) {
            for (int i = P; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }

        return dp[P];
    }

    private int result = 0;

    /**
     * 暴力解法：
     * 执行用时 :673 ms, 在所有 Java 提交中击败了19.31%的用户
     * 内存消耗 :35.2 MB, 在所有 Java 提交中击败了81.70%的用户
     *
     * @param nums
     * @param S
     * @return
     */
    private int findTargetSumWays1(int[] nums, int S) {
        dfs(nums, S, 0, 0);
        return result;
    }

    private void dfs(int[] nums, int S, int index, int cS) {
        if (cS == S && index == nums.length) {
            result++;
            return;
        }
        if (index >= nums.length) {
            return;
        }
        //+
        dfs(nums, S, index + 1, cS + nums[index]);
        //-
        dfs(nums, S, index + 1, cS - nums[index]);
    }
}
