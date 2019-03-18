package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangpeng on 2018/9/10.
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 * @see <a href="https://leetcode-cn.com/problems/combination-sum-ii/description/">combination-sum-ii</a>
 */
public class _40_combinationSum2 {
    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        for (int i = 0; i < lists.size(); i++) {
            Util.printList(lists.get(i));
        }
        Util.printDivideLine();
        List<List<Integer>> lists2 = combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
        for (int i = 0; i < lists2.size(); i++) {
            Util.printList(lists2.get(i));
        }
    }

    /**
     * 最直接的解法，遍历求出子集，求每个子集的和是否满足==target，不过这样子效率会很低。
     * 优化：
     * 1、如果某个集合之和>target，则直接跳过
     * 2、包含>target的集合的集合也直接跳过
     * 3、排序从大开始
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        generate(candidates, 0, target, result, new ArrayList<>());
        return result;
    }

    private static void generate(int[] nums,
                                 int index,
                                 int target,
                                 List<List<Integer>> result,
                                 List<Integer> item) {
        if (index >= nums.length
                || target < 0) {
            return;
        }
        item.add(nums[index]);
        target -= nums[index];
        //==target&&不能有重复的
        if (0 == target) {
            result.add(new ArrayList<>(item));
        }
        generate(nums, index + 1, target, result, item);
        Integer remove = item.remove(item.size() - 1);
        target += remove;
        int i = index;
        while (i < nums.length - 1 && nums[i + 1] == remove) {
            i++;
        }
        generate(nums, i + 1, target, result, item);
    }
}
