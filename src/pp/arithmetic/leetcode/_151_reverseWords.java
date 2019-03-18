package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-02-01.
 * 151. 翻转字符串里的单词
 * <p>
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 示例:
 * <p>
 * 输入: "the sky is blue",
 * 输出: "blue is sky the".
 * 说明:
 * <p>
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 进阶: 请选用C语言的用户尝试使用 O(1) 空间复杂度的原地解法。
 *
 * @see <a href="https://leetcode-cn.com/problems/reverse-words-in-a-string/">reverse-words-in-a-string</a>
 */
public class _151_reverseWords {

    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords(" "));
    }

    /**
     * 执行用时: 15 ms, 在Reverse Words in a String的Java提交中击败了47.75% 的用户
     * 内存消耗: 29.6 MB, 在Reverse Words in a String的Java提交中击败了26.64% 的用户
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        StringBuilder builder = new StringBuilder();
        int si = 0;
        int ei = 0;
        while (si < s.length() && ei < s.length()) {
            while (si < s.length() && s.charAt(si) == ' ') {
                si++;
            }
            if (si == s.length()) {
                break;
            }
            ei = si;
            while (ei < s.length() && s.charAt(ei) != ' ') {
                ei++;
            }
            if (ei == s.length()) {
                break;
            }
            builder.insert(0, s.substring(si, ei));
            builder.insert(0, " ");
            si = ei;
        }
        if (si != ei) {
            if ((ei < s.length() && s.charAt(ei) == ' ')) {
                if (builder.length() > 0) {
                    builder.deleteCharAt(0);
                }
            } else {
                builder.insert(0, s.substring(si, ei));
            }
        }

        return builder.toString();
    }
}
