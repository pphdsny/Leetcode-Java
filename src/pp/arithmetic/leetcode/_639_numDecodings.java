package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-04-26.
 * 639. 解码方法 2
 * <p>
 * 一条包含字母 A-Z 的消息通过以下的方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 除了上述的条件以外，现在加密字符串可以包含字符 '*'了，字符'*'可以被当做1到9当中的任意一个数字。
 * <p>
 * 给定一条包含数字和字符'*'的加密信息，请确定解码方法的总数。
 * <p>
 * 同时，由于结果值可能会相当的大，所以你应当对10^9 + 7取模。（翻译者标注：此处取模主要是为了防止溢出）
 * <p>
 * 示例 1 :
 * <p>
 * 输入: "*"
 * 输出: 9
 * 解释: 加密的信息可以被解密为: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 * 示例 2 :
 * <p>
 * 输入: "1*"
 * 输出: 9 + 9 = 18（翻译者标注：这里1*可以分解为1,* 或者当做1*来处理，所以结果是9+9=18）
 * 说明 :
 * <p>
 * 输入的字符串长度范围是 [1, 10^5]。
 * 输入的字符串只会包含字符 '*' 和 数字'0' - '9'。
 *
 * @see <a href="https://leetcode-cn.com/problems/decode-ways-ii/">decode-ways-ii</a>
 */
public class _639_numDecodings {

    public static void main(String[] args) {
        _639_numDecodings numDecodings = new _639_numDecodings();
        System.out.println(numDecodings.numDecodings("1*"));
    }

    public static final int mod = (int) Math.pow(10, 9) + 7;

    /**
     * LO上优秀解法
     * 解题关键点，理清楚各种情况下的个数可能性
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();

        long[] dp = new long[str.length + 1];
        dp[str.length] = 1;
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] == '0') {
                dp[i] = 0;
            } else {
                long res = dp[i + 1];
                if (str[i] == '*') {
                    res = (9 * res) % mod;
                }
                if (i + 1 < str.length) {
                    long tmp = dp[i + 2];
                    if (str[i] != '*') {
                        if (str[i + 1] != '*') {
                            if ((str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
                                res = (res + tmp) % mod;
                            }
                        } else {
                            if (str[i] < '3') {
                                if (str[i] == '1') {
                                    res = (res + 9 * tmp) % mod;
                                } else if (str[i] == '2') {
                                    res = (res + 6 * (tmp)) % mod;
                                }
                            }
                        }
                    } else {
                        if (str[i + 1] != '*') {
                            if (10 + str[i + 1] - '0' < 27) {
                                res = (res + tmp) % mod;
                            }
                            if (20 + str[i + 1] - '0' < 27) {
                                res = (res + tmp) % mod;
                            }
                        } else {
                            res = (res + 15 * tmp) % mod;
                        }
                    }
                }
                dp[i] = res;
            }
        }

        return (int) (dp[0]);
    }
}
