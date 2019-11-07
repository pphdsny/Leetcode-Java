package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-11-07.
 * 88. 合并两个有序数组
 *
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 *
 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _88_merge {

    public static void main(String[] args) {
        _88_merge merge = new _88_merge();
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0, 0, 0};
        int[] nums2 = new int[]{1, 1, 2, 5, 6};
        merge.merge(nums1, 0, nums2, 5);
        Util.printArray(nums1);
    }

    /**
     * 解题思路：
     * 同时遍历两个数组，比较各自的大小，插入到相应的位置，由于nums1有额外的位置，所以从后面开始插入大元素可以减少元素的移动
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        while (m > 0 || n > 0) {
            int numM;
            int numN;
            if (m > 0 && n > 0) {
                numM = nums1[m - 1];
                numN = nums2[n - 1];
                if (numM > numN) {
                    nums1[m + n - 1] = numM;
                    m--;
                } else {
                    nums1[m + n - 1] = numN;
                    n--;
                }
            } else if (m > 0) {
                //只剩下nums1,肯定是有序的
                break;
            } else {
                nums1[n - 1] = nums2[n - 1];
                n--;
            }
        }
    }
}
