package pp.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2019-04-22.
 * 1025. 除数博弈
 * <p>
 * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
 * <p>
 * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
 * <p>
 * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
 * 用 N - x 替换黑板上的数字 N 。
 * 如果玩家无法执行这些操作，就会输掉游戏。
 * <p>
 * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：2
 * 输出：true
 * 解释：爱丽丝选择 1，鲍勃无法进行操作。
 * 示例 2：
 * <p>
 * 输入：3
 * 输出：false
 * 解释：爱丽丝选择 1，鲍勃也选择 1，然后爱丽丝无法进行操作。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 1000
 *
 * @see <a href="https://leetcode-cn.com/problems/divisor-game/">divisor-game</a>
 */
public class _1025_divisorGame {
    public static void main(String[] args) {
        _1025_divisorGame divisorGame = new _1025_divisorGame();
        System.out.println(divisorGame.divisorGame(3));
        System.out.println(divisorGame.divisorGame(4));
    }

    /**
     * 解题思路：
     * 爱丽丝可能有多种结局么？论证发现只要N给定了，结局也就定了，偶数爱丽丝必胜
     * 但是我们为了练习动态规划（DP）,还是从动态规划的角度来思考下
     * 动态规划解题四部曲，可供参考
     * <p>
     * - 确认原问题与子问题=>原问题：对于N来说爱丽丝是否能赢，子问题：对于i来说爱丽丝是否会赢
     * - 确认状态=>
     * - 确认边界状态的值=>dp[1]=false;dp[2]=true
     * - 确定状态转移方程=>dp[i]=!dp[i-1]
     *
     * 没看明白题目，没意思
     *
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {
        //把偶数留给自己, 把奇数留给对手, 最后剩2的时候选择1即可赢得比赛.
        //若己方拿到的是偶数, 每次选择 1, 就可以把奇数留给对手, 己方赢.
        //若己方拿到的是奇数, 一定不能选择偶数, 留给对手的一定是偶数, 对手可以留给你奇数, 对手赢.
        //动态规划，初始值。
        if (N == 1) return false;

        boolean[] dp = new boolean[N + 1];
        dp[1] = false;

        for (int i = 2; i <= N; i++) {
            dp[i] = !dp[i - 1];
        }
        return dp[N];
    }
}
