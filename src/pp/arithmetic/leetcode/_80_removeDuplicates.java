package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-03-22.
 * 80. 删除排序数组中的重复项 II
 * <p>
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * <p>
 * 给定 nums = [1,1,1,2,2,3],
 * <p>
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 * <p>
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 *
 * @see <a href="https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/">remove-duplicates-from-sorted-array-ii</a>
 */
public class _80_removeDuplicates {

    public static void main(String[] args) {
        _80_removeDuplicates removeDuplicates = new _80_removeDuplicates();
        System.out.println(removeDuplicates.removeDuplicates(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}));
        System.out.println(removeDuplicates.removeDuplicates(new int[]{1, 1, 1, 2, 2, 3}));
    }

    /**
     * 解法二：别人的，更简洁
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (i < 2 || num > nums[i - 2]) {
                nums[i++] = num;
            }
        }
        return i;
    }

    /**
     * 解法一：自己的
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int preIndex = 0, preCount = 1, lastIndex = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[preIndex]) {
                if (preCount < 2) {
                    preCount++;
                    nums[lastIndex] = nums[i];
                    lastIndex++;
                }
            } else {
                preIndex = i;
                preCount = 1;
                nums[lastIndex] = nums[i];
                lastIndex++;
            }
        }

        return lastIndex;
    }
}
