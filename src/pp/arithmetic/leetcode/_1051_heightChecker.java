package pp.arithmetic.leetcode;

import java.util.Arrays;

/**
 * Created by wangpeng on 2019-06-05.
 * 1051. 高度检查器
 * <p>
 * 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。
 * <p>
 * 请你返回至少有多少个学生没有站在正确位置数量。该人数指的是：能让所有学生以 非递减 高度排列的必要移动人数。
 * <p>
 * 示例：
 * 输入：[1,1,4,2,1,3]
 * 输出：3
 * 解释：
 * 高度为 4、3 和最后一个 1 的学生，没有站在正确的位置。
 * <p>
 * 1 <= heights.length <= 100
 * 1 <= heights[i] <= 100
 *
 * @see <a href="https://leetcode-cn.com/problems/height-checker/">height-checker</a>
 */
public class _1051_heightChecker {
    public static void main(String[] args) {
        _1051_heightChecker heightChecker = new _1051_heightChecker();
        System.out.println(heightChecker.heightChecker(new int[]{1, 1, 4, 2, 1, 3}));
    }

    /**
     * 解题思路：
     * 1.对数组进行排序
     * 2.循环遍历原始数组和排序数组，找到差异
     * <p>
     * 解题时间复杂度（O(nLogn+n)）,不知道是否能提交通过，easy的题看来不能考虑太多
     *
     * @param heights
     * @return
     */
    public int heightChecker(int[] heights) {
        int[] sortHeights = new int[heights.length];
        System.arraycopy(heights, 0, sortHeights, 0, heights.length);
        Arrays.sort(sortHeights);
        int diffCount = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != sortHeights[i]) {
                diffCount++;
            }
        }

        return diffCount;
    }
}
