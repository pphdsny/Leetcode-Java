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
public class _51_solveNQueens_2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 1; i < 9; i++) {
            List<List<String>> lists = solveNQueens(i);
        }
        long end = System.currentTimeMillis();
        //3ms
        System.out.println("time:" + (end - start));
//        for (int i = 0; i < lists.size(); i++) {
//            Util.printStringList(lists.get(i));
//        }
    }

    static List<List<String>> res = new ArrayList<List<String>>();
    static boolean[] col;
    static boolean[] dia1;
    static boolean[] dia2;
    static char[][] board;

    public static List<List<String>> solveNQueens(int n) {
        if (n <= 0) return res;
        col = new boolean[n];
        int num = 2 * n - 1;
        dia1 = new boolean[num];
        dia2 = new boolean[num];
        board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        dfs(n, 0);
        return res;
    }

    private static void dfs(int n, int index) {
        if (index == n) {
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                list.add(String.valueOf(board[i]));
            }
            res.add(list);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!col[i] && !dia1[i + index] && !dia2[index - i + n - 1]) {
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                board[index][i] = 'Q';
                dfs(n, index + 1);
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
                board[index][i] = '.';
            }
        }
    }
}
