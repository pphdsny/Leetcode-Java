package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-08-02.
 * 238. 除自身以外数组的乘积
 * <p>
 * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _238_productExceptSelf {

    public static void main(String[] args) {
        _238_productExceptSelf productExceptSelf = new _238_productExceptSelf();
        Util.printArray(productExceptSelf.productExceptSelf(new int[]{0, 0}));
        Util.printArray(productExceptSelf.productExceptSelf(new int[]{1, 0}));
        Util.printArray(productExceptSelf.productExceptSelf(new int[]{0, 1, 2, 3, 4}));
    }

    /**
     * 解题思路：
     * 1.遍历数组，将所有的数进行乘积，得到最大的乘积
     * 2.再次遍历数组，将最大的乘积除以遍历item，得到除自身以外的乘积
     * <p>
     * 注意：
     * 1.如item为0，要跳过处理，不能乘和除
     * 2.如果数组中有0，则非0的数字结果都是0，如0的个数>1，则所有数字结果都是0
     * <p>
     * 执行用时 :4 ms, 在所有 Java 提交中击败了30.79%的用户
     * 内存消耗 :51.7 MB, 在所有 Java 提交中击败了22.40%的用户
     * <p>
     * 解题过程略复杂，他人优秀解法{@link _238_productExceptSelf#productExceptSelf2(int[])}
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] retNums = new int[nums.length];
        int max = 0;
        int zoreCount = 0;
        //1
        for (int i = 0; i < nums.length; i++) {
            int item = nums[i];
            if (item != 0) {
                if (max == 0) max = 1;
                max *= item;
            } else {
                zoreCount++;
            }
        }
        //2
        for (int i = 0; i < nums.length; i++) {
            int item = nums[i];
            if (item != 0) {
                if (zoreCount > 0) {
                    retNums[i] = 0;
                } else {
                    retNums[i] = max / item;
                }
            } else {
                if (zoreCount > 1) {
                    retNums[i] = 0;
                } else {
                    retNums[i] = max;
                }
            }
        }

        return retNums;
    }

    /**
     * 他人解题思路，借鉴下
     * 乘积 = 当前数左边的乘积 * 当前数右边的乘积
     * 有一些分治的思想
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int[] res = new int[nums.length];
        int k = 1;
        for (int i = 0; i < res.length; i++) {
            res[i] = k;
            k = k * nums[i]; // 此时数组存储的是除去当前元素左边的元素乘积
        }
        k = 1;
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] *= k; // k为该数右边的乘积。
            k *= nums[i]; // 此时数组等于左边的 * 该数右边的。
        }
        return res;
    }
}
