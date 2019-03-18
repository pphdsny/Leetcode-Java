package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/9/29.
 * 307.区域和检索 - 数组可修改
 * <p>
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * <p>
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 * <p>
 * 示例:
 * <p>
 * Given nums = [1, 3, 5]
 * <p>
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 说明:
 * <p>
 * 数组仅可以在 update 函数下进行修改。
 * 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
 *
 * @see <a href="https://leetcode-cn.com/problems/range-sum-query-mutable/description/">range-sum-query-mutable</a>
 */
public class _307_NumArray {

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1, 3, 5});
        System.out.println(numArray.sumRange(0,2));
        numArray.update(1,2);
        System.out.println(numArray.sumRange(0,2));
    }

    /**
     * 最简单的实现
     * update复杂度O(1)
     * sum复杂度O(n)
     */
    private static class NumArray {
        int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
        }

        public void update(int i, int val) {
            nums[i] = val;
        }

        public int sumRange(int i, int j) {
            int total = 0;
            for (int k = i; k <= j; k++) {
                total += nums[k];
            }
            return total;
        }
    }
}
