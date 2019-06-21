package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-06-21.
 * 48. 旋转图像
 * <p>
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * <p>
 * 将图像顺时针旋转 90 度。
 * <p>
 * 说明：
 * <p>
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * <p>
 * 示例 1:
 * <p>
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * 示例 2:
 * <p>
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-image
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _48_rotate {

    public static void main(String[] args) {
        _48_rotate rotate = new _48_rotate();
        int[][] arrs = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        rotate.rotate(arrs);
        for (int i = 0; i < arrs.length; i++) {
            Util.printArray(arrs[i]);
        }
    }

    /**
     * 解题思路：
     * 因为需要原地旋转（不借助另一个矩阵），可以考虑借助一个临时的存储位，接受被旋转位的数据
     * 考虑：如何找到旋转规律？==>纸上比划比划
     * 发现旋转规律：下一位的行等于上一位的列，下一位的列=n-上一位的行-1（-1是从0开始）
     * 代码实现：先从外部循环，结束后再逐步向内走，直到循环结束
     * <p>
     * 需要注意：循环结束条件
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) return;
        int hn = n % 2 == 0 ? n / 2 : (n / 2 + 1);//一半取偶数的一半，奇数一半+1
        int rowI = 0, colI = 0;
        int nrowI, ncolI, tempI;//循环的下标
        int temp, preTemp;
        while (colI < n - 1 && rowI < n - 1) {
            nrowI = rowI;
            ncolI = colI;
            preTemp = matrix[nrowI][ncolI];
            //旋转遍历四次
            for (int i = 0; i < 4; i++) {
                //取旋转的位置
                tempI = nrowI;
                nrowI = ncolI;
                ncolI = n - 1 - tempI;
                temp = matrix[nrowI][ncolI];
                matrix[nrowI][ncolI] = preTemp;
                preTemp = temp;
            }
            //取下一位循环位置
            colI++;
            if (colI - rowI > n - rowI * 2 - 2) {  //每次向内缩一圈，少两位
                rowI++;
                colI = rowI;
                if (rowI >= hn) {
                    break;
                }
            }
        }
    }
}
