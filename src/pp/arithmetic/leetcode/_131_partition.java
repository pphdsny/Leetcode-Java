package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2019-12-24.
 * 131. 分割回文串
 *
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回 s 所有可能的分割方案。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _131_partition {

    public static void main(String[] args) {
        _131_partition partition = new _131_partition();
        Util.printLists(partition.partition("aab"));
        Util.printLists(partition.partition("abbaa"));
        Util.printLists(partition.partition(""));
    }

    /**
     * 解题思路：DFS遍历+回溯
     * 1、DFS遍历，从s的第一位开始，逐步判断至最后一位
     * 2、定义一个skip=1，从1开始，代表从当前位加上skip的结果是否是回文
     * 3、定义一个循环，位置从0开始，得到所有的结果
     *
     * 执行用时 :8 ms, 在所有 java 提交中击败了20.05%的用户
     * 内存消耗 :38.1 MB, 在所有 java 提交中击败了97.34%的用户
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> retList = new ArrayList<>();
        dfs(retList,new ArrayList<>(),s,0);
        return retList;
    }

    private void dfs(List<List<String>> retList, List<String> itemList, String s, int index) {
        if (index > s.length()-1){
            retList.add(new ArrayList<>(itemList));
            return;
        }
        int skip = 1;
        while (skip + index <= s.length()) {
            String sub = s.substring(index, index + skip);
            if (isPlalindrome(sub)) {
                itemList.add(sub);
                dfs(retList, itemList, s, index + skip);
                if (itemList.size() > 0) {
                    itemList.remove(itemList.size() - 1);
                }
            }
            skip++;
        }
    }

    //是否是回文
    private boolean isPlalindrome(String s) {
        int si = 0, ei = s.length() - 1;
        while (si < ei) {
            if (s.charAt(si) != s.charAt(ei)) return false;
            si++;
            ei--;
        }
        return true;
    }
}
