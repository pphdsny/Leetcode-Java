package pp.arithmetic.leetcode;

import java.util.HashMap;

/**
 * Created by wangpeng on 2019-05-09.
 * 264. 丑数 II
 * <p>
 * 编写一个程序，找出第 n 个丑数。
 * <p>
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:
 * <p>
 * 1 是丑数。
 * n 不超过1690。
 *
 * @see <a href="https://leetcode-cn.com/problems/ugly-number-ii/">ugly-number-ii</a>
 */
public class _264_nthUglyNumber {

    public static void main(String[] args) {
        _264_nthUglyNumber nthUglyNumber = new _264_nthUglyNumber();
        System.out.println(nthUglyNumber.nthUglyNumber(10));
        long start = System.currentTimeMillis();
        System.out.println(nthUglyNumber.nthUglyNumber(431));
        long end = System.currentTimeMillis();
        System.out.println("循环法计算431耗时：" + (end - start));
        start = System.currentTimeMillis();
        System.out.println(nthUglyNumber.nthUglyNumber2(431));
        end = System.currentTimeMillis();
        System.out.println("三指针法计算431耗时：" + (end - start));
    }

    /**
     * 解题二：动态规划+三指针
     * dp保存按序排列的丑数，三指针分别是*2，*3，*5，找出下一个丑数
     *
     * @param n
     * @return
     */
    public int nthUglyNumber2(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < n; i++) {
            int min = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5));
            if (min == dp[i2] * 2) i2++;
            if (min == dp[i3] * 3) i3++;
            if (min == dp[i5] * 5) i5++;
            dp[i] = min;
        }

        return dp[n - 1];
    }

    private HashMap<Integer, Boolean> map = new HashMap<>();

    /**
     * 丑数求解过程：首先除2，直到不能整除为止，然后除5到不能整除为止，然后除3直到不能整除为止。
     * 最终判断剩余的数字是否为1，如果是1则为丑数，否则不是丑数
     * <p>
     * 解题思路：
     * 从1开始遍历，按丑数求解过程找出满足条件的第n个丑数（提交超时）
     * 思路优化（如何利用之前的计算）
     *
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        map.put(1, true);
        int uglyCount = 1;
        int retVal = 1;
        for (int i = 2; i < Integer.MAX_VALUE; i++) {
            if (uglyCount >= n) {
                break;
            }
            boolean isUgly = isUglyNumber(i);
            if (isUgly) {
                map.put(i, true);
                uglyCount++;
                retVal = i;
            }
        }

        return retVal;
    }

    private boolean isUglyNumber(int num) {
        while (num % 2 == 0) {
            num = num / 2;
            if (map.containsKey(num)) return true;
        }
        while (num % 5 == 0) {
            num = num / 5;
            if (map.containsKey(num)) return true;
        }
        while (num % 3 == 0) {
            num = num / 3;
            if (map.containsKey(num)) return true;
        }
        return num == 1;
    }
}
