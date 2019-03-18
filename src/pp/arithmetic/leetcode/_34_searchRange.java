package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2018/9/12.
 * 34.在排序数组中查找元素的第一个和最后一个位置
 * <p>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * @see <a href="https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/">find-first-and-last-position-of-element-in-sorted-array</a>
 */
public class _34_searchRange {

    public static void main(String[] args) {
        int[] ints = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        Util.printArray(ints);
        int[] ints1 = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 1);
        Util.printArray(ints1);
        int[] ints2 = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 12);
        Util.printArray(ints2);
        int[] ints3 = searchRange(new int[]{1, 1}, 1);
        Util.printArray(ints3);
        int[] ints4 = searchRange(new int[]{1}, 1);
        Util.printArray(ints4);
        int[] ints5 = searchRange(new int[]{1, 2, 3, 3, 3, 3, 4, 5, 9}, 3);
        Util.printArray(ints5);
    }

    /**
     * 需要注意，不要使用循环，复杂度会到O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int startIndex = -1;
        int endIndex = -1;
        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (target == nums[middle]) {
                startIndex = searchLeft(nums, start, middle, target);
                endIndex = searchRight(nums, middle, end, target);
                //方案不行，复杂度可能会到O(n)
//                while (startIndex > 0 && nums[startIndex - 1] == target) {
//                    startIndex--;
//                }
//                while (endIndex < nums.length - 1 && nums[endIndex + 1] == target) {
//                    endIndex++;
//                }
                break;
            } else if (target > nums[middle]) {
                start = middle + 1;
            } else if (target < nums[middle]) {
                end = middle - 1;
            }
        }

        return new int[]{startIndex, endIndex};
    }

    private static int searchLeft(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (target == nums[middle]) {
            if (middle == 0 || target > nums[middle - 1]) {
                return middle;
            }
            return searchLeft(nums, start, middle - 1, target);
        } else if (target > nums[middle]) {
            return searchLeft(nums, middle + 1, end, target);
        } else if (target > nums[middle]) {
            return searchLeft(nums, start, middle - 1, target);
        }
        return -1;
    }

    private static int searchRight(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (target == nums[middle]) {
            if (middle == nums.length - 1 || target < nums[middle + 1]) {
                return middle;
            }
            return searchRight(nums, middle + 1, end, target);
        } else if (target > nums[middle]) {
            return searchRight(nums, middle + 1, end, target);
        } else if (target < nums[middle]) {
            return searchRight(nums, start, middle - 1, target);
        }
        return -1;
    }
}
