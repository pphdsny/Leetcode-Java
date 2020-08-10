package pp.arithmetic.offer;

/**
 * Created by wangpeng on 2020-08-10.
 * 剑指 Offer 15. 二进制中1的个数
 *
 * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 *
 * 示例 1：
 *
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * 示例 2：
 *
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 * 示例 3：
 *
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _15_hammingWeight {

    public static void main(String[] args) {
        _15_hammingWeight hammingWeight = new _15_hammingWeight();
        System.out.println(hammingWeight.hammingWeight(11));
        System.out.println(hammingWeight.hammingWeight(128));
//        System.out.println(hammingWeight.hammingWeight(4294967293));
    }


    /**
     * 解题思路：
     * 如果n%2!=0，则1的个数+1，直到n=1
     *
     * 注意无符号的，对应int会超 右移动使用>>>（无符号右移）
     * @param n
     * @return
     */
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int retVal = 0;
        while (n != 0) {
            retVal += n & 1;
            n = n >>> 1;
        }

        return retVal;
    }
}
