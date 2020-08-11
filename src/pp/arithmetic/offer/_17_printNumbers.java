package pp.arithmetic.offer;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2020-08-11.
 *
 * 剑指 Offer 17. 打印从1到最大的n位数
 *
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *  
 *
 * 说明：
 *
 * 用返回一个整数列表来代替打印
 * n 为正整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _17_printNumbers {

    public static void main(String[] args) {
        _17_printNumbers printNumbers = new _17_printNumbers();
        Util.printArray(printNumbers.printNumbers(1));
        Util.printArray(printNumbers.printNumbers(2));
        Util.printArray(printNumbers.printNumbers(3));
    }

    /**
     * 解题思路：
     * 本题没有什么难度，唯一难的就是咋根据n构建出相应size的数组
     *
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        char[] len = new char[n];
        for (int i = 0; i < n; i++) {
            len[i] = '9';
        }
        int size = Integer.parseInt(new String(len));
        int[] retVal = new int[size];
        for (int i = 0; i < size; i++) {
            retVal[i] = i+1;
        }

        return retVal;
    }
}
