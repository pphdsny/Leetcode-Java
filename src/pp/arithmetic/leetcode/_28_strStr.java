package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-04-15.
 * 28. 实现strStr()
 * <p>
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 * @see <a href="https://leetcode-cn.com/problems/implement-strstr/">implement-strstr</a>
 */
public class _28_strStr {
    public static void main(String[] args) {
        _28_strStr str = new _28_strStr();
        System.out.println(str.strStr("hello", "ll"));
        System.out.println(str.strStr("aaaaa", "aab"));
        System.out.println(str.strStr("a", "a"));
        System.out.println(str.strStr("babbbbbabb", "bbab"));
    }

    /**
     * 解题思路
     * 本题是实现Java字符串中的 {@link String#indexOf(String)}方法
     * 1、开始遍历 haystack，找到和 needle 相同的起始下标si
     * 2、从si开始同时遍历 haystack和needle
     * 3、如遍历过程中一直相同，则返回si，否则si后移1位
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }
        int ei;
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                ei = i;
                for (int j = 1; j < needle.length(); j++) {
                    if (haystack.charAt(++ei) != needle.charAt(j)) {
                        break;
                    }
                }
                if (haystack.charAt(ei) == needle.charAt(ei - i)) {
                    //找到了
                    return i;
                }
            }
        }


        return -1;
    }
}
