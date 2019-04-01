package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-03-29.
 * 978. 最长湍流子数组
 * <p>
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 * <p>
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * <p>
 * 返回 A 的最大湍流子数组的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 示例 2：
 * <p>
 * 输入：[4,8,12,16]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：[100]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 *
 * @see <a href="https://leetcode-cn.com/problems/longest-turbulent-subarray/">longest-turbulent-subarray</a>
 */
public class _978_maxTurbulenceSize {

    public static void main(String[] args) {
        _978_maxTurbulenceSize size = new _978_maxTurbulenceSize();
        System.out.println(size.maxTurbulenceSize(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9}));
        System.out.println(size.maxTurbulenceSize(new int[]{4, 8, 12, 16}));
        System.out.println(size.maxTurbulenceSize(new int[]{4, 4}));
        System.out.println(size.maxTurbulenceSize(new int[]{4, 4, 4}));
    }

    /**
     * 解法二：代码简化了解法一的行数，窗口的思想
     * 先确定一个起始锚点anchor，再确定一个终止的点，两点相减得到区间长度
     *
     * @param A
     * @return
     */
    public int maxTurbulenceSize2(int[] A) {
        int N = A.length;
        int ans = 1;
        int anchor = 0;

        for (int i = 1; i < N; ++i) {
            int c = Integer.compare(A[i - 1], A[i]);
            if (i == N - 1 || c * Integer.compare(A[i], A[i + 1]) != -1) {
                if (c != 0) ans = Math.max(ans, i - anchor + 1);
                anchor = i;
            }
        }

        return ans;
    }

    /**
     * 简单解法：一次遍历，通过多个变量控制循环累加
     *
     * @param A
     * @return
     */
    public int maxTurbulenceSize(int[] A) {
        if (A.length <= 1) {
            return A.length;
        }
        int max = 1;
        int subMax = 0;
        boolean isAccumlation = false;
        int preMinus = 0;
        for (int i = 1; i < A.length; i++) {
            int minus = A[i] - A[i - 1];
            if (minus == 0) {
                max = Math.max(max, subMax);
                isAccumlation = false;
                continue;
            }
            if (!isAccumlation) {
                preMinus = minus;
                subMax = 2;
                isAccumlation = true;
                continue;
            }
            if (preMinus > 0 ^ minus > 0) {
                //替换
                subMax++;
                preMinus = minus;
            } else {
                //结束,重新计数
                max = Math.max(max, subMax);
                preMinus = minus;
                subMax = 2;
            }
        }
        max = Math.max(max, subMax);

        return max;
    }
}
