package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2019-08-30.
 * 448. 找到所有数组中消失的数字
 * <p>
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * <p>
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * <p>
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * 输出:
 * [5,6]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _448_findDisappearedNumbers {

    public static void main(String[] args) {
        _448_findDisappearedNumbers findDisappearedNumbers = new _448_findDisappearedNumbers();
        Util.printList(findDisappearedNumbers.findDisappearedNumbers(new int[]{4, 3, 2, 1, 8, 3, 2, 1}));
        Util.printList(findDisappearedNumbers.findDisappearedNumbers2(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
    }

    /**
     * 解题思路：
     * 简单求解:用一个hash映射表保存出现过的数字，再遍历保存结果找出未出现的数字（使用了O(n)的额外空间）==>{findDisappearedNumbers2}
     * <p>
     * 优化求解：去除O(n)的空间
     * 1.遍历nums,将item对应的位置+n，用于标识是否出现过（注意对item取模）
     * 2.再次遍历nums，找到小于n的位置就是未出现的数字了
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> retList = new ArrayList<>();

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int index = (nums[i] - 1) % n;
            nums[index] += n;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                retList.add(i + 1);
            }
        }

        return retList;
    }

    public List<Integer> findDisappearedNumbers2(int[] nums) {
        List<Integer> retList = new ArrayList<>();
        boolean[] allNums = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            allNums[nums[i] - 1] = true;
        }
        for (int i = 0; i < allNums.length; i++) {
            boolean item = allNums[i];
            if (!item) retList.add(i + 1);
        }
        return retList;
    }
}
