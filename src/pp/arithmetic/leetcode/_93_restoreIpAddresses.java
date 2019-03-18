package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2019-01-02.
 * 93. 复原IP地址
 * <p>
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 示例:
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 * @see <a href="https://leetcode-cn.com/problems/restore-ip-addresses/">restore-ip-addresses</a>
 */
public class _93_restoreIpAddresses {
    public static void main(String[] args) {
        Util.printStringList(restoreIpAddresses("25525511135"));
        Util.printStringList(restoreIpAddresses("0000"));
        Util.printStringList(restoreIpAddresses("9245587303"));
        Util.printStringList(restoreIpAddresses("010010"));
    }

    /**
     * 也可以用递归
     *
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> retList = new ArrayList<>();
        int[] next = new int[]{1, 2, 3};
        int length = s.length();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < next.length; i++) {
            int endI = next[i];
            if (endI >= length) continue;
            String si = s.substring(0, endI);
            if (!isValid(si)) {
                continue;
            }
            builder.append(si).append(".");
            for (int j = 0; j < next.length; j++) {
                int endJ = endI + next[j];
                if (endJ >= length) continue;
                String sj = s.substring(endI, endJ);
                if (!isValid(sj)) {
                    continue;
                }
                builder.append(sj).append(".");
                for (int k = 0; k < next.length; k++) {
                    int endK = endJ + next[k];
                    if (endK >= length) continue;
                    String sk = s.substring(endJ, endK);
                    if (!isValid(sk)) {
                        continue;
                    }
                    builder.append(sk).append(".");
                    //最后一位，直接取最后一位
                    int endL = length;
                    String sl = s.substring(endK, endL);
                    if (isValid(sl)) {
                        builder.append(sl);
                        retList.add(builder.toString());
                    }
                    //删除第3，4位
                    builder.delete(endK + 3, endL + 3);
                    builder.delete(endJ + 2, endK + 3);
                }
                builder.delete(endI + 1, endJ + 2);
            }
            builder.delete(0, endI + 1);
        }
        return retList;
    }

    private static boolean isValid(String s) {
        if (s.length() > 3) return false;
        int i = Integer.parseInt(s);
        if (i > 255 || i < 0 || !s.equals(i + "")) {
            return false;
        }
        return true;
    }

}
