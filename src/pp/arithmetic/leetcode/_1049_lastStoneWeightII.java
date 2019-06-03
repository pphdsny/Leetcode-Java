package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-06-03.
 * 1049. 最后一块石头的重量 II
 * <p>
 * 有一堆石头，每块石头的重量都是正整数。
 * <p>
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 *
 * @see <a href="https://leetcode-cn.com/problems/last-stone-weight-ii/">last-stone-weight-ii</a>
 */
public class _1049_lastStoneWeightII {
    public static void main(String[] args) {
        _1049_lastStoneWeightII lastStoneWeightII = new _1049_lastStoneWeightII();
//        System.out.println(lastStoneWeightII.lastStoneWeightII(new int[]{2, 7, 4, 8, 1, 1}));
//        System.out.println(lastStoneWeightII.lastStoneWeightII(new int[]{6, 2, 2, 6, 5, 7, 7}));
        System.out.println(lastStoneWeightII.lastStoneWeightII(new int[]{31, 26, 33, 21, 40}));
    }

    /**
     * 解题思路：
     * 1.因为需要数组相减得到最少值，所以可以分成两个一样大小的数组
     * 2.往数组中尽可能装满，转换为背包问题
     *
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        /* 由于石头拿走还能放回去，因此可以简单地把所有石头看作两堆
         * 假设总重量为 sum, 则问题转化为背包问题：如何使两堆石头总重量接近 sum / 2
         */
        int len = stones.length;
        /* 获取石头总重量 */
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }
        /* 定义 dp[i] 重量上限为 i 时背包所能装载的最大石头重量 */
        int maxCapacity = sum / 2;
        int[] dp = new int[maxCapacity + 1];
        for (int i = 0; i < len; i++) {
            int curStone = stones[i];
            for (int j = maxCapacity; j >= curStone; j--) {
                dp[j] = Math.max(dp[j], dp[j - curStone] + curStone);
            }
        }
        return sum - 2 * dp[maxCapacity];
    }
}
