package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-03-22.
 * 11. 盛最多水的容器
 * <p>
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * <image src="https://aliyun-lc-upload.oss-cn-hangzhou.aliyuncs.com/aliyun-lc-upload/uploads/2018/07/25/question_11.jpg"></image>
 * <p>
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 *
 * @see <a href="https://leetcode-cn.com/problems/container-with-most-water/">container-with-most-water</a>
 */
public class _11_maxArea {

    public static void main(String[] args) {
        _11_maxArea maxArea = new _11_maxArea();
        System.out.println(maxArea.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public int maxArea(int[] height) {
        int startI = 0, endI = height.length - 1;
        int max = 0;
        while (startI < endI) {
            int minHeight = Math.min(height[startI], height[endI]);
            int area = minHeight * (endI - startI);
            max = Math.max(area, max);
            if (height[startI] < height[endI]) {
                startI++;
            } else {
                endI--;
            }
        }

        return max;
    }
}
