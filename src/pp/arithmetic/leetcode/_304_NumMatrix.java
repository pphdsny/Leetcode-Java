package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-04-02.
 * 304. 二维区域和检索 - 矩阵不可变
 * <p>
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。
 * <image src="https://leetcode.com/static/images/courses/range_sum_query_2d.png"></image>
 * <p>
 * Range Sum Query 2D
 * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 * <p>
 * 示例:
 * <p>
 * 给定 matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * <p>
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * 说明:
 * <p>
 * 你可以假设矩阵不可变。
 * 会多次调用 sumRegion 方法。
 * 你可以假设 row1 ≤ row2 且 col1 ≤ col2。
 *
 * @see <a href="https://leetcode-cn.com/problems/range-sum-query-2d-immutable/">range-sum-query-2d-immutable</a>
 */
public class _304_NumMatrix {

    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][]{
                {-4,-5}
        });
        System.out.println(numMatrix.sumRegion(0, 1, 0, 1));
//        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
//        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }

    /**
     * 由于sumRegion方法会被方法调用，所以计算的话不能每次都遍历
     * 基于动态规划的思想，将矩阵中的每一点相对于(0,0)初始化计算出来
     * DP[i][j]=DP[i-1][j]+DP[i][j-1]-DP[i-1][j-1]，其中i>0,j>0
     * 已知：左上角 (row1, col1)，右下角(row2, col2)
     * 根据题目可以得出面积=DP[row2][col2]-DP[row1-1][col2]-DP[row2][col1-1]+DP[row1-1][col1-1]，其中r1,c1必须大于0
     * 如r1==0或者c1==0做特殊处理
     */
    public static class NumMatrix {

        private int[][] dp;

        public NumMatrix(int[][] matrix) {
            if (matrix.length == 0) return;
            dp = new int[matrix.length][matrix[0].length];
            //初始化第一行和第一列
            dp[0][0] = matrix[0][0];
            for (int i = 1; i < matrix.length; i++) {
                dp[i][0] = dp[i - 1][0] + matrix[i][0];
            }
            for (int i = 1; i < matrix[0].length; i++) {
                dp[0][i] = dp[0][i - 1] + matrix[0][i];
            }
            for (int i = 1; i < matrix.length; i++) {
                for (int j = 1; j < matrix[0].length; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (dp == null || dp.length == 0) return 0;
            if (row1 == 0 && col1 == 0) {
                return dp[row2][col2];
            } else if (row1 > 0 && col1 > 0) {
                return dp[row2][col2] - dp[row1 - 1][col2] - dp[row2][col1 - 1] + dp[row1 - 1][col1 - 1];
            } else if (row1 == 0) {
                return dp[row2][col2] - dp[row2][col1 - 1];
            } else {
                return dp[row2][col2] - dp[row1 - 1][col2];
            }
        }
    }
}
