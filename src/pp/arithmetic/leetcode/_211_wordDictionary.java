package pp.arithmetic.leetcode;

import pp.arithmetic.model.TrieTree;

/**
 * Created by wangpeng on 2018/9/28.
 * 211.添加与搜索单词 - 数据结构设计
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 * <p>
 * 示例:
 * <p>
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * 说明:
 * <p>
 * 你可以假设所有单词都是由小写字母 a-z 组成的。
 *
 * @see <a href="https://leetcode-cn.com/problems/add-and-search-word-data-structure-design/description/">add-and-search-word-data-structure-design</a>
 */
public class _211_wordDictionary {
    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); //-> false
        System.out.println(wordDictionary.search("bad")); //-> true
        System.out.println(wordDictionary.search(".ad")); //-> true
        System.out.println(wordDictionary.search("b..")); //-> true
    }

    public static class WordDictionary {
        private TrieTree root;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            root = new TrieTree('0');
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            char[] chars = word.toCharArray();
            TrieTree node = root;
            for (int i = 0; i < chars.length; i++) {
                TrieTree[] tries = node.tries;
                int index = chars[i] - 'a';
                if (tries[index] == null) {
                    tries[index] = new TrieTree(chars[i]);
                }
                node = tries[index];
            }
            node.isEnd = true;
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            char[] chars = word.toCharArray();
            return dfs(root, chars, 0);
        }

        private boolean dfs(TrieTree node, char[] chars, int index) {
            if (index == chars.length) {
                return node.isEnd;
            }
            char aChar = chars[index];
            if (aChar == '.') {
                TrieTree[] tries = node.tries;
                for (int i = 0; i < tries.length; i++) {
                    if (tries[i] != null && dfs(tries[i], chars, index + 1)) {
                        return true;
                    }
                }
            } else {
                TrieTree[] tries = node.tries;
                TrieTree aTry = tries[aChar - 'a'];
                if (aTry != null && dfs(aTry, chars, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }

}
