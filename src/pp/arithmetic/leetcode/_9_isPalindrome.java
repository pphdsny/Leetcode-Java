package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/11/1.
 * 9. 回文数
 * <p>
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 * <p>
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * @see <a href="https://leetcode-cn.com/problems/palindrome-number/description/">palindrome-number</a>
 */
public class _9_isPalindrome {
    public static void main(String[] args) {
        boolean palindrome = isPalindrome(123);
        System.out.println(palindrome);
    }

    public static boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) { //x是10的倍数一定不是回文串
            return false;
        }
        int s = 0;
        while (s <= x) {
            s = s * 10 + x % 10;
            if (s == x || s == x / 10) { //分别处理整数长度是奇数或者偶数的情况
                return true;
            }
            x /= 10;
        }
        return false;
    }
}
