package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-08-31.
 * 461. 汉明距离
 *
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * 注意：
 * 0 ≤ x, y < 231.
 *
 * 示例:
 *
 * 输入: x = 1, y = 4
 *
 * 输出: 2
 *
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hamming-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _461_hammingDistance {
    public static void main(String[] args) {
        _461_hammingDistance hammingDistance = new _461_hammingDistance();
        System.out.println(hammingDistance.hammingDistance(1, 4));
        System.out.println(hammingDistance.hammingDistance1(1, 254));
    }

    /**
     * 解题思路：
     * 1.x^y做异或操作，得到的结果中不同位置都是1
     * 2.将异或结果转换成二进制字符串
     * 3.遍历二进制字符串得到 1 的个数
     *
     * 更优解法{@link _461_hammingDistance#hammingDistance1(int, int)}
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        int result = 0;
        int i = x ^ y;
        String s = Integer.toBinaryString(i);
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == '1') result++;
        }
        return result;
    }

    /**
     * Integer.bitCount源码剖析
     * (>>> 代表无符号右移，对于负数，右移后首位补0而非1)
     * 0x55555555 ==> 01010101010101010101010101010101
     * 0x33333333 ==> 00110011001100110011001100110011
     * 0x0f0f0f0f ==> 00001111000011110000111100001111
     *
     * //第一步，每两位一个二进制数，每个二进制数的值表示这两位中“1”的数量。00->00,01->01,10->01,11->10
     * i = i - ((i >>> 1) & 0x55555555);
     * //第二步，两两分组，计算出两两的总数，保存在4中
     * i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
     * //第三步，四四分组，计算出四四中的总数，保存在8中
     * i = (i + (i >>> 4)) & 0x0f0f0f0f;
     * //第四步，八八分组，计算出八八中的总数，保存在16中
     * i = i + (i >>> 8);
     * //第五步，16-16分组，计算出16-16总数
     * i = i + (i >>> 16);
     * //结果返回
     * return i & 0x3f;
     *
     * 过程并不是很好理解，可以通过debug和手写模拟下
     * 用一句话就是把二进制数按两位分组，相邻分组两两相加得四位二进制的bitCount，再按四位分组，相邻分组两两相得八位二进制的bitCount，以此类推直到算出32位的bitCount数量。
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance1(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
