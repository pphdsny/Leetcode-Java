package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-03-27.
 * 485. 最大连续1的个数
 * <p>
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * 注意：
 * <p>
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 *
 * @see <a href="https://leetcode-cn.com/problems/max-consecutive-ones/">max-consecutive-ones</a>
 */
public class _485_findMaxConsecutiveOnes {

    public static void main(String[] args) {
        _485_findMaxConsecutiveOnes ones = new _485_findMaxConsecutiveOnes();
        System.out.println(ones.findMaxConsecutiveOnes(new int[]{1, 1, 0, 1, 1, 1}));
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int itemCount = 0;
        for (int ri = 0; ri < nums.length; ri++) {
            if (nums[ri] == 1) {
                itemCount++;
            } else {
                max = Math.max(max, itemCount);
                itemCount = 0;
            }
        }
        max = Math.max(max, itemCount);

        return max;
    }
}
