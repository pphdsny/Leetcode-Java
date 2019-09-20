package pp.arithmetic.leetcode;

import java.util.Stack;

/**
 * Created by wangpeng on 2019-09-12.
 * 581. 最短无序连续子数组
 * <p>
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * <p>
 * 你找到的子数组应是最短的，请输出它的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 说明 :
 * <p>
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _581_findUnsortedSubarray {

    public static void main(String[] args) {
        _581_findUnsortedSubarray findUnsortedSubarray = new _581_findUnsortedSubarray();
        System.out.println(findUnsortedSubarray.findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(findUnsortedSubarray.findUnsortedSubarray(new int[]{8, 6, 4, 8, 10, 9, 15}));
        System.out.println(findUnsortedSubarray.findUnsortedSubarray(new int[]{2, 3, 3, 2, 4}));
        System.out.println(findUnsortedSubarray.findUnsortedSubarray(new int[]{1, 3, 2, 2, 2}));
        System.out.println(findUnsortedSubarray.findUnsortedSubarray(new int[]{1, 1}));
        System.out.println(findUnsortedSubarray.findUnsortedSubarray(new int[]{1, 2, 4, 5, 3}));
    }

    /**
     * 解题思路：
     * 核心是我们需要找到无序子数组中最小元素和最大元素分别对应的正确位置
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }
}
