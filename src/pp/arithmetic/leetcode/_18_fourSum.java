package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangpeng on 2019-04-10.
 * 18. 四数之和
 * <p>
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 *
 * @see <a href="https://leetcode-cn.com/problems/4sum/">4sum</a>
 */
public class _18_fourSum {

    public static void main(String[] args) {
        _18_fourSum fourSum = new _18_fourSum();
        List<List<Integer>> lists = fourSum.fourSum(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0);
        for (int i = 0; i < lists.size(); i++) {
            Util.printList(lists.get(i));
        }
    }

    /**
     * 解题思路：
     * 1、先对数组进行排序
     * 2、先用四个指针i1=0,i2=1,i3=len-2,i4=len-1，分别指向数组开头和结尾两处
     * 3、先控制i1,i4不动，i2,i3向中心遍历，寻找满足条件的所有情况
     * 4、i1,i4再想内部移动，重复步骤#3
     * 5、注意遍历过程总重复数据的问题
     * <p>
     * 首次提交：
     * 执行用时 : 77 ms, 在4Sum的Java提交中击败了64.43% 的用户
     * 内存消耗 : 39.5 MB, 在4Sum的Java提交中击败了13.83% 的用户
     * 时间复杂度在O(n^3)级别，待优化
     * 优化方案：添加一些异常情况，提前终止遍历
     * 优化提交：
     * 执行用时 : 26 ms, 在4Sum的Java提交中击败了96.13% 的用户
     * 内存消耗 : 39.7 MB, 在4Sum的Java提交中击败了13.83% 的用户
     *
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> retList = new ArrayList<>();
        int length = nums.length;
        if (length < 4) {
            return retList;
        }
        //先排序
        Arrays.sort(nums);
        //定义四个指针
        int i1 = 0, i4 = length - 1;
        int i2, i3;
        while (i1 < length - 2) {
            //添加终止条件
            if (nums[i1] * 4 > target) {
                break;
            }
            if (i1 >= i4 - 2 || nums[i1] + nums[i4] * 3 < target) {
                i1 = getNextIndex(nums, i1);
                i4 = length - 1;
                continue;
            }
            i2 = i1 + 1;
            i3 = i4 - 1;
            while (i2 < i3) {
                int sum = nums[i1] + nums[i2] + nums[i3] + nums[i4];
                if (sum == target) {
                    retList.add(Arrays.asList(nums[i1], nums[i2], nums[i3], nums[i4]));
                    i2 = getNextIndex(nums, i2);
                    i3 = getPreIndex(nums, i3);
                    continue;
                }
                if (sum > target) {
                    i3 = getPreIndex(nums, i3);
                    continue;
                }
                if (sum < target) {
                    i2 = getNextIndex(nums, i2);
                    continue;
                }
            }
            i4 = getPreIndex(nums, i4);
        }

        return retList;
    }

    /**
     * 获取下个不重复index
     *
     * @param nums
     * @param ci
     * @return
     */
    private int getNextIndex(int[] nums, int ci) {
        int c = nums[ci];
        for (int i = ci + 1; i < nums.length; i++) {
            if (nums[i] != c) {
                return i;
            }
        }
        return nums.length - 1;
    }

    /**
     * 取前一个不重复的index
     *
     * @param nums
     * @param ci
     * @return
     */
    private int getPreIndex(int[] nums, int ci) {
        int c = nums[ci];
        for (int i = ci - 1; i >= 0; i--) {
            if (nums[i] != c) {
                return i;
            }
        }
        return 0;
    }
}
