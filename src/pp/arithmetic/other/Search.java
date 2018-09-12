package pp.arithmetic.other;

/**
 * Created by wangpeng on 2018/9/12.
 * 找元素
 */
public class Search {

    public static void main(String[] args) {
        int index = binarySearch(new int[]{1, 2, 3, 4, 12}, 12);
        System.out.println(index);
        int index2 = binarySearch2(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 2);
        System.out.println(index2);
    }

    /**
     * 循环实现
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (target > nums[middle]) {
                start = middle + 1;
            } else if (target < nums[middle]) {
                end = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * 递归实现
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch2(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    private static int search(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) / 2;
        if (target == nums[middle]) {
            return middle;
        }
        if (target > nums[middle]) {
            return search(nums, middle + 1, end, target);
        }
        if (target < nums[middle]) {
            return search(nums, start, middle - 1, target);
        }
        return -1;
    }
}
