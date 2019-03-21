package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-03-20.
 * 167. 两数之和 II - 输入有序数组
 * <p>
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * <p>
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * <p>
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * @see <a href="https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/">two-sum-ii-input-array-is-sorted</a>
 */
public class _167_twoSum {

    public static void main(String[] args) {
        _167_twoSum twoSum = new _167_twoSum();
        Util.printArray(twoSum.twoSum(new int[]{2, 7, 11, 15}, 9));
    }

    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int startI = 0, endI = numbers.length - 1;
        while (startI < endI) {
            int add = numbers[startI] + numbers[endI];
            if (add == target) {
                result[0] = startI + 1;
                result[1] = endI + 1;
                break;
            } else if (add > target) {
                endI--;
            } else {
                startI++;
            }
        }
        return result;
    }
}
