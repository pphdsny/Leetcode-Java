package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-01-29.
 * 14. 最长公共前缀
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 *
 * @see <a href="https://leetcode-cn.com/problems/longest-common-prefix/">longest-common-prefix</a>
 */
public class _14_longestCommonPrefix {

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        System.out.println(longestCommonPrefix(new String[]{}));
        Util.printDivideLine();
        System.out.println(longestCommonPrefix2(new String[]{"flower", "flow11", "flight"}));
        System.out.println(longestCommonPrefix2(new String[]{"dog", "racecar", "car"}));
        System.out.println(longestCommonPrefix2(new String[]{}));
    }

    /**
     * 使用分治思想，时间复杂度能到O(logm*n)
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        return getPrefix(strs, 0, strs.length - 1);
    }

    private static String getPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        }
        String leftPre = getPrefix(strs, start, (start + end) / 2);
        String rightPre = getPrefix(strs, (start + end) / 2 + 1, end);
        return getTwoLongestCommonPrefix(leftPre, rightPre);
    }

    /**
     * 时间复杂度（O(m*n),m是数组的长度，n是每个String的平均长度）
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String preStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            preStr = getTwoLongestCommonPrefix(preStr, strs[i]);
        }

        return preStr;
    }

    private static String getTwoLongestCommonPrefix(String s1, String s2) {
        int index = 0;
        while (index < s1.length() && index < s2.length()) {
            if (s1.charAt(index) == s2.charAt(index)) {
                index++;
            } else {
                break;
            }
        }
        return s1.substring(0, index);
    }

}
