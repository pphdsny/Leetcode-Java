package pp.arithmetic.leetcode;

import java.util.Arrays;

/**
 * Created by wangpeng on 2019-04-09.
 * 16. 最接近的三数之和
 * <p>
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 *
 * @see <a href="https://leetcode-cn.com/problems/3sum-closest/">3sum-closest</a>
 */
public class _16_threeSumClosest {

    public static void main(String[] args) {
        _16_threeSumClosest threeSumClosest = new _16_threeSumClosest();
        System.out.println(threeSumClosest.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
    }

    /**
     * 解题思路：
     * 1、先排序，快排（O(nLogn)）
     * 2、随机取一个数i（第一个开始），头尾各取一个数开始遍历si=i+1,ei=n-1
     * 3、三数相加S1，S1-T1=D
     * 4、D==0则直接返回，D>0则ei--，D<0则si++
     * <p>
     * 最坏时间复杂度在O(n^2)
     * <p>
     * 执行用时 : 12 ms, 在3Sum Closest的Java提交中击败了98.06% 的用户
     * 内存消耗 : 36.1 MB, 在3Sum Closest的Java提交中击败了0.86% 的用户
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ret = 0;
        int minDiff = Integer.MAX_VALUE;
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            int si = i + 1, ei = len - 1;
            while (si < ei) {
                int sum = nums[i] + nums[si] + nums[ei];
                if (sum == target) return sum;
                int diff = Math.abs(sum - target);
                if (diff < minDiff) {
                    minDiff = diff;
                    ret = sum;
                }
                if (sum > target)
                    ei--;
                else
                    si++;
            }
        }

        return ret;
    }
}
