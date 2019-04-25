package pp.arithmetic.leetcode;

import java.util.*;

/**
 * Created by wangpeng on 2019-04-25.
 * 139. 单词拆分
 * <p>
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * <p>
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 * 注意你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * @see <a href="https://leetcode-cn.com/problems/word-break/">word-break</a>
 */
public class _139_wordBreak {
    public static void main(String[] args) {
        _139_wordBreak wordBreak = new _139_wordBreak();
        System.out.println(wordBreak.wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(wordBreak.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(wordBreak.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        System.out.println(wordBreak.wordBreak2("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    }

    /**
     * 解题思路：回溯实现
     * 0、用hashmap保存字典，减少匹配的耗时
     * 1、按位逐个遍历字符串，看是否和字典中匹配，匹配上再后移截取
     * 2、如果后续没有匹配上，直到字符串结束，那么上个字符串截取位置再向后寻找
     * <p>
     * 可解题，但是提交超时，超时用例如下
     * "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
     * ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
     * 超时分析：对于上述测试用例，有大量重复的反复比较
     * <p>
     * 解法二：{@link _139_wordBreak#wordBreak2(String, List)}
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        HashMap<String, Boolean> map = new HashMap<>();
        for (int i = 0; i < wordDict.size(); i++) {
            map.put(wordDict.get(i), true);
        }
        LinkedList<String> findList = new LinkedList<>();
        boolean lastMatch = false;
        int si = 0, ei = 1;
        while (si < s.length()) {
            if (ei > s.length()) {
                if (lastMatch) break;   //完全匹配成功
                if (findList.size() == 0) break;  //完全未找到
                //si回到上个位置
                ei = si + 1;
                String lastStr = findList.removeLast();
                si = si - lastStr.length();
                continue;
            }
            String substring = s.substring(si, ei);
            if (map.getOrDefault(substring, false)) {//匹配上了
                si = ei;
                ei++;
                lastMatch = true;
                findList.add(substring);
            } else {
                ei++;
                lastMatch = false;
            }
        }

        return lastMatch;
    }

    /**
     * 解法二：
     * 看了下题目关联的话题只有`动态规划`，那朝着动态规划的方向思考下
     * - 确认原问题与子问题=>原问题：字符串s能否拆分成功，子问题：字符串s的前i个字符能否拆分成wordDict
     * - 确认状态=>dp[i]表示字符串s的前i个字符能否拆分成wordDict
     * - 确认边界状态的值=>dp[0]=true
     * - 确定状态转移方程=>dp[i]=dp[j] && wordDict.contains(s.substring(j, i)),(0<=j<i)
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
