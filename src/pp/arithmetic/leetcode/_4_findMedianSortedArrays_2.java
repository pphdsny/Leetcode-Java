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
public class _4_findMedianSortedArrays_2 {

    public static void main(String[] args) {
        double medianSortedArrays = findMedianSortedArrays(new int[]{1, 3}, new int[]{2});
        System.out.println(medianSortedArrays);
        double medianSortedArrays1 = findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4});
        System.out.println(medianSortedArrays1);
    }

    /**
     * 要求时间复杂度O(Log(n))
     * 构造两块，都相等....中位数就是中间的数
     * 实现分析@see <a herf="https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/"></a>
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            int[] temp = nums1;
            nums2 = nums1;
            nums1 = temp;
            m = nums1.length;
            n = nums2.length;
        }
        int half = (m + n + 1) / 2;
        int iMin = 0;
        int iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = half - i;
            if (i < iMax && nums2[j - 1] > nums1[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && nums1[i - 1] > nums2[j]) {
                iMax = i - 1; // i is too big
            } else {//i符合条件
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }

        return 0;
    }


}
