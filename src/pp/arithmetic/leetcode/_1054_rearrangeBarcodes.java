package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-06-10.
 * 1054. 距离相等的条形码
 * <p>
 * 在一个仓库里，有一排条形码，其中第 i 个条形码为 barcodes[i]。
 * <p>
 * 请你重新排列这些条形码，使其中两个相邻的条形码 不能 相等。 你可以返回任何满足该要求的答案，此题保证存在答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,1,1,2,2,2]
 * 输出：[2,1,2,1,2,1]
 * 示例 2：
 * <p>
 * 输入：[1,1,1,1,2,2,3,3]
 * 输出：[1,3,1,3,2,1,2,1]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= barcodes.length <= 10000
 * 1 <= barcodes[i] <= 10000
 * <p>
 *
 * @see <a href="https://leetcode-cn.com/problems/distant-barcodes/">distant-barcodes</a>
 */
public class _1054_rearrangeBarcodes {
    public static void main(String[] args) {
        _1054_rearrangeBarcodes rearrangeBarcodes = new _1054_rearrangeBarcodes();
        Util.printArray(rearrangeBarcodes.rearrangeBarcodes(new int[]{1, 1, 1, 1, 2, 2, 3, 3}));
    }

    /**
     * 解题思路：
     * 为了保证可以实现相邻一定不相等，可以依次交错排列同一个数字。
     * <p>
     * 首先统计每个数字的出现次数
     * 最特殊的情况为，数组的长度为奇数，某一个数字出现 (length+1)/2(length+1)/2 次，
     * 如 [2, 1, 2, 1, 2]，此时必须先从奇数位开始放置2，之后才能防止别的数组。
     * <p>
     * 首先从奇数位开始放置出现次数最多的数字。
     * 将其余数字放置在奇数位。
     * 将剩余数字依次放置在偶数位。
     *
     * @param barcodes
     * @return
     */
    public int[] rearrangeBarcodes(int[] barcodes) {
        /* 存在特殊情况结果类似 2, 1, 2, 1, 2
         * 因此优先使用出现次数最多的元素填充奇数位
         */
        /* 统计每个数据的出现次数 */
        int len = barcodes.length;
        int[] count = new int[10001];
        for (int i = 0; i < len; i++) {
            count[barcodes[i]]++;
        }
        /* 得到出现次数最多的数字 */
        int maxCnt = 0;
        int maxNum = 0;
        for (int i = 1; i < 10001; i++) {
            if (count[i] > maxCnt) {
                maxCnt = count[i];
                maxNum = i;
            }
        }
        /* 先填充奇数位 */
        int[] result = new int[len];
        int pos = 0;    // result 填充位置
        int idx = 0;    // count 使用位置
        /* 先使用出现次数最多的数字填充奇数位, 最多恰好填满 */
        while (pos < len) {
            if (count[maxNum] <= 0) {
                break;  // 填充完毕
            } else {
                count[maxNum]--;
                result[pos] = maxNum;
                pos += 2;
            }
        }
        /* 尝试继续填充奇数位 */
        while (pos < len) {
            if (count[idx] <= 0) {
                idx++;
                continue;
            } else {
                count[idx]--;
                result[pos] = idx;
                pos += 2;
            }
        }
        /* 继续填充偶数位 */
        pos = 1;
        while (pos < len) {
            if (count[idx] <= 0) {
                idx++;
                continue;
            } else {
                count[idx]--;
                result[pos] = idx;
                pos += 2;
            }
        }
        return result;
    }
}
