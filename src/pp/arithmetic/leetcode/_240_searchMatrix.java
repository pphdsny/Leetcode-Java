package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-08-05.
 * 240. 搜索二维矩阵 II
 * <p>
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 * <p>
 * 现有矩阵 matrix 如下：
 * <p>
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * <p>
 * 给定 target = 20，返回 false。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _240_searchMatrix {

    public static void main(String[] args) {
        _240_searchMatrix searchMatrix = new _240_searchMatrix();
        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        //初始题解
        System.out.println(searchMatrix.searchMatrix(matrix, 5));
        System.out.println(searchMatrix.searchMatrix(matrix, 20));
        System.out.println(searchMatrix.searchMatrix(matrix, 18));
        System.out.println(searchMatrix.searchMatrix(matrix, 19));
        //更优题解
        System.out.println(searchMatrix.searchMatrix2(matrix, 5));
    }

    /**
     * 解题思路：
     * 最简单的解法就是全遍历一遍矩阵，时间复杂度O(m*n)，肯定不是题目中所说的高效算法
     * 看看能不能减少一些不必要的遍历，观察矩阵是有规则的：从左到右和从上到下都是递增的
     * 1.斜脚线遍历Matrix，如果taget==遍历项，则直接返回
     * 2.如果target>遍历项，则继续遍历下一个斜脚线
     * 3.如果target<遍历项，则结果在其左子矩阵和右上子矩阵中，将子矩阵循环1-3
     * <p>
     * 执行用时 :19 ms, 在所有 Java 提交中击败了16.82%的用户
     * 内存消耗 :51.2 MB, 在所有 Java 提交中击败了42.79%的用户
     * 耗时思考：
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        return searchMatrix(matrix, target, 0, matrix.length, 0, matrix[0].length);
    }

    private boolean searchMatrix(int[][] matrix, int target, int sx, int ex, int sy, int ey) {
        if (sx == ex || sy == ey) return false;
        int x = sx, y = sy;
        while (x < ex && y < ey) {
            if (target == matrix[x][y]) {
                return true;
            }
            if (target > matrix[x][y]) {
                x++;
                y++;
            } else {
                return searchMatrix(matrix, target, sx, x, y, ey) || searchMatrix(matrix, target, x, ex, sy, y);
            }
        }
        if (x == ex && y == ey) {
            return false;
        }
        //matrix不一定是对称矩阵，可能会存在遗留项
        if (x < ex) {
            return searchMatrix(matrix, target, x, ex, sy, ey);
        }
        if (y < ey) {
            return searchMatrix(matrix, target, sx, ex, y, ey);
        }

        return false;
    }

    /**
     * 时间复杂度O(m+n)
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return false;
        int row = matrix.length - 1;
        int col = 0;
        int count = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (target < matrix[row][col]) row--;
            else if (target > matrix[row][col]) col++;
            else {
                count++;
                row--;
                col++;
            }
        }
        return count > 0;

    }

}
