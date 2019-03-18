package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2018/9/11.
 * 22. 括号生成
 * <p>
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 *
 * @see <a href="https://leetcode-cn.com/problems/generate-parentheses/description/">generate-parentheses</a>
 */
public class _22_generateParenthesis {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> strings = generateParenthesis(10);
        long end = System.currentTimeMillis();
        System.out.println("slef:" + (end - start));
        Util.printStringList(strings);
        Util.printDivideLine();
        start = System.currentTimeMillis();
        List<String> strings2 = generateParenthesisByOther(10);
        end = System.currentTimeMillis();
        System.out.println("other:" + (end - start));
        Util.printStringList(strings2);
    }

    /**
     * 使用char[]数组，代替频繁创建String对象，节约时间和空间
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, n, 0, 0, new char[n * 2]);
        return result;
    }

    private static void generate(List<String> result,
                                 int n,
                                 int left,
                                 int right,
                                 char[] pair) {
        if (right > left) {
            return;
        }
        if (left + right == n * 2) {
            result.add(String.copyValueOf(pair));
        }
        if (left < n) {
            pair[left + right] = '(';
            generate(result, n, left + 1, right, pair);
        }
        if (right < left) {
            pair[left + right] = ')';
            generate(result, n, left, right + 1, pair);
        }
    }

    public static List<String> generateParenthesisByOther(int n) {
        List<String> list = new ArrayList<String>();
        generate2(n, 0, 0, list, new char[n * 2]);
        return list;
    }

    private static void generate2(int n, int left, int right, List<String> list, char[] par) {
        if (right > left)
            return;
        if (left + right == n * 2) {
            list.add(String.copyValueOf(par));
        }
        if (left < n) {
            par[left + right] = '(';
            generate2(n, left + 1, right, list, par);
        }
        if (right < left) {
            par[left + right] = ')';
            generate2(n, left, right + 1, list, par);
        }

    }
}
