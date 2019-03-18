package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by wangpeng on 2018/9/10.
 * 90.子集 II
 * <p>
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,2]
 * 输出:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 *
 * @see <a href="https://leetcode-cn.com/problems/subsets-ii/description/">subsets-ii</a>
 */
public class _90_subsetsWithDup {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<List<Integer>> lists = subsetsWithDup(new int[]{2, 3, 4, 5, 2, 3, 4, 1, 2, 2});
        long end = System.currentTimeMillis();
        System.out.println("subsetsWithDup:" + (end - start));
        for (int i = 0; i < lists.size(); i++) {
            Util.printList(lists.get(i));
        }
        System.out.println();
        start = System.currentTimeMillis();
        List<List<Integer>> lists1 = subsetsWithDupByOther(new int[]{2, 3, 4, 5, 2, 3, 4, 1, 2, 2});
        end = System.currentTimeMillis();
        System.out.println("subsetsWithDupByOther:" + (end - start));
        for (int i = 0; i < lists1.size(); i++) {
            Util.printList(lists1.get(i));
        }
    }

    /**
     * 他人的解法：利用回溯过程中就将一些不必要的分支进行修剪，避免浪费
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDupByOther(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), 0, nums);
        return result;
    }

    public static void helper(List<List<Integer>> res, List<Integer> each, int pos, int[] n) {
        if (pos <= n.length) {
            res.add(each);
        }
        int i = pos;
        while (i < n.length) {
            each.add(n[i]);
            helper(res, new ArrayList<>(each), i + 1, n);
            each.remove(each.size() - 1);
            i++;
            while (i < n.length && n[i] == n[i - 1]) {
                i++;
            }
        }
        return;
    }

    /**
     * 自己的实现排序那边有些麻烦,时间复杂度高
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        result.add(new ArrayList<>());
        generate(nums, 0, result, item);
        return result;
    }

    private static void generate(int[] nums,
                                 int index,
                                 List<List<Integer>> result,
                                 List<Integer> item) {
        if (index >= nums.length) {
            return;
        }
        item.add(nums[index]);
        if (!contains(result, item)) {
            result.add(new ArrayList<>(item));
        }
        generate(nums, index + 1, result, item);
        item.remove(new Integer(nums[index]));
        generate(nums, index + 1, result, item);
    }


    private static boolean contains(List<List<Integer>> result, List<Integer> item) {
        for (int i = 0; i < result.size(); i++) {
            List<Integer> temp = result.get(i);
            if (temp.size() == item.size()) {
                Collections.sort(item);
                Collections.sort(temp);
                boolean isContain = true;
                for (int j = 0; j < temp.size(); j++) {
                    if (item.get(j) != temp.get(j)) {
                        isContain = false;
                        break;
                    }
                }
                if (isContain) {
                    return true;
                }
            }
        }
        return false;
    }
}
