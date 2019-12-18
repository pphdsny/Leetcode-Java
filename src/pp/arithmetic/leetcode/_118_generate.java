package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2019-12-18.
 * 118. 杨辉三角
 *
 *
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 * https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _118_generate {


    public static void main(String[] args) {
        _118_generate generate = new _118_generate();
        List<List<Integer>> list = generate.generate(5);
        for (int i = 0; i < list.size(); i++) {
            Util.printList(list.get(i));
        }
    }

    /**
     * 解题思路：
     * 0、用一个list数组保存上一行的遍历结果
     * 1、当下一行是头和尾，直接赋值1
     * 2、当下一行在中间位置j，结果=preItem.get(j - 1) + preItem.get(j)
     *
     * 执行用时 :1 ms, 在所有 java 提交中击败了98.18%的用户
     * 内存消耗 :34.5 MB, 在所有 java 提交中击败了25.70%的用户
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> retList = new ArrayList<>();
        List<Integer> preItem = null;
        for (int i = 0; i < numRows; i++) {
            List<Integer> item = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    item.add(1);
                } else {
                    item.add(preItem.get(j - 1) + preItem.get(j));
                }
            }
            preItem = item;
            retList.add(item);
        }
        return retList;
    }
}
