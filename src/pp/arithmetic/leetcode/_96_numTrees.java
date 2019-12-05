package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-12-04.
 *
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _96_numTrees {

    public static void main(String[] args) {
        _96_numTrees numTrees = new _96_numTrees();
        System.out.println(numTrees.numTrees(1));
        System.out.println(numTrees.numTrees(2));
        System.out.println(numTrees.numTrees(3));
        System.out.println(numTrees.numTrees(4));
        System.out.println(numTrees.numTrees(5));
    }

    /**
     * 解法一：{@link _96_numTrees#dfs(int, int)}，直接利用DFS进行求解，待优化
     * 解法二：{@link _96_numTrees#dp(int)}，将二叉树编译转换为动态规范转换
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        return dp(n);
    }

    /**
     * 解题思路（动态规划）
     * 1、将大问题转换为小问题：第i位的个数，再求和
     * 2、第i位的个数 += dp[j]（左） * dp[i - j - 1]（右） (j=0,j<i,j++)
     * 简单来说，当我要求n=2的时候，我需要n=1，但是n=1可能在2的左边或者右边，另一边就是0
     *
     * 执行用时 : 1 ms, 在所有 java 提交中击败了24.63%的用户
     * 内存消耗 :32.9 MB, 在所有 java 提交中击败了9.48%的用户
     *
     * @param n
     * @return
     */
    public int dp(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }


    /**
     * 解题思路：
     * 对于树的题目就是进行遍历求解，本题可以考虑用FDS
     * 1、遍历1..n，每一位都可以作为根节点的一种
     * 2、i作为根节点的时候，求解1..i-1（左子树）和i+1..n（右子树）的个数
     * 3、左右子树的个数也可以用步骤1递归求解
     * 4、最终结果：（左子树个数*右子树个数）再从1..n求和
     *
     * 提交通过，但是执行耗时并不是很优
     *
     * 执行用时 :1929 ms, 在所有 java 提交中击败了5.02%的用户
     * 内存消耗 :33 MB, 在所有 java 提交中击败了9.48%的用户
     * @param si
     * @param ei
     * @return
     */
    public int dfs(int si, int ei) {
        if (si >= ei) return 1;
        int sum = 0;
        for (int i = si; i <= ei; i++) {
            int leftCount = dfs(si, i - 1);
            int rightCount = dfs(i + 1, ei);
            sum += (leftCount * rightCount);
        }
        return sum;
    }
}
