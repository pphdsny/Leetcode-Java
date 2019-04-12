package pp.arithmetic.leetcode;

import java.util.Stack;

/**
 * Created by wangpeng on 2019-04-12.
 * 20. 有效的括号
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 *
 * @see <a href="https://leetcode-cn.com/problems/valid-parentheses/">valid-parentheses</a>
 */
public class _20_isValid {
    public static void main(String[] args) {
        _20_isValid valid = new _20_isValid();
        System.out.println(valid.isValid("()"));
        System.out.println(valid.isValid("()[]{}"));
        System.out.println(valid.isValid("(]"));
        System.out.println(valid.isValid("([)]"));
        System.out.println(valid.isValid("{[]}"));
    }

    /**
     * 解题思路：
     * 需要知道是否有效，也就是左右得成对出现，利用栈去做存储
     * 1、左括号时，则入栈
     * 2、有括号时，则判断栈顶是否是配套左括号，如是则将其出栈，否则无效
     * 3、遍历完成后，如栈空则有效，反则无效
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()){
                    return false;
                }
                Character pop = stack.pop();
                switch (c) {
                    case ')':
                        if (pop != '(') {
                            return false;
                        }
                        break;
                    case '}':
                        if (pop != '{') {
                            return false;
                        }
                        break;
                    case ']':
                        if (pop != '[') {
                            return false;
                        }
                        break;
                }
            }
        }

        return stack.isEmpty();
    }
}
