package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-06-06.
 * 1053. 交换一次的先前排列
 * <p>
 * 给你一个正整数的数组 A（其中的元素不一定完全不同），请你返回可在 一次交换（交换两数字 A[i] 和 A[j] 的位置）后得到的、按字典序排列小于 A 的最大可能排列。
 * <p>
 * 如果无法这么操作，就请返回原数组。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,2,1]
 * 输出：[3,1,2]
 * 解释：
 * 交换 2 和 1
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：[1,1,5]
 * 输出：[1,1,5]
 * 解释：
 * 这已经是最小排列
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * 输入：[1,9,4,6,7]
 * 输出：[1,7,4,6,9]
 * 解释：
 * 交换 9 和 7
 * <p>
 * <p>
 * 示例 4：
 * <p>
 * 输入：[3,1,1,3]
 * 输出：[1,3,1,3]
 * 解释：
 * 交换 1 和 3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * 1 <= A[i] <= 10000
 *
 * @see <a href="https://leetcode-cn.com/problems/previous-permutation-with-one-swap/">previous-permutation-with-one-swap</a>
 */
public class _1053_prevPermOpt1 {

    public static void main(String[] args) {
        _1053_prevPermOpt1 prevPermOpt1 = new _1053_prevPermOpt1();
        Util.printArray(prevPermOpt1.prevPermOpt1(new int[]{1, 9, 4, 6, 7}));
    }

    /**
     * 解题思路：
     * 这道题目的关键是 按字典序排列小于 A 的最大可能排列， 那么有
     *
     * 1.对当前序列进行逆序查找，找到第一个降序的位置i，使得A[i]>A[i+1]A[i]>A[i+1]
     *  1.1>由于A[i]>A[i+1]A[i]>A[i+1]，必能构造比当前字典序小的序列
     *  1.2>由于逆序查找，交换A[i]为最优解
     * 2.寻找在 A[i] 最左边且小于 A[i] 的最大的数字 A[j]
     *  2.1>由于 A[j] < A[]i]A[j]<A[]i], 交换 A[i] 与 A[j] 后的序列字典序一定小于当前字典序
     *  2.2>由于 A[j] 是满足关系的最大的最左的，因此一定是满足小于关系的交换后字典序最大的
     *
     * @param A
     * @return
     */
    public int[] prevPermOpt1(int[] A) {
        int len = A.length;
        int curMax = -1;
        int index = -1;
        boolean hasResult = false;
        for (int i = len - 2; i >= 0; i--) {
            if (A[i + 1] < A[i]) {                    // 此处逆序，需要移动A[i]
                for (int j = i + 1; j < len; j++) { // 寻找与 A[i] 交换的位置
                    if (A[i] > A[j]) {               // 必须满足 A[i] > A[j]，否则不能满足交换后的字典序小于原始字典序
                        hasResult = true;
                        if (A[j] > curMax) {
                            curMax = A[j];
                            index = j;
                        }
                    }
                }
                if (hasResult) {
                    int tmp = A[i];
                    A[i] = A[index];
                    A[index] = tmp;
                    return A;
                }
            }
        }
        return A;
    }
}
