package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/10/24.
 * 4. 两个排序数组的中位数
 * <p>
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
 * <p>
 * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 * <p>
 * 你可以假设 nums1 和 nums2 不同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 中位数是 (2 + 3)/2 = 2.5
 *
 * @see <a href="https://leetcode-cn.com/problems/median-of-two-sorted-arrays/description/">median-of-two-sorted-arrays</a>
 */
public class _4_findMedianSortedArrays {

    public static void main(String[] args) {
        double medianSortedArrays = findMedianSortedArrays(new int[]{1, 3}, new int[]{2});
        System.out.println(medianSortedArrays);
        double medianSortedArrays1 = findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        System.out.println(medianSortedArrays1);
    }

    /**
     * 时间复杂度O(n)，不满足要求
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //先对两个有序数组进行排序,O(log)的时间复杂度=>归并排序
        int[] newArr = new int[nums1.length + nums2.length];
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                newArr[i + j] = nums1[i];
                i++;
            } else {
                newArr[i + j] = nums2[j];
                j++;
            }
        }
        while (i < nums1.length) {
            newArr[i + j] = nums1[i];
            i++;
        }
        while (j < nums2.length) {
            newArr[i + j] = nums2[j];
            j++;
        }
        double retVal;
        if (newArr.length % 2 == 0) {
            retVal = (newArr[newArr.length / 2] + newArr[newArr.length / 2 - 1]) / 2f;
        } else {
            retVal = newArr[newArr.length / 2];
        }
        return retVal;
    }
}
