package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-01-29.
 * 567. 字符串的排列
 * <p>
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * <p>
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * <p>
 * <p>
 * 示例2:
 * <p>
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * <p>
 * <p>
 * 注意：
 * <p>
 * 1.输入的字符串只包含小写字母
 * 2.两个字符串的长度都在 [1, 10,000] 之间
 *
 * @see <a href="https://leetcode-cn.com/problems/permutation-in-string/">permutation-in-string</a>
 */
public class _567_checkInclusion {

    public static void main(String[] args) {

        System.out.println(checkInclusion("ab", "eidbaooo"));
        System.out.println(checkInclusion("ab", "eidboaoo"));
    }

    /**
     * 方法1：最简单最暴力的方法其实就是找到s1的所有全排列，然后在s2中查找是否这些全排列字符串在s2中。但是这种方法耗时太大，会导致超时。
     * <p>
     * 方法2：滑动窗口
     * 其实不需要找到s1的全排列，因为我们只需要考虑s2中是否包含s1中同样个数的字符，并且这些字符是连在一起的就行了。
     * 因此，我们可以使用一个滑动窗口，在s2上滑动。在这个滑动窗口中的字符及其个数是否刚好等于s1中的字符及其个数，
     * 此外滑动窗口保证了这些字符是连在一起的。
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] diff = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            diff[s1.charAt(i) - 'a']++;
            diff[s2.charAt(i) - 'a']--;
        }
        for (int i = s1.length(); i < s2.length(); i++) {
            if (isSame(diff)) {
                return true;
            }
            diff[s2.charAt(i - s1.length()) - 'a']++;
            diff[s2.charAt(i) - 'a']--;
        }
        return isSame(diff);
    }

    private static boolean isSame(int[] diff) {
        for (int i = 0; i < diff.length; i++) {
            if (diff[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
