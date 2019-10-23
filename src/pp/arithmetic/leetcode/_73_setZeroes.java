package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-10-23.
 * 73. 矩阵置零
 *
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出:
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 示例 2:
 *
 * 输入:
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出:
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * 进阶:
 *
 * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个常数空间的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/set-matrix-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _73_setZeroes {

    public static void main(String[] args) {
        _73_setZeroes setZeroes = new _73_setZeroes();
        int[][] matrix = new int[][]{
                {1, 1, 1},
                {0, 1, 2}
        };
        setZeroes.setZeroes(matrix);
        int[][] matrix1 = new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        setZeroes.setZeroes(matrix1);
        int[][] matrix2 = new int[][]{
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        setZeroes.setZeroes(matrix2);
    }

    /**
     * 难点在于：使用常数空间，我们先来看不适用常数空间咋解决
     * O(mn)：直接生成一个同等大小的矩阵，遍历原始矩阵，遇0将新矩阵横竖都设置为0
     * O(m+n)：两个set分别保存有0的横和竖列，遍历结束直接将set中的横竖设0
     * 解题思路：
     * 1、利用矩阵的第一行和第一列保存有0的行和列
     * 2、需要考虑下特殊情况的[0,0]，这个位置可能是横列、纵列、自身导致赋的0
     *
     * 执行用时 :1 ms, 在所有 java 提交中击败了100.00%的用户
     * 内存消耗 :43.3 MB, 在所有 java 提交中击败了97.83%的用户
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        boolean isCol = false;
        int row = matrix.length;
        int col = matrix[0].length;

        //使用第一行、第一列标记0
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                isCol = true;
            }
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        //横、竖列置0
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][0] ==0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        //[0,0]为0的时候，需要特殊判断下是横还是纵
        if (matrix[0][0] == 0) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }

        if (isCol) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
