package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2019-05-29.
 * 46. 全排列
 * <p>
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * @see <a href="https://leetcode-cn.com/problems/permutations/">permutations</a>
 */
public class _46_permute {
    public static void main(String[] args) {
        _46_permute permute = new _46_permute();
        List<List<Integer>> list = permute.permute(new int[]{1, 2, 3});
        for (int i = 0; i < list.size(); i++) {
            Util.printList(list.get(i));
        }
    }

    /**
     * 解题思路（回溯算法）:
     * 根据题意，数组中的每一个数字，都可能出现在排列中的任何位置，所以一个个的去试着放
     * 1、将数组转换成一个list，再定义一个itemList保存遍历结果，retList保存返回结果
     * 2、循环list下标index，取index的数加入itemList，将其移除list
     * 3、再将剩余的list重复第2步
     * 4、当itemList的大小==数组大小，添加到retList
     * 5、回退第2步，将之前移除的数组添加到list中，并从itemList中移除
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> retList = new ArrayList<>();
        List<Integer> numList = toList(nums);
        dfs(numList, retList, new ArrayList<>(), nums.length);
        return retList;
    }

    private void dfs(List<Integer> numList,
                     List<List<Integer>> retList,
                     List<Integer> itemList,
                     int n) {
        if (itemList.size() == n) {
            retList.add(new ArrayList<>(itemList));
            return;
        }
        for (int i = 0; i < numList.size(); i++) {
            Integer item = numList.remove(i);
            itemList.add(item);
            dfs(numList, retList, itemList, n);
            itemList.remove(item);
            numList.add(i, item);
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
