package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.*;

/**
 * Created by wangpeng on 2018/9/18.
 * 49.字母异位词分组
 * <p>
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * @see <a href="https://leetcode-cn.com/problems/group-anagrams/description/">group-anagrams</a>
 */
public class _49_groupAnagrams {

    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> lists = groupAnagrams(strs);
        for (int i = 0; i < lists.size(); i++) {
            Util.printStringList(lists.get(i));
        }
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String sort = sort(strs[i]);
            List<String> list = map.get(sort);
            if (list == null) {
                list = new ArrayList<>();
                map.put(sort, list);
            }
            list.add(strs[i]);
        }
        for (Map.Entry<String, List<String>> entry :
                map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    private static String sort(String src) {
        char[] chars = src.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

}
