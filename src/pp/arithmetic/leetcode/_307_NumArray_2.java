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
public class _307_NumArray_2 {

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1, 3, 5});
        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }

    /**
     * 换一种实现，是复杂度能到O(logn)-->线段树
     * update复杂度O(logn)
     * sum复杂度O(logn)
     */
    private static class NumArray {
        int[] nums;
        int[] result;

        public NumArray(int[] nums) {
            this.nums = nums;
            result = new int[nums.length * 4];
            buildSegmentTree(nums, result, 0, 0, nums.length - 1);
        }


        private void buildSegmentTree(int[] nums,
                                      int[] result,
                                      int position,
                                      int left,
                                      int right) {
            if (left == right) {
                result[position] = nums[left];
                return;
            }
            int mid = (left + right) / 2;
            buildSegmentTree(nums, result, position * 2 + 1, left, mid);
            buildSegmentTree(nums, result, position * 2 + 2, mid + 1, right);
            result[position] = result[position * 2 + 1] + result[position * 2 + 2];
        }

        public void update(int i, int val) {
            updateSegmentTree(result, 0, 0, nums.length - 1, i, val);
        }

        private void updateSegmentTree(int[] result,
                                       int pos,
                                       int left,
                                       int right,
                                       int index,
                                       int newValue) {
            if (left == right && left == index) {
                result[pos] = newValue;
                return;
            }
            int mid = (left + right) / 2;
            if (index <= mid) {
                updateSegmentTree(result, pos * 2 + 1, left, mid, index, newValue);
            } else {
                updateSegmentTree(result, pos * 2 + 2, mid + 1, right, index, newValue);
            }
            result[pos] = result[pos * 2 + 1] + result[pos * 2 + 2];
        }

        public int sumRange(int i, int j) {
            return sumSegmentTree(result, 0, 0, nums.length - 1, i, j);
        }

        private int sumSegmentTree(int[] result,
                                   int pos,
                                   int left,
                                   int right,
                                   int pleft,
                                   int pright) {
            if (left > pright || right < pleft) {
                return 0;
            }
            if (pleft <= left && pright >= right) {
                return result[pos];
            }
            int mid = (left + right) / 2;
            return sumSegmentTree(result, pos * 2 + 1, left, mid, pleft, pright) +
                    sumSegmentTree(result, pos * 2 + 2, mid + 1, right, pleft, pright);
        }
    }
}
