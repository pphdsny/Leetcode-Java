package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.Arrays;

/**
 * Created by wangpeng on 2019-03-19.
 * 1. 两数之和
 * <p>
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @see <a href="https://leetcode-cn.com/problems/two-sum/">two-sum</a>
 */
public class _1_twoSum {

    public static void main(String[] args) {
        _1_twoSum twoSum = new _1_twoSum();
        Util.printArray(twoSum.twoSum(new int[]{3, 2, 4}, 6));
        Util.printArray(twoSum.twoSum2(new int[]{3, 2, 4}, 6));
    }

    /**
     * 解法二：
     * 针对 {@link _1_twoSum#twoSum(int[], int)}的优化
     * 先排序，再双指针同时向里缩进，求和==target
     * <p>
     * 执行用时O(n) : 7 ms, 在Two Sum的Java提交中击败了89.46% 的用户
     * 内存消耗 : 38.1 MB, 在Two Sum的Java提交中击败了0.99% 的用户
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        int[] copyNums = new int[nums.length];
        System.arraycopy(nums, 0, copyNums, 0, nums.length);
        Arrays.sort(nums);
        int sIndex = 0;
        int eIndex = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[sIndex] + nums[eIndex] == target) {
                result[0] = nums[sIndex];
                result[1] = nums[eIndex];
                break;
            } else if (nums[sIndex] + nums[eIndex] > target) {
                eIndex--;
            } else {
                sIndex++;
            }

        }

        boolean fGit = false;
        for (int i = 0; i < copyNums.length; i++) {
            if (!fGit && copyNums[i] == result[0]) {
                sIndex = i;
                fGit = true;
            } else if (copyNums[i] == result[1]) {
                eIndex = i;
            }
        }
        if (sIndex > eIndex) {
            result[0] = eIndex;
            result[1] = sIndex;
        } else {
            result[0] = sIndex;
            result[1] = eIndex;
        }

        return result;
    }

    /**
     * 最简单的实现方案，双重循环，可优化
     * <p>
     * 执行用时O(n^2) : 57 ms, 在Two Sum的Java提交中击败了26.43% 的用户
     * 内存消耗 : 40.9 MB, 在Two Sum的Java提交中击败了0.99% 的用户
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }
}
