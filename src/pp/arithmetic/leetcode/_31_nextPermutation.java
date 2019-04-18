package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.Arrays;

/**
 * Created by wangpeng on 2019-04-18.
 * 31. 下一个排列
 * <p>
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须原地修改，只允许使用额外常数空间。
 * <p>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * @see <a href="https://leetcode-cn.com/problems/next-permutation/">next-permutation</a>
 */
public class _31_nextPermutation {

    public static void main(String[] args) {
        _31_nextPermutation permutation = new _31_nextPermutation();
        int[] nums = new int[]{1, 2, 3, 4, 7, 5, 6};
        Util.printArray(nums);
        permutation.nextPermutation(nums);
        Util.printArray(nums);
        permutation.nextPermutation(nums);
        Util.printArray(nums);
    }

    /**
     * 解题思路：
     * 必须原地修改，代表只能数组内替换，而不能借助新的数组
     * 如：1 2 4 5 3
     * 1、从尾部开始遍历，找到刚开始递减的数字 4
     * 2、从 4 后面找比4最小的数字 5
     * 3、将 4 和 5 替换
     * 4、后续数组升序排列 1 2 5 3 4
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int pNum = nums[nums.length - 1];
        //找到开始递减的index
        int cIndex = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int cNum = nums[i];
            if (cNum < pNum) {
                cIndex = i;
                break;
            }
            pNum = cNum;
        }
        if (cIndex == -1) {
            //没有找到，全数组重新升序排列
            Arrays.sort(nums);
            return;
        }
        //找满足条件的替换值
        int rIndex = cIndex + 1;
        for (int i = cIndex + 1; i < nums.length; i++) {
            if (nums[i] <= nums[cIndex]) {
                break;
            }
            rIndex = i;
        }
        //替换
        int temp = nums[cIndex];
        nums[cIndex] = nums[rIndex];
        nums[rIndex] = temp;
        //剩下的重新排序
        Arrays.sort(nums, cIndex + 1, nums.length);
    }
}
