package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/11/1.
 * 190. 颠倒二进制位
 * <p>
 * 颠倒给定的 32 位无符号整数的二进制位。
 * <p>
 * 示例:
 * <p>
 * 输入: 43261596
 * 输出: 964176192
 * 解释: 43261596 的二进制表示形式为 00000010100101000001111010011100 ，
 * 返回 964176192，其二进制表示形式为 00111001011110000010100101000000 。
 * 进阶:
 * 如果多次调用这个函数，你将如何优化你的算法？
 *
 * @see <a href="https://leetcode-cn.com/problems/reverse-bits/description/">reverse-bits</a>
 */
public class _190_reverseBits {
    public static void main(String[] args) {
        int i = reverseBits(1);
        System.out.println(i);
    }

    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += n & 1;
            n >>= 1;
            if (i < 31) {
                result <<= 1;
            }
        }
        return result;
    }
    /**
     * 反转操作总结
     * - 求回文
     *  - 字符串回文：从头和尾以此遍历,最简单
     *  - 数字回文：n/10,构建一半，是否相等 _9_isPalindrome
     *  - 链表回文：链表反转一半 _234_isPalindrome
     * - 反转
     *  - 数字反转：n/10，注意Int越界 _7_reverse
     *  - 二进制反转：>>1 _190_reverseBits
     */
}
