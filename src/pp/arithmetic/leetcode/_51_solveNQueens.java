package pp.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2018/9/11.
 * 51.N皇后
 * <p>
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * <p>
 * https://leetcode-cn.com/static/images/problemset/8-queens.png
 * <p>
 * <p>
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * <p>
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例:
 * <p>
 * 输入: 4
 * 输出: [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 * @see <a href="https://leetcode-cn.com/problems/n-queens/description/">n-queens</a>
 */
public class _51_solveNQueens {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 1; i <9 ; i++) {
            List<List<String>> lists = solveNQueens(i);
        }
        long end = System.currentTimeMillis();
        //22ms,优化参考：_51_solveNQueens_2
        //优化：1、减少不能放的地方赋值；2、Q的遍历赋值
        System.out.println("time:" + (end - start));
//        for (int i = 0; i < lists.size(); i++) {
//            Util.printStringList(lists.get(i));
//        }
    }

    /**
     * 1.构造空的n*n的二维数组
     * 2.定义每个Q皇后的杀伤范围
     * 3.从第一行开始递归回溯
     * 4.如果某一行都没有可以放置的位置，则回溯
     *
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        int[][] nums = new int[n][n];
        int[][] QIndex = new int[n][n];
        generate(nums, QIndex, result, 0);
        return result;
    }

    private static void generate(int[][] nums,
                                 int[][] QIndex,
                                 List<List<String>> result,
                                 int x) {
        if (x == nums.length) {
            result.add(convert(QIndex));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[x][i] == 0) {
                int[][] copyNums = copyArray(nums);
                damage(copyNums, x, i);
                QIndex[x][i] = 1;
                generate(copyNums, QIndex, result, x + 1);
                QIndex[x][i] = 0;
            }
        }
    }

    private static List<String> convert(int[][] copyNums) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < copyNums.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < copyNums[i].length; j++) {
                if (copyNums[i][j] == 0) {
                    builder.append(".");
                } else {
                    builder.append("Q");
                }
            }
            result.add(builder.toString());
        }
        return result;
    }

    private static int[][] copyArray(int[][] nums) {
        int[][] newNums = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                newNums[i][j] = nums[i][j];
            }
        }
        return newNums;
    }

    private static void damage(int[][] nums, int x, int y) {
        int[] dx = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
        nums[x][y] = 1;
        for (int i = 0; i < dx.length; i++) {
            int tempDX = x + dx[i];
            int tempDY = y + dy[i];
            while (tempDX >= 0 && tempDX < nums.length
                    && tempDY >= 0 && tempDY < nums.length) {
                nums[tempDX][tempDY] = 1;
                tempDX = tempDX + dx[i];
                tempDY = tempDY + dy[i];
            }
        }
    }

}
