package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-04-01.
 * 303. 区域和检索 - 数组不可变
 * <p>
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * <p>
 * 示例：
 * <p>
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 * <p>
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 说明:
 * <p>
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 *
 * @see <a href="https://leetcode-cn.com/problems/range-sum-query-immutable/">range-sum-query-immutable</a>
 */
public class _303_NumArray {
    public static void main(String[] args) {
        NumArray2 obj = new NumArray2(new int[]{-2, 0, 3, -5, 2, -1});
        int param_1 = obj.sumRange(0, 2);
        System.out.println(param_1);
    }

    public static class NumArray2 {

        private final int[] step;

        public NumArray2(int[] nums) {
            step = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                if (i == 0) {
                    step[i] = nums[i];
                } else {
                    step[i] = step[i - 1] + nums[i];
                }
            }
        }

        /**
         * 分治的算法
         *
         * @param i
         * @param j
         * @return
         */
        public int sumRange(int i, int j) {
            if (i == 0) {
                return step[j];
            } else {
                return step[j] - step[i - 1];
            }
        }
    }


    public static class NumArray {

        private final int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
        }

        /**
         * 执行用时 : 244 ms, 在Range Sum Query - Immutable的Java提交中击败了52.98% 的用户
         * 内存消耗 : 45.6 MB, 在Range Sum Query - Immutable的Java提交中击败了75.08% 的用户
         * 正常解法，耗时较高，由于sumRange会被频繁调用，所以可以用动态规划的方式，记录每一步到之前步数，再相减
         *
         * @param i
         * @param j
         * @return
         */
        public int sumRange(int i, int j) {
            int sum = 0;
            for (int k = i; k <= j; k++) {
                sum += nums[k];
            }
            return sum;
        }
    }
}
