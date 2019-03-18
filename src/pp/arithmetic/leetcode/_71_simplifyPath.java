package pp.arithmetic.leetcode;

import java.util.Stack;

/**
 * Created by wangpeng on 2019-01-02.
 * 71. 简化路径
 * <p>
 * 给定一个文档 (Unix-style) 的完全路径，请进行路径简化。
 * <p>
 * 例如，
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * <p>
 * 边界情况:
 * <p>
 * 你是否考虑了 路径 = "/../" 的情况？
 * 在这种情况下，你需返回 "/" 。
 * 此外，路径中也可能包含多个斜杠 '/' ，如 "/home//foo/" 。
 * 在这种情况下，你可忽略多余的斜杠，返回 "/home/foo" 。
 *
 * @see <a href="https://leetcode-cn.com/problems/simplify-path/">simplify-path</a>
 */
public class _71_simplifyPath {
    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            simplifyPath("/home/");
            simplifyPath("/a/./b/../../c/");
            simplifyPath("/../");
        }
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);
    }

    public static String simplifyPath(String path) {
        String[] split = path.split("/");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (s.equals("/") || s.equals(".") || s.length() == 0) {
                continue;
            }
            if (s.equals("..")) {
                if (!stack.empty()) stack.pop();
                continue;
            }
            stack.push(s);
        }
        StringBuilder builder = new StringBuilder();
        while (!stack.empty()) {
            String pop = stack.pop();
            builder.insert(0, pop);
            builder.insert(0, "/");
        }
        if (builder.length() == 0) builder.append("/");
        return builder.toString();
    }
}
