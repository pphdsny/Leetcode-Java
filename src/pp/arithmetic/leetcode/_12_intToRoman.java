package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-04-08.
 * 12. 整数转罗马数字
 * <p>
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
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
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 3
 * 输出: "III"
 * 示例 2:
 * <p>
 * 输入: 4
 * 输出: "IV"
 * 示例 3:
 * <p>
 * 输入: 9
 * 输出: "IX"
 * 示例 4:
 * <p>
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例 5:
 * <p>
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * @see <a href="https://leetcode-cn.com/problems/integer-to-roman/">integer-to-roman</a>
 */
public class _12_intToRoman {

    public static void main(String[] args) {
        _12_intToRoman intToRoman = new _12_intToRoman();
//        System.out.println(intToRoman.intToRoman(3));
//        System.out.println(intToRoman.intToRoman(4));
//        System.out.println(intToRoman.intToRoman(9));
//        System.out.println(intToRoman.intToRoman(58));
//        System.out.println(intToRoman.intToRoman(1994));
        System.out.println(intToRoman.intToRoman2(3));
        System.out.println(intToRoman.intToRoman2(4));
        System.out.println(intToRoman.intToRoman2(9));
        System.out.println(intToRoman.intToRoman2(58));
        System.out.println(intToRoman.intToRoman2(1994));
    }

    /**
     * 解题思路：
     * 1、将待转换的数字拆分成个十百千
     * 2、逐步求解各位对应的罗马数字
     * 解题过程略显麻烦，但是这是基本的解题过程，可以抽象一下，见解法二{@link _12_intToRoman#intToRoman2(int)}
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuilder builder = new StringBuilder();
        //千,千<3999，不存在4这种特殊情况
        int qi = num / 1000;
        if (qi > 0) {
            for (int i = 0; i < qi; i++) {
                builder.append("M");
            }
            num -= qi * 1000;
        }
        //百
        int bi = num / 100;
        if (bi > 0) {
            if (bi < 4) {
                for (int i = 0; i < bi; i++) {
                    builder.append("C");
                }
            } else if (bi == 4) {
                builder.append("CD");
            } else if (bi == 5) {
                builder.append("D");
            } else if (bi < 9) {
                builder.append("D");
                for (int i = 0; i < bi - 5; i++) {
                    builder.append("C");
                }
            } else {
                builder.append("CM");
            }
            num -= bi * 100;
        }
        //十
        int si = num / 10;
        if (si > 0) {
            if (si < 4) {
                for (int i = 0; i < si; i++) {
                    builder.append("X");
                }
            } else if (si == 4) {
                builder.append("XL");
            } else if (si == 5) {
                builder.append("L");
            } else if (si < 9) {
                builder.append("L");
                for (int i = 0; i < si - 5; i++) {
                    builder.append("X");
                }
            } else {
                builder.append("XC");
            }
            num -= si * 10;
        }
        //个
        int gi = num;
        if (gi > 0) {
            if (gi < 4) {
                for (int i = 0; i < gi; i++) {
                    builder.append("I");
                }
            } else if (gi == 4) {
                builder.append("IV");
            } else if (gi == 5) {
                builder.append("V");
            } else if (gi < 9) {
                builder.append("V");
                for (int i = 0; i < gi - 5; i++) {
                    builder.append("I");
                }
            } else {
                builder.append("IX");
            }
        }
        return builder.toString();
    }

    /**
     * 解法二：利用一个数组将上述循环过程简化
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * <p>
     * 执行用时 : 21 ms, 在Integer to Roman的Java提交中击败了100.00% 的用户
     * 内存消耗 : 40.8 MB, 在Integer to Roman的Java提交中击败了65.63% 的用户
     *
     * @param num
     * @return
     */
    public String intToRoman2(int num) {
        StringBuilder builder = new StringBuilder();
        String[] romanStrs = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] romanInts = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int index = 0;
        while (num > 0) {
            while (num >= romanInts[index]) {
                builder.append(romanStrs[index]);
                num -= romanInts[index];
            }
            index++;
        }

        return builder.toString();
    }
}
