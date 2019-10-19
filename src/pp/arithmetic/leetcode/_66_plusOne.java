package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2019-10-19.
 * 66. 加一
 * <p>
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 * <p>
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _66_plusOne {

    public static void main(String[] args) {
        _66_plusOne plusOne = new _66_plusOne();
        Util.printArray(plusOne.plusOne(new int[]{1, 2, 3}));
        Util.printArray(plusOne.plusOne(new int[]{4, 3, 2, 1}));
        Util.printArray(plusOne.plusOne(new int[]{9, 9, 9}));
        Util.printArray(plusOne.plusOne(new int[]{0}));
        Util.printArray(plusOne.plusOne(new int[]{9}));
    }

    /**
     * 解题思路：
     * 一道算数加法+1，需要注意两种情况：
     * 1、低位向高位进位 ==> 从末尾开始遍历
     * 2、整数头需要进位 ==> 构建一个新数组保存返回结果
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        List<Integer> retList = new ArrayList<>();
        int highAdd = 1;    //进位
        for (int i = digits.length - 1; i >= 0; i--) {
            int newDigit = digits[i] + highAdd;
            highAdd = newDigit / 10;
            newDigit = newDigit % 10;
            retList.add(0, newDigit);
        }
        if (highAdd > 0) retList.add(0, highAdd);
        //listToArr
        int[] retArr = new int[retList.size()];
        for (int i = 0; i < retList.size(); i++) {
            retArr[i] = retList.get(i);
        }

        return retArr;
    }
}
