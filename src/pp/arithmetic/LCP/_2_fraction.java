package pp.arithmetic.LCP;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-09-30.
 * LCP 2. 分式化简
 * <p>
 * 有一个同学在学习分式。他需要将一个连分数化成最简分数，你能帮助他吗？
 * <p>
 * https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/09/09/fraction_example_1.jpg
 * <p>
 * 连分数是形如上图的分式。在本题中，所有系数都是大于等于0的整数。
 * <p>
 *  
 * <p>
 * 输入的cont代表连分数的系数（cont[0]代表上图的a0，以此类推）。返回一个长度为2的数组[n, m]，使得连分数的值等于n / m，且n, m最大公约数为1。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：cont = [3, 2, 0, 2]
 * 输出：[13, 4]
 * 解释：原连分数等价于3 + (1 / (2 + (1 / (0 + 1 / 2))))。注意[26, 8], [-13, -4]都不是正确答案。
 * 示例 2：
 * <p>
 * 输入：cont = [0, 0, 3]
 * 输出：[3, 1]
 * 解释：如果答案是整数，令分母为1即可。
 * 限制：
 * <p>
 * cont[i] >= 0
 * 1 <= cont的长度 <= 10
 * cont最后一个元素不等于0
 * 答案的n, m的取值都能被32位int整型存下（即不超过2 ^ 31 - 1）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/deep-dark-fraction
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _2_fraction {

    public static void main(String[] args) {
        _2_fraction fraction = new _2_fraction();
        Util.printArray(fraction.fraction(new int[]{3, 2, 0, 2}));
        Util.printArray(fraction.fraction(new int[]{0, 0, 3}));
        Util.printArray(fraction.fraction(new int[]{3}));
    }

    /**
     * 解题思路：
     * 对整个题目比划比划你会发现，整个解题的过程就是个循环求解分式，为了更好的写代码，逆向求解
     * 1、定义一个length为2的数组result，第一位是分子，第二位是分母
     * 2、逆向遍历数组
     * 3、对于第一位，result[0]=1,result[1]=item
     * 4、分式对下一位相加，直接分母*该值加到分子上，并将分子分母求导数
     * 5、由于是逆向求解，还需要对最终结果进行求导数
     *
     * @param cont
     * @return
     */
    public int[] fraction(int[] cont) {
        //1
        int[] result = new int[2];
        //2
        for (int i = cont.length - 1; i >= 0; i--) {
            int item = cont[i];
            if (result[0] == 0 && result[1] == 0) {
                //3
                result[0] = 1;
                result[1] = item;
            } else {
                //4
                result[0] += result[1] * item;
                swap(result);
            }
        }
        //5
        swap(result);
        return result;
    }

    private void swap(int[] arr) {
        int temp = arr[0];
        arr[0] = arr[1];
        arr[1] = temp;
    }
}
