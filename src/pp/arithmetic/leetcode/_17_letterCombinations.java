package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2019-04-09.
 * 17. 电话号码的字母组合
 * <p>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * <image src="http://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png"></image>
 * <p>
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * @see <a href="https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/">letter-combinations-of-a-phone-number</a>
 */
public class _17_letterCombinations {

    public static void main(String[] args) {
        _17_letterCombinations letterCombinations = new _17_letterCombinations();
        Util.printStringList(letterCombinations.letterCombinations("23"));
    }

    char[][] map = new char[][]{
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    /**
     * 解题思路：
     * 这道题初看挺简单的，每一位对应的字母做个对应关系，弄个多项循环，但是代码实现还是很恶心的。
     * 看了下题目关联的话题，有`回溯算法`，可以往这方面考虑
     * 1、利用二维数组建立数字和字母的映射关系
     * 2、深度遍历，一层层回溯得到结果
     * <p>
     * 执行用时 : 1 ms, 在Letter Combinations of a Phone Number的Java提交中击败了100.00% 的用户
     * 内存消耗 : 36.8 MB, 在Letter Combinations of a Phone Number的Java提交中击败了0.82% 的用户
     * 内存上开销略大，二维数组可以变成一维字符串数组
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> retList = new ArrayList<>();
        dfs(digits, 0, new char[digits.length()], retList);
        return retList;
    }

    private void dfs(String digits, int index, char[] cs, List<String> retList) {
        if (digits.length() == 0) return;
        if (index >= digits.length()) {
            retList.add(new String(cs));
            return;
        }
        char c = digits.charAt(index);
        char[] maps = map[c - '2'];
        for (int i = 0; i < maps.length; i++) {
            cs[index] = maps[i];
            dfs(digits, index + 1, cs, retList);
        }
    }
}
