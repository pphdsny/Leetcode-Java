package pp.arithmetic.leetcode;

import pp.arithmetic.model.TrieTree;

/**
 * Created by wangpeng on 2018/9/28.
 * 208.实现 Trie (前缀树)
 * <p>
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * <p>
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 *
 * @see <a href="https://leetcode-cn.com/problems/implement-trie-prefix-tree/description/">implement-trie-prefix-tree</a>
 */
public class _208_Trie {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app"));     // 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));     //true
    }

    public static class Trie {
        private TrieTree root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieTree('0');
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
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
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            char[] chars = word.toCharArray();
            TrieTree node = root;
            for (int i = 0; i < chars.length; i++) {
                TrieTree[] tries = node.tries;
                int index = chars[i] - 'a';
                TrieTree temp = tries[index];
                if (temp == null) {
                    return false;
                }
                node = temp;
            }
            return node.isEnd;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();
            TrieTree node = root;
            for (int i = 0; i < chars.length; i++) {
                TrieTree[] tries = node.tries;
                int index = chars[i] - 'a';
                TrieTree temp = tries[index];
                if (temp == null) {
                    return false;
                }
                node = temp;
            }
            return true;
        }
    }
}
