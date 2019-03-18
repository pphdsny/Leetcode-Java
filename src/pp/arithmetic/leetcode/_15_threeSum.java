package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.*;

/**
 * Created by wangpeng on 2019-02-20.
 * 15. 三数之和
 * <p>
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 *
 * @see <a href="https://leetcode-cn.com/problems/3sum/">3sum</a>
 */
public class _15_threeSum {

    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for (int i = 0; i < lists.size(); i++) {
            Util.printList(lists.get(i));
        }
        List<List<Integer>> lists1 = threeSum(new int[]{0, 0, 0});
        for (int i = 0; i < lists1.size(); i++) {
            Util.printList(lists1.get(i));
        }
    }

    /**
     * 时间负责度O(n^2)
     * <p>
     * 执行用时: 93 ms, 在3Sum的Java提交中击败了58.30% 的用户
     * 内存消耗: 48.7 MB, 在3Sum的Java提交中击败了35.24% 的用户
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> retList = new ArrayList<>();
        if (nums.length < 3) {
            return retList;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            //去重
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int target = -nums[i];
            int li = i + 1;
            int ri = nums.length - 1;
            while (ri > li && nums[i] + nums[li] + nums[ri] > 0) {
                //最小值加上右边界仍大于0，则右边界过大，缩小右边界
                ri--;
            }
            if (ri < li) {
                break;
            }
            while (li < ri) {
                int twoSum = nums[li] + nums[ri];
                if (twoSum == target) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[li]);
                    list.add(nums[ri]);
                    retList.add(list);
                    //避免重复
                    while (++li < ri && nums[li - 1] == nums[li]) {
                    }
                    while (--ri > li && nums[ri + 1] == nums[ri]) {
                    }
                } else if (twoSum > target) {
                    ri--;
                } else {
                    li++;
                }
            }
        }
        return retList;
    }

    /**
     * 时间负责度O(n^3)，提交超时
     *
     * @param nums
     * @return
     */
    @Deprecated
    public static List<List<Integer>> threeSum_d(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        List<List<Integer>> retList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        Collections.sort(list);
                        set.add(list);
                    }
                }
            }
        }
        for (List<Integer> value : set) {
            retList.add(value);
        }
        return retList;
    }
}
