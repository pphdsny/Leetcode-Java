package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2018/9/25.
 * 300.最长上升子序列
 * <p>
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n^2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(nlogn) 吗?
 *
 * @see <a href="https://leetcode-cn.com/problems/longest-increasing-subsequence/description/">longest-increasing-subsequence</a>
 */
public class _300_lengthOfLIS {
    public static void main(String[] args) {
        int i = lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
        System.out.println(i);
        int i1 = lengthOfLIS(new int[]{2, 2});
        System.out.println(i1);
        Util.printDivideLine();
        int i2 = lengthOfLIS2(new int[]{10, 9, 2, 5, 3, 7, 101, 18});
        System.out.println(i2);
        int i3 = lengthOfLIS2(new int[]{1, 3, 4, 2, 3});
        System.out.println(i3);
    }

    /**
     * 使用DP，定义dp[]为以i结尾的最长子序列
     * dp[i]=dp[0...i-1]+1
     * 时间复杂度O(n^2)
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLength = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > maxLength) {
                maxLength = dp[i];
            }

        }
        return maxLength;
    }

    /**
     * 通过一个栈，维护一个递增数组，数组中保存尽可能小的数字，通过二分查找替换更小的
     * 时间复杂度O(nlogn)
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] lens = new int[nums.length];
        int max = 1;
        lens[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > lens[max - 1]) {
                lens[max++] = nums[i];
            } else {
                int left = 0;
                int right = max - 1;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (lens[mid] >= nums[i]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                lens[left] = nums[i];
            }
        }
        return max;
    }
}
