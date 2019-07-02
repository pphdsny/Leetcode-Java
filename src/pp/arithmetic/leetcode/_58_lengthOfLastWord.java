package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-07-02.
 * 58. 最后一个单词的长度
 * <p>
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 * <p>
 * 如果不存在最后一个单词，请返回 0 。
 * <p>
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: "Hello World"
 * 输出: 5
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/length-of-last-word
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _58_lengthOfLastWord {

    public static void main(String[] args) {
        _58_lengthOfLastWord length = new _58_lengthOfLastWord();
        System.out.println(length.lengthOfLastWord("Hello World"));
        System.out.println(length.lengthOfLastWord("  s  "));
        System.out.println(length.lengthOfLastWord(" Hell    "));
    }

    /**
     * 解题思路：
     * 从s的最后一个开始遍历，遇到首个非空的字母开始计数到下一个为空的时候停止
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int startIndex = -1, endIndex = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (startIndex != -1 && s.charAt(i) == ' ') {
                endIndex = i;
                break;
            }
            if (startIndex == -1 && s.charAt(i) != ' ') {
                startIndex = i;
            }
        }
        if (startIndex == -1) {
            return 0;
        }
        return startIndex - endIndex;
    }
}
