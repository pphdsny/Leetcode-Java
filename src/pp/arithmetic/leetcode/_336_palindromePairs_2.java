package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wangpeng on 2018/11/2.
 * 336. 回文对
 * <p>
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2:
 * <p>
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]]
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 *
 * @see <a href="https://leetcode-cn.com/problems/palindrome-pairs/description/">palindrome-pairs</a>
 */
public class _336_palindromePairs_2 {
    public static void main(String[] args) {
        List<List<Integer>> lists = palindromePairsOptimize(new String[]{
                "abcd", "dcba", "lls", "s", "sssll"
        });
        for (int i = 0; i < lists.size(); i++) {
            Util.printList(lists.get(i));
        }

    }

    /**
     * 时间复杂度O(n*m*n)
     * 能减少一些匹配的时间开销
     *
     * @param words
     * @return
     */
    private static List<List<Integer>> palindromePairsOptimize(String[] words) {
        List<List<Integer>> res = new ArrayList();
        if (words == null || words.length == 0) return res;

        TrieNode root = new TrieNode();
        //构建字典树
        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }
        //找结果
        for (int i = 0; i < words.length; i++) {
            searchWord(root, i, words[i], res);
        }

        return res;
    }

    private static void addWord(TrieNode root, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a';
            if (root.next[j] == null) root.next[j] = new TrieNode();
            //若word的子串[i, i]为回文，说明以word的子串[i + 1, word.length()]为后缀
            //且剩余字符串为回文
            if (isPalindrome(word, 0, i)) root.list.add(index);
            root = root.next[j];
        }
        root.index = index;
        root.list.add(index);
    }

    private static void searchWord(TrieNode root, int i, String word, List<List<Integer>> res) {
        for (int j = 0; j < word.length(); j++) {
            //若word[0, j - 1]与字典树该节点对应的字符串匹配
            //且word[j, word.length() - 1]为回文,说明(i, root.index)满足条件
            if (root.index >= 0 && root.index != i && isPalindrome(word, j, word.length() - 1)) {
                res.add(Arrays.asList(i, root.index));
            }
            root = root.next[word.charAt(j) - 'a'];
            if (root == null) return;
        }
        //注意这里
        for (int j : root.list) {
            if (j == i) continue;
            res.add(Arrays.asList(i, j));
        }
    }

    private static boolean isPalindrome(String str, int start, int end) {
        while (start < end) {
            if (str.charAt(start++) != str.charAt(end--)) return false;
        }

        return true;
    }

    private static class TrieNode {
        //存放字符串对应下标，若该节点在字典树中不是单词，则默认-1
        int index;
        //存放以当前字符串为后缀，剩余字符串为回文的字符串的下标
        List<Integer> list;
        TrieNode[] next;

        public TrieNode() {
            index = -1;
            list = new ArrayList();
            next = new TrieNode[26];
        }
    }
}
