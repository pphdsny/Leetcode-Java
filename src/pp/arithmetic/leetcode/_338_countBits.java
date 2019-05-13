package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-05-13.
 * 338. 比特位计数
 * <p>
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 * <p>
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * @see <a href="https://leetcode-cn.com/problems/counting-bits/">counting-bits</a>
 */
public class _338_countBits {
    public static void main(String[] args) {
        _338_countBits countBits = new _338_countBits();
        Util.printArray(countBits.countBits(2));
        Util.printArray(countBits.countBits(5));
    }

    /**
     * 题目已经强调了需要O(n)的复杂度，只能遍历一遍，可以考虑动态规划
     * 根据题目意思，先手动画一下数字和2进制的具体映射关系
     * 数字   0    1   2   3   4   5   6   7   8
     * 二进   0    1   10  11  100 101 110 111 1000
     * 1个数  0   1   1   2   1   2   2   3   1
     * 根据递推效果，看着好像没有什么规律
     * 但是仔细思考下，10进制转2进制必须要除以2，有些能整除，有些不能整除
     * 不能整除的3的1个数=3/1=数字1的1个数+1
     * 能整除的4的的1个数=4/2=数字2的1个数
     * 拿其他数字验证后发现的确是这个规律，得到动态规划状态转移方程：
     * int d = i / 2;
     * int m = i % 2;
     * if (m == 0) {
     *  dp[i] = dp[d];
     * } else {
     *  dp[i] = dp[d] + 1;
     * }
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        if (num < 0) return new int[0];
        int[] dp = new int[num + 1];
        dp[0] = 0;
        for (int i = 1; i <= num; i++) {
            int d = i / 2;
            int m = i % 2;
            if (m == 0) {
                dp[i] = dp[d];
            } else {
                dp[i] = dp[d] + 1;
            }
        }

        return dp;
    }
}
