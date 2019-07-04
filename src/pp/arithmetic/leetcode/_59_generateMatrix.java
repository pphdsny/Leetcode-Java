package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-07-02.
 * 59. 螺旋矩阵 II
 * <p>
 * 给定一个正整数 n，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _59_generateMatrix {
    public static void main(String[] args) {
        _59_generateMatrix generateMatrix = new _59_generateMatrix();
        int[][] ints = generateMatrix.generateMatrix(3);
        for (int i = 0; i < ints.length; i++) {
            Util.printArray(ints[i]);
        }
    }

    /**
     * 解题思路：
     * 通过四个变量（2个行0~n、2个列0~n），一圈一圈的遍历，每一圈遍历的时候做四个方向的遍历
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        int index = 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) matrix[r1][c] = index++;
            for (int r = r1 + 1; r <= r2; r++) matrix[r][c2] = index++;
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) matrix[r2][c] = index++;
                for (int r = r2; r > r1; r--) matrix[r][c1] = index++;
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return matrix;
    }
}
