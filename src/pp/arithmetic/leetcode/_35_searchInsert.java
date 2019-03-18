package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/9/12.
 * 35. 搜索插入位置
 * <p>
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 你可以假设数组中无重复元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 * <p>
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * @see <a href="https://leetcode-cn.com/problems/search-insert-position/description/">search-insert-position</a>
 */
public class _35_searchInsert {
    public static void main(String[] args) {
        int i = searchInsert(new int[]{1, 3, 5, 6}, 2);
        System.out.println(i);
        int i1 = searchInsert(new int[]{1, 3, 5, 6}, 7);
        System.out.println(i1);
        int i2 = searchInsert(new int[]{1, 3, 5, 6}, 0);
        System.out.println(i2);
        int i3 = searchInsert(new int[]{}, 0);
        System.out.println(i3);
    }

    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int start = 0;
        int end = nums.length - 1;
        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (target < nums[middle]) {
                if (middle == 0) {
                    return 0;
                } else if (target > nums[middle - 1]) {
                    return middle;
                }
                end = middle - 1;
            } else if (target > nums[middle]) {
                if (middle == nums.length - 1) {
                    return nums.length;
                } else if (target < nums[middle + 1]) {
                    return middle + 1;
                }
                start = middle + 1;
            } else {
                return middle;
            }
        }
        return 0;
    }
}
