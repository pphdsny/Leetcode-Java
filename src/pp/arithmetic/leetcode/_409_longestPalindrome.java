package pp.arithmetic.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangpeng on 2018/9/18.
 * 409.最长回文数
 * <p>
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * <p>
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * <p>
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * "abccccdd"
 * <p>
 * 输出:
 * 7
 * <p>
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 *
 * @see <a href="https://leetcode-cn.com/problems/longest-palindrome/description/">longest-palindrome</a>
 */
public class _409_longestPalindrome {
    public static void main(String[] args) {
        int max = 100000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            longestPalindrome("abccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccdd");
        }
        long end = System.currentTimeMillis();
        //318ms
        System.out.println("self time:" + (end - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            longestPalindromeOther("abccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccddabccccdd");
        }
        end = System.currentTimeMillis();
        //63
        System.out.println("other time:" + (end - start));
//        System.out.println(maxLength);
    }

    public static int longestPalindrome(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        //使用高级数据结构hashmap，耗性能
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            Integer count = map.get(chars[i]);
            if (count == null) {
                count = 0;
            }
            map.put(chars[i], ++count);
        }
        int maxLength = 0;
        int flag = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 0) {
                maxLength += entry.getValue();
            } else {
                maxLength += entry.getValue() - 1;
                flag = 1;
            }
        }
        return maxLength + flag;
    }

    public static int longestPalindromeOther(String s) {
        char chars[] = s.toCharArray();
        int lowerCount[] = new int[26];
        int upperCount[] = new int[26];
        int odds = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            //构造映射关系，相当于hashcode
            if (chars[i] < 'a')
                upperCount[chars[i] - 'A']++;
            else
                lowerCount[chars[i] - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (lowerCount[i] % 2 == 1)
                odds++;
            if (upperCount[i] % 2 == 1)
                odds++;
        }
        if (odds == 0)
            return n;
        else
            return n - odds + 1;
    }
}
