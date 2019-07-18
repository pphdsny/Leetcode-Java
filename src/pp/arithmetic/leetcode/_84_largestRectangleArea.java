package pp.arithmetic.leetcode;

import java.util.Stack;

/**
 * Created by wangpeng on 2019-07-18.
 * <p>
 * 84. 柱状图中最大的矩形
 * <p>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 *  https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/histogram.png
 * <p>
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * <p>
 * https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/histogram_area.png
 * <p>
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _84_largestRectangleArea {

    public static void main(String[] args) {
        _84_largestRectangleArea largestRectangleArea = new _84_largestRectangleArea();
        System.out.println(largestRectangleArea.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(largestRectangleArea.largestRectangleArea(new int[]{1}));
        System.out.println(largestRectangleArea.largestRectangleArea(new int[]{4, 2}));
    }

    /**
     * 解题思路：
     * 能画出的矩形面积受两部分影响，一是宽，一是高，但是具体如何才能使勾勒出的矩形最大？==>尽可能使宽*高最大化
     * 难点：如何保存遍历过程中的中间结果，可能中间结果在不断遍历的过程中会变成最大的面积
     * <p>
     * 分治求解
     * 1、先找到矩形中最小的一个，求出面积
     * 2、再求最小左边的矩形面积（循环1，2，3）
     * 3、再求最小右边的矩形面积（循环1，2，3）
     * 4、从其中找出最大的面积
     * <p>
     * 执行用时 :591 ms, 在所有 Java 提交中击败了20.50%的用户 ==>时间复杂度O(nLogn)
     * 内存消耗 :43.3 MB, 在所有 Java 提交中击败了31.60%的用户
     * <p>
     * 更优解法(栈)：{@link _84_largestRectangleArea#largestRectangleArea2(int[])} ==>时间复杂度O(n)
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        return calculate(heights, 0, heights.length - 1);
    }

    /**
     * 分治求解
     *
     * @param heights
     * @param start
     * @param end
     * @return
     */
    private int calculate(int[] heights, int start, int end) {
        if (start > end) {
            return 0;
        }

        int min = start;
        for (int i = start; i <= end; i++) {
            if (heights[i] < heights[min]) {
                min = i;
            }
        }
        int mid = heights[min] * (end - start + 1);
        int left = calculate(heights, start, min - 1);
        int right = calculate(heights, min + 1, end);
        return Math.max(mid, Math.max(left, right));
    }


    /**
     * 栈
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        return maxarea;
    }
}
