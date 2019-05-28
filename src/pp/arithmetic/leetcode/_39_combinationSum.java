package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by wangpeng on 2019-05-20.
 * 39. 组合总和
 * <p>
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 *
 * @see <a href="https://leetcode-cn.com/problems/combination-sum/">combination-sum</a>
 */
public class _39_combinationSum {

    public static void main(String[] args) {
        _39_combinationSum combinationSum = new _39_combinationSum();
//        List<List<Integer>> lists = combinationSum.combinationSum(new int[]{1,2}, 2);
        List<List<Integer>> lists = combinationSum.combinationSum(new int[]{2, 3, 5}, 8);
        for (int i = 0; i < lists.size(); i++) {
            Util.printList(lists.get(i));
        }
    }

    /**
     * 解题思路：
     * 1.先对数组进行排序
     * 2.循环数组，从第0位开始取数，不断叠加直到（c[i]/target的除数）
     * 3.如 == target,则保存下来
     * 4.如 > target，则--当前数的个数，循环步骤2
     * 5.如 < target，则取下一位
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> retList = new ArrayList<>();
        //排序
        Arrays.sort(candidates);
        //递归循环
        combinationSum(candidates, target, 0, retList, new ArrayList<>(), 0);

        return retList;
    }

    private void combinationSum(int[] candidates,
                                int target,
                                int index,
                                List<List<Integer>> retList,
                                List<Integer> addList,
                                int addSum) {
        if (index >= candidates.length) return;
        int n = (target - addSum) / candidates[index];
        if (n == 0) return;
        //全部添加到队列中
        for (int i = 0; i <= n; i++) {
            addList.add(candidates[index]);
        }
        int nSum = (n+1) * candidates[index];
        for (int i = n; i >= 0; i--) {
            //每次减-后，更新sum和list
            nSum -= candidates[index];
            addList.remove(addList.size() - 1);
            if (nSum + addSum == target) {
                //等于，添加到队列中
                retList.add(new ArrayList<>(addList));
            } else if (nSum + addSum > target) {
                //大于，--i
            } else {
                //小于，向后取数
                combinationSum(candidates, target, index + 1, retList, addList, addSum + nSum);
            }
        }
    }
}
