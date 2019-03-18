package pp.arithmetic.leetcode;

import java.util.PriorityQueue;

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
public class _215_findKthLargest {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6};
        int kthLargest = findKthLargest(nums, 4);
        System.out.println("kthLargest:" + kthLargest);
    }

    /**
     * 使用堆进行实现，时间复杂度n*logn,logn是堆排序需要是时间
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            priorityQueue.add(num);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        return priorityQueue.peek();
    }
}
