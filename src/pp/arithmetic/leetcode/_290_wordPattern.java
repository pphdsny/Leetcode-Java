package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangpeng on 2018/9/18.
 * 290.单词模式
 * <p>
 * 给定一种 pattern(模式) 和一个字符串 str ，判断 str 是否遵循相同的模式。
 * <p>
 * 这里的遵循指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应模式。
 * <p>
 * 示例1:
 * <p>
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
 *
 * @see <a href="https://leetcode-cn.com/problems/word-pattern/description/">word-pattern</a>
 */
public class _290_wordPattern {
    public static void main(String[] args) {
        boolean b = wordPattern("abba", "dog cat cat dog");
        System.out.println(b);
        boolean b1 = wordPattern("abba", "dog cat cat fish");
        System.out.println(b1);
        Util.printDivideLine();
        boolean b2 = wordPattern2("abba", "dog cat cat dog");
        System.out.println(b2);
        boolean b21 = wordPattern2("abba", "dog cat cat fish");
        System.out.println(b21);
    }

    public static boolean wordPattern(String pattern, String str) {
        char[] pChars = pattern.toCharArray();
        String[] strSplit = str.split(" ");
        if (pChars.length != strSplit.length) {
            return false;
        }
        Map<String, Character> map = new HashMap<>();
        int[] use = new int[26];
        for (int i = 0; i < strSplit.length; i++) {
            char pChar = pChars[i];
            String key = strSplit[i];
            Character value = map.get(key);
            if (value == null) {
                if (use[pChar - 'a'] != 0) {
                    return false;
                }
                use[pChar - 'a'] = 1;
                map.put(key, pChar);
            } else if (value != pChar) {
                return false;
            }
        }

        return true;
    }

    /**
     * 考虑下不用HashMap能否实现？
     *
     * @param pattern
     * @param str
     * @return
     */
    public static boolean wordPattern2(String pattern, String str) {
        char[] pChars = pattern.toCharArray();
        String[] strSplit = str.split(" ");
        if (pChars.length != strSplit.length) {
            return false;
        }
        String[] use = new String[26];
        for (int i = 0; i < pChars.length; i++) {
            String s = use[pChars[i] - 'a'];
            if (s == null) {
                use[pChars[i] - 'a'] = strSplit[i];
            } else if (!s.equals(strSplit[i])) {
                return false;
            }
        }
        return true;
    }
}
