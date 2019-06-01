package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangpeng on 2019-06-01.
 * 77. 组合
 * <p>
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 *
 * @see <a href="https://leetcode-cn.com/problems/combinations/">combinations</a>
 */
public class _77_combine {
    public static void main(String[] args) {
        _77_combine combine = new _77_combine();
        List<List<Integer>> lists = combine.combine(4, 2);
        for (int i = 0; i < lists.size(); i++) {
            Util.printList(lists.get(i));
        }
    }

    /**
     * 解题思路（回溯算法）：
     * 1.从list中开始取，一直取到k位
     * 2.取的数放在一个list中，方便增减
     * 3.取到k位后，回退到上一位，取没有取到的数字，直到全部取完
     * <p>
     * 注意不能重复[2,3]和[3,2]是一个组合，排序重复的办法：后面取的数必须比之前的大
     * 优化点：可以去除一些不必要的循环，如循环的终止条件不是<=n，而是<=n-k+index
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> retList = new ArrayList<>();
        dfs(retList, new LinkedList<>(), 0, 1, n, k);
        return retList;
    }

    /**
     * DFS遍历
     * @param retList 返回结果
     * @param itemList 取数集合
     * @param preNum 之前取的数
     * @param index 当前第几位
     * @param n n个数
     * @param k 取k位
     */
    private void dfs(List<List<Integer>> retList,
                     List<Integer> itemList,
                     int preNum,
                     int index,
                     int n,
                     int k) {
        if (itemList.size() == k) {
            retList.add(new LinkedList<>(itemList));
            return;
        }
        for (int i = preNum + 1; i <= n - k + index; i++) {
            itemList.add(i);
            dfs(retList, itemList, i, index + 1, n, k);
            itemList.remove(itemList.size() - 1);
        }
    }
}
