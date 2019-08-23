package pp.arithmetic.leetcode;

import java.util.LinkedList;

/**
 * Created by wangpeng on 2019-08-23.
 * 394. 字符串解码
 * <p>
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例:
 * <p>
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _394_decodeString {

    public static void main(String[] args) {
        _394_decodeString decodeString = new _394_decodeString();
        System.out.println(decodeString.decodeString("3[a2[c]]"));
        System.out.println(decodeString.decodeString("2[abc]3[cd]ef"));
    }

    /**
     * 解题思路：
     * 将decodeStr拆解后会有四种可能：数字、字母、[、]
     * 使用栈保存遍历的结果，numStack保存数字，stringStack保存字母
     * 例:3[a2[c]]
     * 1、遇到数字：计算数字的大小，注意连续数字的情况
     * 2、遇到左括号：将之前得到的数字入栈，之前得到的字母也入栈
     * 3、遇到字母：累加连续字母
     * 4、遇到右括号：将数字出栈，将累加字母根据数字翻倍，将字母也出栈，和翻倍字母拼接
     * 5、循环1-4
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        StringBuilder builder = new StringBuilder();
        LinkedList<Integer> numStack = new LinkedList<>();
        LinkedList<String> stringStack = new LinkedList<>();
        int num = 0;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                //1
                num = num * 10 + c - '0';
            } else if (c == '[') {
                //2
                numStack.addLast(num);
                stringStack.addLast(builder.toString());
                builder.delete(0, builder.length());
                num = 0;
            } else if (c == ']') {
                //4
                String item = builder.toString();
                Integer numItem = numStack.removeLast();
                for (int i = 1; i < numItem; i++) {
                    builder.append(item);
                }
                builder.insert(0, stringStack.removeLast());
            } else {
                //3
                builder.append(c);
            }
        }

        return builder.toString();
    }
}
