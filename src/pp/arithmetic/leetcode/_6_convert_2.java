package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-03-18.
 * 6. Z 字形变换
 * <p>
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * @see <a href="https://leetcode-cn.com/problems/zigzag-conversion/">zigzag-conversion</a>
 */
public class _6_convert_2 {

    public static void main(String[] args) {
        _6_convert_2 convert = new _6_convert_2();
        System.out.println(convert.convert("LEETCODEISHIRING", 4));
        System.out.println(convert.convert("LE", 1));
    }

    /**
     * 执行用时 : 28 ms, 在ZigZag Conversion的Java提交中击败了96.91% 的用户
     * 内存消耗 : 40.3 MB, 在ZigZag Conversion的Java提交中击败了42.22% 的用户
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (s.length() <= numRows || numRows == 1) {
            return s;
        }
        int sl = s.length();
        //一个Z的长度(除去一边)，算一个周期
        int cl = numRows * 2 - 2;
        //总共这样子的Z有多少个，可能不完全
        int clc = s.length() / cl;
        if (sl % cl != 0) clc = clc + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            if (i == 0) {   //第一行，一个周期内只有一个点
                for (int j = 0; j < clc; j++) {
                    int index = j * cl;
                    if (index < sl) {
                        builder.append(s.charAt(index));
                    }
                }
            } else if (i == numRows - 1) { //最后一行，一个周期内只有一个点
                for (int j = 0; j < clc; j++) {
                    int index = j * cl + numRows - 1;
                    if (index < sl) {
                        builder.append(s.charAt(index));
                    }
                }
            } else {//中间行，一个周期内有2个点
                for (int j = 0; j < clc; j++) {
                    int index1 = i + j * cl;
                    int index2 = cl - i + j * cl;
                    if (index1 < sl) {
                        builder.append(s.charAt(index1));
                    }
                    if (index2 < sl) {
                        builder.append(s.charAt(index2));
                    }
                }
            }
        }

        return builder.toString();
    }
}
