package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-06-20.
 * 41. 缺失的第一个正数
 * <p>
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * <p>
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 说明:
 * <p>
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 */
public class _41_firstMissingPositive {
    public static void main(String[] args) {
        _41_firstMissingPositive firstMissingPositive = new _41_firstMissingPositive();
        System.out.println(firstMissingPositive.firstMissingPositive(new int[]{3, 4, -1, 1}));
    }

    /**
     * 解题思路：
     * 题目要求算法时间复杂度在O(n)，也就是说不能排序（排序最好的平均复杂度也在O(nLogn)）
     * O(n)的复杂度只允许一次循环或者常数级的循环，并且只能使用常数级别的空间==>题目的难点
     * <p>
     * 官方的解法：
     * 1、检查 1 是否存在于数组中。如果没有，则已经完成，1 即为答案。
     * 2、如果 nums = [1]，答案即为 2 。
     * 3、将负数，零，和大于 n 的数替换为 1 。
     * 4、遍历数组。当读到数字 a 时，替换第 a 个元素的符号。 注意重复元素：只能改变一次符号。由于没有下标 n ，使用下标 0 的元素保存是否存在数字 n。
     * 5、再次遍历数组。返回第一个正数元素的下标。
     * 6、如果 nums[0] > 0，则返回 n 。
     * 7、如果之前的步骤中没有发现 nums 中有正数元素，则返回n + 1。
     * 详细说明：https://leetcode-cn.com/problems/first-missing-positive/solution/que-shi-de-di-yi-ge-zheng-shu-by-leetcode/
     * <p>
     * 时间复杂度： O(N) 由于所有的操作一共只会遍历长度为 N 的数组 4 次。
     * 空间复杂度： O(1) 由于只使用了常数的空间，原地遍历
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // 基本情况
        int contains = 0;
        for (int i = 0; i < n; i++)
            if (nums[i] == 1) {
                contains++;
                break;
            }

        //不存在1，则最小正整数就是1
        if (contains == 0)
            return 1;

        // nums = [1]
        if (n == 1)
            return 2;

        // 用 1 替换负数，0，
        // 和大于 n 的数
        // 在转换以后，nums 只会包含
        // 正数
        for (int i = 0; i < n; i++)
            if ((nums[i] <= 0) || (nums[i] > n))
                nums[i] = 1;

        // 使用索引和数字符号作为检查器
        // 例如，如果 nums[1] 是负数表示在数组中出现了数字 `1`
        // 如果 nums[2] 是正数 表示数字 2 没有出现
        for (int i = 0; i < n; i++) {
            int a = Math.abs(nums[i]);
            // 如果发现了一个数字 a - 改变第 a 个元素的符号
            // 注意重复元素只需操作一次
            if (a == n)
                nums[0] = -Math.abs(nums[0]);
            else
                nums[a] = -Math.abs(nums[a]);
        }

        // 现在第一个正数的下标
        // 就是第一个缺失的数
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0)
                return i;
        }

        if (nums[0] > 0)
            return n;

        return n + 1;
    }
}
