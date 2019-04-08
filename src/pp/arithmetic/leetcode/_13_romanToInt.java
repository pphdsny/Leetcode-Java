package pp.arithmetic.leetcode;

import javax.swing.plaf.TextUI;

/**
 * Created by wangpeng on 2019-04-08.
 * 13. 罗马数字转整数
 * <p>
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "III"
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: "IV"
 * 输出: 4
 * 示例 3:
 * <p>
 * 输入: "IX"
 * 输出: 9
 * 示例 4:
 * <p>
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 * <p>
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * @see <a href="https://leetcode-cn.com/problems/roman-to-integer/">roman-to-integer</a>
 */
public class _13_romanToInt {

    public static void main(String[] args) {
        _13_romanToInt romanToInt = new _13_romanToInt();
        System.out.println(romanToInt.romanToInt("III"));
        System.out.println(romanToInt.romanToInt("IV"));
        System.out.println(romanToInt.romanToInt("IX"));
        System.out.println(romanToInt.romanToInt("LVIII"));
        System.out.println(romanToInt.romanToInt("MCMXCIV"));
    }

    /**
     * 解题思路：
     * 借鉴 {@link _12_intToRoman#intToRoman2(int) } 中的数组替换的思想，反向求对于数字
     * <p>
     * 执行用时 : 26 ms, 在Roman to Integer的Java提交中击败了100.00% 的用户
     * 内存消耗 : 44 MB, 在Roman to Integer的Java提交中击败了65.57% 的用户
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int retNum = 0;
        String[] romanStrs = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] romanInts = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int startI = 0, index = 0;
        while (startI < s.length() && index < romanStrs.length) {
            int romanLen = romanStrs[index].length();
            if (startI + romanLen > s.length()) {
                index++;
                continue;
            }
            while (startI < s.length() && startI + romanLen <= s.length()
                    && romanStrs[index].equals(s.substring(startI, startI + romanLen))) {
                retNum += romanInts[index];
                startI += romanLen;
            }
            index++;
        }

        return retNum;
    }
}
