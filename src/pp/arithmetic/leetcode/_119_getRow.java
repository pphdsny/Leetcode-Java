package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2019-12-18.
 * 119. 杨辉三角 II
 *
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 * https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 *
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _119_getRow {

    public static void main(String[] args) {
        _119_getRow getRow = new _119_getRow();
        Util.printList(getRow.getRow(6));
    }

    /**
     * 解题思路：
     * 最简单的就是像 {@link _118_generate} 中解题方式，求出第row行的结果，可是期望 O(k) 空间复杂度，这是难点
     * 考虑能不能通过规律找出直接计算第row行的结果？
     * 通过杨辉三角规律可知，第i行第j个得数字结果是(i,j)的组合数
     *
     * 执行用时 :1 ms, 在所有 java 提交中击败了93.68%的用户
     * 内存消耗 :33.6 MB, 在所有 java 提交中击败了23.63%的用户
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> retList = new ArrayList<>();
        int N = rowIndex;
        long pre = 1;
        retList.add(1);
        for (int k = 1; k <= N; k++) {
            long cur = pre * (N - k + 1) / k;
            retList.add((int) cur);
            pre = cur;
        }
        return retList;
    }
}
