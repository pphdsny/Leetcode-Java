package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-03-28.
 * 239. 滑动窗口最大值
 * <p>
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口最大值。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------             -----
 * [1  3  -1] -3  5  3  6  7     3
 * 1 [3  -1  -3] 5  3  6  7      3
 * 1  3 [-1  -3  5] 3  6  7      5
 * 1  3  -1 [-3  5  3] 6  7      5
 * 1  3  -1  -3 [5  3  6] 7      6
 * 1  3  -1  -3  5 [3  6  7]     7
 * 注意：
 * <p>
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 输入数组的大小，且输入数组不为空。
 * <p>
 * 进阶：
 * <p>
 * 你能在线性时间复杂度内解决此题吗？
 *
 * @see <a href="https://leetcode-cn.com/problems/sliding-window-maximum/">sliding-window-maximum</a>
 */
public class _239_maxSlidingWindow {
    public static void main(String[] args) {
        _239_maxSlidingWindow window = new _239_maxSlidingWindow();
        Util.printArray(window.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3));
    }

    /**
     * 线性时间 = O(n)
     * 最简单的方式是每移动一次，重新计算一次K中的最大值，最好复杂度O(n)，最坏复杂度在O(n^2)
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0)
            return new int[]{};
        int[] retMax = new int[nums.length - k + 1];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - k + 1; i++) {
            if (i == 0) {
                max = getMax(nums, i, i + k);
            } else {
                if (nums[i + k - 1] > max) {
                    max = nums[i + k - 1];
                } else if (nums[i - 1] == max) {
                    max = getMax(nums, i, i + k);
                }
            }
            retMax[i] = max;
        }
        return retMax;
    }

    private int getMax(int[] nums, int li, int ri) {
        int max = Integer.MIN_VALUE;
        for (int i = li; i < ri; i++) {
            max = nums[i] > max ? nums[i] : max;
        }
        return max;
    }
}
