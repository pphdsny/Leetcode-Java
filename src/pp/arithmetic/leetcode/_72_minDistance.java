package pp.arithmetic.leetcode;

import static java.lang.Math.min;

/**
 * Created by wangpeng on 2019-05-05.
 * 72. 编辑距离
 * <p>
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * 示例 1:
 * <p>
 * 输入: word1 = "horse", word2 = "ros"
 * 输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:
 * <p>
 * 输入: word1 = "intention", word2 = "execution"
 * 输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * @see <a href="https://leetcode-cn.com/problems/edit-distance/">edit-distance</a>
 */
public class _72_minDistance {

    public static void main(String[] args) {
        _72_minDistance distance = new _72_minDistance();
        System.out.println(distance.minDistance("horse", "ros"));
        System.out.println(distance.minDistance("intention", "execution"));
    }

    /**
     * 解题思路：
     * 二维数组保存一一配对的所需的步数，int[][] dp
     * w1 = "horse", w2 = "ros"
     * 如果遍历的两个字符串相等，则步数不改变
     * 如果不相等则步数一定+1
     * 替换 dp[i-1][j-1]
     * 插入 dp[i][j-1]
     * 删除 dp[i-1][j]
     * 取上面三个操作中的最小的一个
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int l1 = word1.length();
        int l2 = word2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];
        //初始化
        for (int i = 0; i <= l1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= l2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    dp[i][j] = min(dp[i - 1][j - 1], min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[l1][l2];
    }
}
