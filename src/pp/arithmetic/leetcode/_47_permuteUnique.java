package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangpeng on 2019-05-30.
 * 47. 全排列 II
 * <p>
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 *
 * @see <a href="https://leetcode-cn.com/problems/permutations-ii/">permutations-ii</a>
 */
public class _47_permuteUnique {
    public static void main(String[] args) {
        _47_permuteUnique permuteUnique = new _47_permuteUnique();
        List<List<Integer>> lists = permuteUnique.permuteUnique(new int[]{1, 1, 2, 2});
        for (int i = 0; i < lists.size(); i++) {
            Util.printList(lists.get(i));
        }
    }

    /**
     * 解题思路：
     * 整体思路类似 {@link _46_permute}，其中需要注意的一点是，重复数字再取的时候得跳过
     * 1.先对数组进行排序
     * 2.采用回溯算法进行取数，当即将取到的数和之前回退回来的数一致的时候，再向上一层回溯
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> retList = new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> numList = toList(nums);
        dfs(numList, retList, new ArrayList<>(), nums.length);
        return retList;
    }

    //递归回溯
    private void dfs(List<Integer> numList,
                     List<List<Integer>> retList,
                     List<Integer> itemList,
                     int n) {
        if (itemList.size() == n) {
            retList.add(new ArrayList<>(itemList));
            return;
        }
        Integer preNum = null;
        for (int i = 0; i < numList.size(); i++) {
            if (preNum != null && preNum.equals(numList.get(i))) {
                //重复数字，不重复取
                continue;
            }
            Integer item = numList.remove(i);
            itemList.add(item);
            dfs(numList, retList, itemList, n);
            itemList.remove(itemList.size() - 1);
            numList.add(i, item);
            preNum = item;
        }
    }

    private List<Integer> toList(int[] nums) {
        List<Integer> retList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            retList.add(nums[i]);
        }
        return retList;
    }
}
