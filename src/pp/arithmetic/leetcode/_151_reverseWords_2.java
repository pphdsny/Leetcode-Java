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
public class _151_reverseWords_2 {

    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords(" "));
    }

    public static String reverseWords(String s) {
        if (s.length() == 0) return s;
        char[] ch = s.toCharArray();
        char[] res = new char[ch.length];
        int len = helper(ch, ch.length - 1, res, 0, 0);
        return new String(res, 0, len);
    }

    private static int helper(char[] ch, int r, char[] res, int l, int len) {
        while (r >= 0 && ch[r] == ' ') {
            r--;
        }
        if (r < 0) return Math.max(0, len - 1);
        int right = r;
        while (r >= 0 && ch[r] != ' ') {
            r--;
        }
        len += right - r + 1;
        for (int left = r + 1; left <= right; left++, l++) {
            res[l] = ch[left];
        }
        if (l < res.length) {
            res[l++] = ' ';
        }
        return helper(ch, r, res, l, len);
    }
}
