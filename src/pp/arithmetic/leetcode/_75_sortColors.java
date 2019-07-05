package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-07-04.
 * 75. 颜色分类
 * <p>
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _75_sortColors {

    public static void main(String[] args) {
        _75_sortColors sortColors = new _75_sortColors();
        int[] nums = {2, 1, 2};
        sortColors.sortColors(nums);
        Util.printArray(nums);
    }

    /**
     * 解题思路：
     * 原地排序==>借助常量级的空间做中转，可以参考快排实现？
     * 但是题目所需：你能想出一个仅使用常数空间的一趟扫描算法吗？（也就是说时间复杂度最好是O(n)）
     * 我们用三个指针（p0, p2 和curr）来分别追踪0的最右边界，2的最左边界和当前考虑的元素
     * 此问题称为"荷兰国旗问题"
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        // 对于所有 idx < i : nums[idx < i] = 0
        // j是当前考虑元素的下标
        int p0 = 0, curr = 0;
        // 对于所有 idx > k : nums[idx > k] = 2
        int p2 = nums.length - 1;

        int tmp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                // 交换第 p0个和第curr个元素
                // i++，j++
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            } else if (nums[curr] == 2) {
                // 交换第k个和第curr个元素
                // p2--
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            } else {
                curr++;
            }
        }
    }
}
