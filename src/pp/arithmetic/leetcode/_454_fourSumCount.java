package pp.arithmetic.leetcode;

import java.util.HashMap;

/**
 * Created by wangpeng on 2019-04-10.
 * 454. 四数相加 II
 * <p>
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * <p>
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 * <p>
 * 例如:
 * <p>
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * 输出:
 * 2
 * <p>
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * @see <a href="https://leetcode-cn.com/problems/4sum-ii/">4sum-ii</a>
 */
public class _454_fourSumCount {
    public static void main(String[] args) {
        _454_fourSumCount fourSumCount = new _454_fourSumCount();
        System.out.println(fourSumCount.fourSumCount(
                new int[]{-1, -1},
                new int[]{-1, 1},
                new int[]{-1, 1},
                new int[]{-1, 1})
        );
    }

    /**
     * 最直接的方案就是四次循环拿到满足条件的计数，不过这个通不过，时间复杂度在O(n^4)
     * 可行解题思路：
     * 看题目关联到哈希表，所以往那个方向考虑下
     * 1、先将4个数组分层两组A+B，C+D
     * 2、定义个一个hash表，存储A+B所有的结果计数
     * 3、在遍历C+D的时候，取反后，看hash表中是否存在计数
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int retCount = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int sumAB = A[i] + B[j];
                map.put(sumAB, map.getOrDefault(sumAB, 0) + 1);
            }
        }
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sumCD = C[i] + D[j];
                retCount += map.getOrDefault(-sumCD, 0);
            }
        }

        return retCount;
    }
}
