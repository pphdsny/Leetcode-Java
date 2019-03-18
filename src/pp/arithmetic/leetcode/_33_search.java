package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/9/12.
 * 33. 搜索旋转排序数组
 * <p>
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * @see <a href="https://leetcode-cn.com/problems/search-in-rotated-sorted-array/description/">search-in-rotated-sorted-array</a>
 */
public class _33_search {

    public static void main(String[] args) {
        int search = search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);
        System.out.println(search);
        int search1 = search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3);
        System.out.println(search1);
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (target == nums[middle]) {
                return middle;
            } else if (target < nums[middle]) {
                if (nums[start] < nums[middle]) {
                    //一定是递增区间
                    if (target > nums[start]) {
                        end = middle - 1;
                    } else if (target < nums[start]) {
                        start = middle + 1;
                    } else {
                        return start;
                    }
                } else if (nums[start] > nums[middle]) {
                    //一定是旋转区间
                    end = middle - 1;
                } else {
                    start = middle + 1;
                }
            } else if (target > nums[middle]) {
                if (nums[end] > nums[middle]) {
                    //一定是递增区间
                    if (target > nums[end]) {
                        end = middle - 1;
                    } else if (target < nums[end]) {
                        start = middle + 1;
                    } else {
                        return end;
                    }
                } else if (nums[end] < nums[middle]) {
                    //包含旋转区间
                    start = middle + 1;
                } else {
                    end = middle - 1;
                }
            }
        }
        return -1;
    }

    public static int searchByOther(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] > nums[end]) {  // eg. 3,4,5,6,1,2
                if (target > nums[mid] || target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            } else {  // eg. 5,6,1,2,3,4
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
        }
        if (start == end && target != nums[start]) return -1;
        return start;
    }

}
