package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/8/28.
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * @see <a href="https://leetcode-cn.com/problems/kth-largest-element-in-an-array/description/">kth-largest-element-in-an-array</a>
 */
public class _215_findKthLargest_2 {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        int kthLargest = findKthLargest(nums, 4);
        System.out.println("kthLargest:" + kthLargest);
    }

    /**
     * 利用快排序得思想：
     * 1、找到一个基准点，基准点前的都比其大，基准点后的都比其小
     * 2、如果这个基准点正好==k-1，则是所求
     * 3、如果基准点>k-1,则所求在基准点左侧
     * 4、如果基准点<k-1,则所求在基准点右侧
     * 5、对于3，4更新基准点
     * <p>
     * 由于并没有对数组进行绝对的排序，所费的时间绝对比快排小
     * 说时间复杂度是O(n)，n/2+n/4+...+1 ~= n
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int pivot = partition(nums, left, right);
            if (pivot == k - 1) {
                return nums[pivot];
            } else if (pivot > k - 1) {
                right = pivot - 1;
            } else {
                left = pivot + 1;
            }
        }
        return nums[left];
    }

    private static int partition(int[] nums, int left, int right) {
        //先获取三个数的中位数
        int pivot = median3(nums, left, right);
        //int pivot = nums[left];

        int start = left, end = right - 1;
        while (start < end) {
            //从pivot左边找起，停在第一个比pivot小的地方，等待交换
            while (nums[++start] > pivot) {
            }
            //从pivot右边朝气，停在第一个比pivot大的地方，等待交换
            while (nums[--end] < pivot) {
            }
            if (start < end) {
                swap(nums, start, end);
            }
        }
        //此时，交换start与pivot
        swap(nums, start, right - 1);
        return start;
    }

    private static int median3(int[] nums, int left, int right) {
        int median = (left + right) / 2;
        if (nums[left] < nums[median]) {
            swap(nums, left, median);
        }
        if (nums[left] < nums[right]) {
            swap(nums, left, right);
        }
        if (nums[median] < nums[right]) {
            swap(nums, median, right);
        }
        swap(nums, median, right - 1);
        return nums[right - 1];

    }

    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
