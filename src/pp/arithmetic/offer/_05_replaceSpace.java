package pp.arithmetic.offer;

/**
 * Created by wangpeng on 2020-07-30.
 * 剑指 Offer 05. 替换空格
 *
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *  
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _05_replaceSpace {

    public static void main(String[] args) {
        _05_replaceSpace replaceSpace = new _05_replaceSpace();
        System.out.println(replaceSpace.replaceSpace("We are happy."));
    }

    /**
     * 解题思路：
     * 看到题目最直接的想法就是遍历异常，遇到空格就替换
     * 没有清楚这道题到底想考什么？
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        char[] chars = s.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == ' '){
                builder.append("%20");
            }else{
                builder.append(aChar);
            }
        }

        return builder.toString();
    }
}
