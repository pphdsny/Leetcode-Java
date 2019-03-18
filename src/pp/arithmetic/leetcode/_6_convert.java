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
public class _6_convert {

    public static void main(String[] args) {
        _6_convert convert = new _6_convert();
        System.out.println(convert.convert("LEETCODEISHIRING", 4));
        System.out.println(convert.convert("LE", 1));
    }

    /**
     * 执行用时 : 75 ms, 在ZigZag Conversion的Java提交中击败了34.97% 的用户
     * 内存消耗 : 51.3 MB, 在ZigZag Conversion的Java提交中击败了18.60% 的用户
     * <p>
     * 耗时在数组两次循环
     * 优化->一次循环，不依赖数据
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (s.length() <= numRows || numRows == 1) {
            return s;
        }
        //构建存储数据
        int l = numRows * 2 - 2;
        int l1 = s.length() / l;
        int l2;
        int ms = s.length() % l;
        if (ms == 0)
            l2 = 0;
        else if (ms < numRows)
            l2 = 1;
        else
            l2 = s.length() % l - numRows + 1;

        char[][] ss = new char[numRows][(l1 * (numRows - 1) + l2)];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int ml = i % l;
            int col, row;
            if (ml < numRows) {
                row = ml;
            } else {
                row = l - ml;
            }
            int dl = i / l;
            if (ml < numRows) {
                col = dl * (numRows - 1);
            } else {
                col = dl * (numRows - 1) + ml - numRows + 1;
            }
            ss[row][col] = chars[i];
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ss.length; i++) {
            for (int j = 0; j < ss[i].length; j++) {
                if (ss[i][j] == '\u0000') {
                    continue;
                }
                builder.append(ss[i][j]);
            }
        }

        return builder.toString();
    }
}
