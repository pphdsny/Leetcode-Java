package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-10-24.
 * 74. 搜索二维矩阵
 *
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1:
 *
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * 示例 2:
 *
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _74_searchMatrix {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 50}
        };
        _74_searchMatrix searchMatrix = new _74_searchMatrix();
        System.out.println(searchMatrix.searchMatrix(matrix,1));
        System.out.println(searchMatrix.searchMatrix(matrix,3));
        System.out.println(searchMatrix.searchMatrix(matrix,5));
        System.out.println(searchMatrix.searchMatrix(matrix,7));
        System.out.println(searchMatrix.searchMatrix(matrix,10));
        System.out.println(searchMatrix.searchMatrix(matrix,11));
        System.out.println(searchMatrix.searchMatrix(matrix,16));
        System.out.println(searchMatrix.searchMatrix(matrix,20));
        System.out.println(searchMatrix.searchMatrix(matrix,23));
        System.out.println(searchMatrix.searchMatrix(matrix,50));
        System.out.println(searchMatrix.searchMatrix(matrix,13));
        System.out.println(searchMatrix.searchMatrix(matrix,40));
    }

    /**
     * 解题思路：
     * 整个矩阵类似一个有序的升序数组，考虑使用二分查找是否存在目标值
     * 难点：中间点的计算
     * 1.利用公式计算起点和终点之间的差：(ex - sx) * col + ey - sy，
     * 2.中间点距离起点的步数：ml = ((ex - sx) * col + ey - sy) / 2
     * 3.mx = sx + (ml + sy) / col <== 起点+偏移计算出中间点的x
     * 4.my = ml + sy - (mx - sx) * col <== 根据第一步的公式，代入mx，计算出my
     * 5.利用二分查找的规则判断出结果
     *
     * 执行用时 :0 ms, 在所有 java 提交中击败了100.00%的用户
     * 内存消耗 :42.6 MB, 在所有 java 提交中击败了39.56%的用户
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        if (col == 0) return false;
        int sx = 0, sy = 0, ex = row - 1, ey = col - 1;
        if (target < matrix[sx][sy] || target > matrix[ex][ey]) return false;
        int mx, my, ml;
        while (sx * col + sy <= ex * col + ey) {
            //计算中间点
            ml = ((ex - sx) * col + ey - sy) / 2;
            mx = sx + (ml + sy) / col;
            my = ml + sy - (mx - sx) * col;
            int middle = matrix[mx][my];
            if (middle == target) return true;
            //防止无法退出
            if (ml == 0) {
                if (matrix[ex][ey] == target) return true;
                return false;
            }
            if (middle > target) {
                ex = mx;
                ey = my;
            } else {
                sx = mx;
                sy = my;
            }
        }

        return false;
    }
}
