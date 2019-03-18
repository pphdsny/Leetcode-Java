package pp.arithmetic.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by wangpeng on 2018-12-10.
 * 354. 俄罗斯套娃信封问题
 * <p>
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * <p>
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * <p>
 * 说明:
 * 不允许旋转信封。
 * <p>
 * 示例:
 * <p>
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 *
 * @see <a href="https://leetcode-cn.com/problems/russian-doll-envelopes/description/">russian-doll-envelopes</a>
 */
public class _354_maxEnvelopes {

    public static void main(String[] args) {
        //3
        System.out.println(maxEnvelopes(new int[][]{
                {46,89},{50,53},{52,68},{72,45},{77,81}
        }));
        //3
        System.out.println(maxEnvelopes(new int[][]{
                {5, 4}, {6, 4}, {6, 7}, {2, 3}
        }));
        //3
        System.out.println(maxEnvelopes(new int[][]{
                {30, 50}, {12, 2}, {3, 4}, {12, 15}
        }));
        //3
        System.out.println(maxEnvelopes(new int[][]{
                {1, 3}, {3, 5}, {6, 7}, {6, 8}, {8, 4}, {9, 5}
        }));
        //5
        System.out.println(maxEnvelopes(new int[][]{
                {2, 100}, {3, 200}, {4, 300}, {5, 500}, {5, 400}, {5, 250}, {6, 370}, {6, 360}, {7, 380}
        }));
        //5
        System.out.println(maxEnvelopes(new int[][]{
                {15, 8}, {2, 20}, {2, 14}, {4, 17}, {8, 19}, {8, 9}, {5, 7}, {11, 19}, {8, 11}, {13, 11}, {2, 13}, {11, 19}, {8, 11}, {13, 11}, {2, 13}, {11, 19}, {16, 1}, {18, 13}, {14, 17}, {18, 19}
        }));
    }

    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length < 1) {
            return 0;
        }
        sort(envelopes);
        int[] dp = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            dp[i] = 1;
        }
        int ret = 1;
        //由于叠加的顺序可能不一样，所以需要暴力的去便利i下面的每一种情况
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ret = Math.max(dp[i], ret);
        }
        return ret;
    }

    private static void sort(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
    }
}
