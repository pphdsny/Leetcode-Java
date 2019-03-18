package pp.arithmetic.leetcode;

import java.util.HashMap;

/**
 * Created by wangpeng on 2019-02-26.
 * 128. 最长连续序列
 * <p>
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * <p>
 * 要求算法的时间复杂度为 O(n)。
 * <p>
 * 示例:
 * <p>
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * @see <a href="https://leetcode-cn.com/problems/longest-consecutive-sequence/">longest-consecutive-sequence</a>
 */
public class _128_longestConsecutive {
    public static void main(String[] args) {
        int longestConsecutive = new _128_longestConsecutive().longestConsecutive(new int[]{100, 2, 3, 299, 3, 4, 111, 6, 5, 1});
        int longestConsecutive2 = new _128_longestConsecutive().longestConsecutive(new int[]{100, 4, 200, 1, 3, 2});
        System.out.println(longestConsecutive);
        System.out.println(longestConsecutive2);
    }

    /**
     * 利用了并查集的思想
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) continue;
            //获取左右数字连续个数
            Integer leftCount = map.getOrDefault(num - 1, 0);
            Integer rightCount = map.getOrDefault(num + 1, 0);
            int count = leftCount + rightCount + 1;
            //更新
            map.put(num, count);
            map.put(num - leftCount, count);
            map.put(num + rightCount, count);
            max = Math.max(max, count);
        }
        return max;
    }

}
