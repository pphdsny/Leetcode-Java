package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2018/9/25.
 * 120.三角形最小路径和
 * <p>
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * <p>
 * 例如，给定三角形：
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * <p>
 * 说明：
 * <p>
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * @see <a href="https://leetcode-cn.com/problems/triangle/description/">triangle</a>
 */
public class _120_minimumTotal {
    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        List<Integer> list3 = new ArrayList<>();
        list3.add(6);
        list3.add(5);
        list3.add(7);
        List<Integer> list4 = new ArrayList<>();
        list4.add(4);
        list4.add(1);
        list4.add(8);
        list4.add(3);
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);
        int i = minimumTotal(lists);
        System.out.println(i);
        Util.printDivideLine();
        //更优
        int i1 = minimumTotal2(lists);
        System.out.println(i1);
    }

    /**
     * 会超时，复杂度2^n(n=三角形的高度)
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal(List<List<Integer>> triangle) {
        int dfs = dfs(triangle, 0, 0, 0, 0);
        return dfs;
    }

    private static int dfs(List<List<Integer>> triangle,
                           int startIndex,
                           int endIndex,
                           int floor,
                           int total) {
        if (floor >= triangle.size()) {
            return total;
        }
        int resultTotal = Integer.MAX_VALUE;
        List<Integer> list = triangle.get(floor);
        for (int i = startIndex; i <= endIndex; i++) {
            int nextStartIndex = i;
            int nextEndIndex = i + 1;
            int tempTotal = dfs(triangle, nextStartIndex, nextEndIndex, floor + 1, total + list.get(i));
            if (resultTotal > tempTotal) {
                resultTotal = tempTotal;
            }
        }
        return resultTotal;
    }

    /**
     * 时间复杂度：T=1+2+3+...+n=n(n-1)/2=O(n^2)
     * 空间复杂度：O(n^2)
     *
     * @param triangle
     * @return
     */
    public static int minimumTotal2(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.size()];
        //先构造最后一层的dp
        List<Integer> lastFloor = triangle.get(triangle.size() - 1);
        for (int i = 0; i < lastFloor.size(); i++) {
            dp[triangle.size() - 1][i] = lastFloor.get(i);
        }
        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + list.get(j);
            }
        }
        return dp[0][0];
    }
}
