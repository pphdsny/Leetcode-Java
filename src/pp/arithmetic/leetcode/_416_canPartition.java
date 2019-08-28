package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-08-28.
 * 416. 分割等和子集
 * <p>
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 注意:
 * <p>
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * <p>
 * 输入: [1, 5, 11, 5]
 * <p>
 * 输出: true
 * <p>
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *  
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1, 2, 3, 5]
 * <p>
 * 输出: false
 * <p>
 * 解释: 数组不能分割成两个元素和相等的子集.
 *  
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _416_canPartition {

    public static void main(String[] args) {
        _416_canPartition canPartition = new _416_canPartition();
        System.out.println(canPartition.canPartition(new int[]{1, 5, 11, 5}));
        System.out.println(canPartition.canPartition(new int[]{1, 3, 2, 4, 4,1}));
    }

    /**
     * 解题思路：
     * 1、对数组进行求和，如总和为奇数则不可能存在相等的两个集合
     * 2、找到求和为总和一半的集合，则满足拆分
     * 3、对于已存在得数组中求出满足条件的总数，可以将问题拆解成0-1背包问题，转换为动态规划求解
     * 4、对于dp[i][j]代表，0-i中找出总和为j的集合，那么对于nums[i]来说可以选择（nums[i]<j），也可以不选
     * 5、动态转移方程：dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        //1
        if (sum % 2 != 0) {
            return false;
        }
        //2
        sum = sum / 2;
        boolean[][] dp = new boolean[nums.length][sum + 1];
        //初始化，减少dp转换时候的判断
        for (int i = 0; i <= sum; i++) {
            if (nums[0] == i) {
                dp[0][i] = true;
            }
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= sum; j++) {
                if (nums[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[nums.length - 1][sum];
    }

    /**
     * 优化：将二维降为一维，利用之前的计算判断
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int size = nums.length;

        int s = 0;
        for (int num : nums) {
            s += num;
        }
        if ((s & 1) == 1) {
            return false;
        }

        int target = s / 2;

        // 从第 2 行以后，当前行的结果参考了上一行的结果，因此使用一维数组定义状态就可以了
        boolean[] dp = new boolean[target + 1];
        // 先写第 1 行，看看第 1 个数是不是能够刚好填满容量为 target
        for (int j = 1; j < target + 1; j++) {
            if (nums[0] == j) {
                dp[j] = true;
                // 如果等于，后面就不用做判断了，因为 j 会越来越大，肯定不等于 nums[0]
                break;
            }
        }
        // 注意：因为后面的参考了前面的，我们从后向前填写
        for (int i = 1; i < size; i++) {
            // 后面的容量越来越小，因此没有必要再判断了，退出当前循环
            for (int j = target; j >= 0 && j >= nums[i]; j--) {
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
