package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2018/9/7.
 * 78. 子集
 * <p>
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 *
 * @see <a href="https://leetcode-cn.com/problems/subsets/description/">subsets</a>
 */
public class _78_subsets {
    public static void main(String[] args) {
        List<List<Integer>> subsets = subsets(new int[]{1, 2, 3});
        for (int i = 0; i < subsets.size(); i++) {
            Util.printList(subsets.get(i));
        }
        System.out.println("------------");
        List<List<Integer>> subsets2 = subsets2(new int[]{1, 2, 3});
        for (int i = 0; i < subsets2.size(); i++) {
            Util.printList(subsets2.get(i));
        }
    }

    /**
     * 回溯法
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        result.add(item);
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
        //添加自己
        item.add(nums[index]);
        result.add(new ArrayList<>(item));
        generate(nums, index + 1, result, item);
        //移除自己
        item.remove(new Integer(nums[index]));
        generate(nums, index + 1, result, item);
    }

    /**
     * 方法二：位运算法
     * 每一位都有可能有或者没有，例如【1，2，3】，每一位100,101,110,111,010,011,001,000
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < 1 << nums.length; i++) {
            List<Integer> item = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    item.add(nums[j]);
                }
            }
            result.add(item);
        }
        return result;
    }
}
