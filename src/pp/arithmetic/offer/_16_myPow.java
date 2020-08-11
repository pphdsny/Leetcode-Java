package pp.arithmetic.offer;

/**
 * Created by wangpeng on 2020-08-11.
 *
 * 剑指 Offer 16. 数值的整数次方
 *
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 *
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 *
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 *  
 *
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _16_myPow {

    public static void main(String[] args) {
        _16_myPow myPow = new _16_myPow();
        System.out.println(myPow.myPow(2.0,10));
        System.out.println(myPow.myPow(2.1,3));
        System.out.println(myPow.myPow(2.0,-2));
        System.out.println(myPow.myPow(0.00001, 2147483647));
        System.out.println(myPow.myPow(2, -2147483648));
    }

    /**
     * 解题思路：
     * 最简单的方式就是直接循环0-n，将x相乘得出结果，题目中的n范围比较大，这样子效率太低
     * 优化：类似2分拆分，一半一半的计算结果，最终相乘
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n<0){
            //指数是否负数，负数需要取倒数
            x = 1/x;
        }
        //是否取一半还有剩余一个
        boolean isOdd = n % 2 != 0;
        double v = myPow(x, Math.abs(n / 2));
        if (isOdd) {
            return  v * v * x;
        } else {
            return  v * v;
        }
    }

}
