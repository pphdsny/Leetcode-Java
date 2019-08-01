package pp.arithmetic.leetcode;

import java.util.HashMap;

/**
 * Created by wangpeng on 2019-08-01.
 * 169. 求众数
 * <p>
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _169_majorityElement {
    public static void main(String[] args) {
        _169_majorityElement majorityElement = new _169_majorityElement();
        System.out.println(majorityElement.majorityElement(new int[]{3, 2, 3}));
        System.out.println(majorityElement.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    /**
     * 解题思路：
     * 如题所示，总是存在众数，所以是不是找出出现次数最大的数即可
     * 利用一个HashMap存储遍历的结果树，最终找到最大值
     * <p>
     * 提交结果：
     * 执行用时 :33 ms, 在所有 Java 提交中击败了25.67%的用户
     * 内存消耗 :50 MB, 在所有 Java 提交中击败了29.52%的用户
     * <p>
     * 时间复杂度O(n)，hashmap的扩容需要时间，有性能消耗
     * 优化方案
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxCount = Integer.MIN_VALUE;
        int maxValue = 0;
        for (int i = 0; i < nums.length; i++) {
            int item = nums[i];
            Integer count = map.getOrDefault(item, 0);
            map.put(item, ++count);
            if (count > maxCount) {
                maxCount = count;
                maxValue = item;
            }
        }
        return maxValue;
    }

    /**
     * 优化方案：减少HashMap的扩容消耗
     * 官方说明：投票法Boyer-Moore
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        int result = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                //因为众数个数>n/2，所以最后的result一定是众数
                result = nums[i];
                count++;
            } else if (result == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return result;
    }
}
