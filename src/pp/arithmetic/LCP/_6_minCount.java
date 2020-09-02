package pp.arithmetic.LCP;

/**
 * Created by wangpeng on 2020-09-02.
 * LCP 06. 拿硬币
 * <p>
 * 桌上有 n 堆力扣币，每堆的数量保存在数组 coins 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[4,2,1]
 * <p>
 * 输出：4
 * <p>
 * 解释：第一堆力扣币最少需要拿 2 次，第二堆最少需要拿 1 次，第三堆最少需要拿 1 次，总共 4 次即可拿完。
 * <p>
 * 示例 2：
 * <p>
 * 输入：[2,3,10]
 * <p>
 * 输出：8
 * <p>
 * 限制：
 * <p>
 * 1 <= n <= 4
 * 1 <= coins[i] <= 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/na-ying-bi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _6_minCount {

    public static void main(String[] args) {
        _6_minCount minCount = new _6_minCount();
        System.out.println(minCount.minCount(new int[]{4, 2, 1}));
        System.out.println(minCount.minCount(new int[]{2, 3, 10}));

    }

    /**
     * 解题思路：
     * 需要最少次数，利用贪心的思路，每次尽可能的多拿（也就是2个）
     *
     * @param coins
     * @return
     */
    public int minCount(int[] coins) {
        if (coins == null) return 0;
        int retVal = 0;
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            if (coin % 2 == 0) {
                retVal += coin / 2;
            } else {
                retVal += coin / 2 + 1;
            }
        }

        return retVal;
    }
}
