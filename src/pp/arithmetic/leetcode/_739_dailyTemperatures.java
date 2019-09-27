package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-09-27.
 * 739. 每日温度
 *
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _739_dailyTemperatures {

    public static void main(String[] args) {
        _739_dailyTemperatures dailyTemperatures = new _739_dailyTemperatures();
        Util.printArray(dailyTemperatures.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
        Util.printArray(dailyTemperatures.dailyTemperatures2(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }

    /**
     * 解题思路：
     * 暴力求解，每计算一位都向后遍历求出大于其的首位位置 ==>时间复杂度O(n^2)
     * 执行用时 :247 ms, 在所有 Java 提交中击败了32.89%的用户
     * 内存消耗 :42 MB, 在所有 Java 提交中击败了 95.19%的用户
     *
     * 优化求解：{@link _739_dailyTemperatures#dailyTemperatures2(int[])}
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] ret = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            for (int j = i+1; j < T.length; j++) {
                if (T[j]>T[i]){
                    ret[i] = j-i;
                    break;
                }
            }
        }

        return ret;
    }

    /**
     * 优化求解：
     * 对于求解一来说，存在大量的重复计算，考虑是否可以利用之前的计算结果？
     * 1、换个方向进行遍历，从右向左，对于最后一位来说肯定为0
     * 2、对于下一位，如 > 后一位则看后一位对应的是否有大于其的温度，如有则跳到该位置，继续步骤2
     * 3、如下一位 < 后一位，则直接标记为位置的差值
     * 4、如碰到某个位置对应的最大温度数为0，则表示后面不会有更大的值，那当然当前值就应该也为0
     *
     * 执行用时 :5 ms, 在所有 Java 提交中击败了99.86%的用户
     * 内存消耗 :42 MB, 在所有 Java 提交中击败了95.19%的用户
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures2(int[] T) {
        int length = T.length;
        int[] ret = new int[length];

        //1
        for (int i = length - 2; i >= 0; i--) {
            //2 += ret[j]
            for (int j = i + 1; j < length; j+= ret[j]) {
                if (T[j] > T[i]) {
                    //3
                    ret[i] = j - i;
                    break;
                } else if (ret[j] == 0) { //4
                    ret[i] = 0;
                    break;
                }
            }
        }

        return ret;

    }
}
