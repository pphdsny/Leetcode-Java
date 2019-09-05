package pp.arithmetic.leetcode;

import java.util.HashMap;

/**
 * Created by wangpeng on 2019-09-05.
 * 560. 和为K的子数组
 * <p>
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * <p>
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _560_subarraySum {

    public static void main(String[] args) {
        _560_subarraySum subarraySum = new _560_subarraySum();
        System.out.println(subarraySum.subarraySum(new int[]{1,1,1,1,1,1},2));
        System.out.println(subarraySum.subarraySum2(new int[]{1,1,1,1,1,1},2));
    }

    /**
     * 解题思路：
     * 由于数组中存在正数和负数，所以求和必须得所有的0-n之间的所有区间都得统计
     * 暴力就是三层循环，可以利用之前的计算结果
     * sum[i]保存0-i之间的和，求解i-j就使用sum[j]-sum[i]
     *
     * 执行用时 :488 ms, 在所有 Java 提交中击败了7.40%的用户
     * 内存消耗 :43.4 MB, 在所有 Java 提交中击败了71.76%的用户
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int[] sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        for (int start = 0; start < nums.length; start++) {
            for (int end = start + 1; end <= nums.length; end++) {
                if (sum[end] - sum[start] == k)
                    count++;
            }
        }
        return count;
    }

    /**
     * 优化求解：
     * 核心思想：sum[i]代表0-i的总和，如果sum[i]-sum[j]==k，那么sum[j]对于的个数就是区间i-j的满足条件和
     * 使用一个map保存sum<->count之间的对应关系
     * O(n)复杂度就能找出结果
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum2(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;

    }
}
