package pp.arithmetic.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangpeng on 2019-02-27.
 * 60. 第k个排列
 * <p>
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * <p>
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * <p>
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * <p>
 * 说明：
 * <p>
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * <p>
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * <p>
 * 输入: n = 4, k = 9
 * 输出: "2314"
 *
 * @see <a href="https://leetcode-cn.com/problems/permutation-sequence/">permutation-sequence</a>
 */
public class _60_getPermutation_m {
    public static void main(String[] args) {
        _60_getPermutation_m getPermutation_m = new _60_getPermutation_m();
        String permutation1 = getPermutation_m.getPermutation(3, 3);
        System.out.println(permutation1);
        String permutation2 = getPermutation_m.getPermutation(4, 9);
        System.out.println(permutation2);

    }

    /**
     * 根据n阶乘的情况，按照有序算出K对应每一位的值
     *
     * 执行用时: 11 ms, 在Permutation Sequence的Java提交中击败了81.62% 的用户
     * 内存消耗: 37.3 MB, 在Permutation Sequence的Java提交中击败了3.26% 的用户
     *
     * @param n
     * @param k
     * @return
     */
    private String getPermutation(int n, int k) {
        char[] chars = new char[n];
        int[] factorial = new int[n + 1];
        for (int i = n; i >= 0; i--) {
            if (i == n) {
                factorial[i] = 1;
            } else {
                factorial[i] = factorial[i + 1] * (n - i);
            }
        }
        if (k > factorial[0]) {
            return "";
        }
        List<Integer> numList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            numList.add(i + 1);
        }
        for (int i = 0; i < n; i++) {
            int index = (k - 1) / factorial[i + 1];
            chars[i] = (char) (numList.get(index) + '0');
            numList.remove(index);
            k = k - factorial[i + 1] * index;
        }
        return new String(chars);
    }
}
