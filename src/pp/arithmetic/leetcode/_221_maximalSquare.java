package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018-12-06.
 * 221. 最大正方形
 * <p>
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * 输出: 4
 *
 * @see <a href="https://leetcode-cn.com/problems/maximal-square/description/">maximal-square</a>
 */
public class _221_maximalSquare {
    public static void main(String[] args) {
        System.out.println(maximalSquare(new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        }));
        System.out.println(maximalSquare(new char[][]{
                {'0', '0', '1'},
                {'0', '0', '1'},
                {'0', '1', '1'},
                {'1', '0', '0'}
        }));
        System.out.println(maximalSquare(new char[][]{
                {'1'}
        }));
    }

    /**
     * DP:拆分成第i,j个位置上能组成最大正方形的面积
     * 状态转移方程：
     * dp[][]
     *
     * @param matrix
     * @return
     */
    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length < 1) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int maxLen = 0;
        //init
        for (int i = 0; i < n; i++) {
            //一个1的点也是一个正方形
            dp[0][i] = matrix[0][i] == '1' ? 1 : 0;
            maxLen = Math.max(dp[0][i], maxLen);
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            maxLen = Math.max(dp[i][0], maxLen);
        }
        //循环构造
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = 0;
                if (matrix[i][j] == '0') {
                    continue;
                }
                int min = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                dp[i][j] = min + 1;
                maxLen = Math.max(dp[i][j], maxLen);
            }
        }
        return maxLen * maxLen;
    }
}
