package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-10-18.
 * 65. 有效数字
 * <p>
 * 验证给定的字符串是否可以解释为十进制数字。
 * <p>
 * 例如:
 * <p>
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 * <p>
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
 * <p>
 * 数字 0-9
 * 指数 - "e"
 * 正/负号 - "+"/"-"
 * 小数点 - "."
 * 当然，在输入中，这些字符的上下文也很重要。
 * <p>
 * 更新于 2015-02-10:
 * C++函数的形式已经更新了。如果你仍然看见你的函数接收 const char * 类型的参数，请点击重载按钮重置你的代码
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _65_isNumber {

    public static void main(String[] args) {
        _65_isNumber isNumber = new _65_isNumber();
        /**
         *
         * "0" => true
         * " 0.1 " => true
         * "abc" => false
         * "1 a" => false
         * "2e10" => true
         * " -90e3   " => true
         * " 1e" => false
         * "e3" => false
         * " 6e-1" => true
         * " 99e2.5 " => false
         * "53.5e93" => true
         * " --6 " => false
         * "-+3" => false
         * "95a54e53" => false
         */
        System.out.println(isNumber.isNumber("+.8"));    //true
        System.out.println(isNumber.isNumber("-e58 "));    //false
        System.out.println(isNumber.isNumber("3."));    //true
        System.out.println(isNumber.isNumber("01"));    //true
        System.out.println(isNumber.isNumber(".1"));    //true
        System.out.println(isNumber.isNumber("0"));     //true
        System.out.println(isNumber.isNumber("0.1"));   //true
        System.out.println(isNumber.isNumber("abc"));   //false
        System.out.println(isNumber.isNumber("1 a"));   //false
        System.out.println(isNumber.isNumber("2e10"));  //true
        System.out.println(isNumber.isNumber("-90e3")); //true
        System.out.println(isNumber.isNumber(" 1e"));   //false
        System.out.println(isNumber.isNumber("e3"));    //false
        System.out.println(isNumber.isNumber(" 6e-1")); //true
        System.out.println(isNumber.isNumber(" 99e2.5"));//false
        System.out.println(isNumber.isNumber("53.5e93"));//true
        System.out.println(isNumber.isNumber(" --6"));  //false
        System.out.println(isNumber.isNumber("-+3"));   //false
        System.out.println(isNumber.isNumber("95a54e53"));//false
    }

    /**
     * 解题思路：
     * 本题最大的难点在于各种情况互相依赖，后面想了想，每种情况只关注自己该放的位置就可以了
     * caseTest有1400多个，情况比较多，导致提交多次，比较全的caseTest参考上面链接
     * 1、输入左右可能有空格，先去除左右空格
     * 2、对于length==1的情况，直接判断是否是0-9
     * 3、添加几个计数器减少循环次数（e的个数、小数点的个数、+,-的个数）
     * 4、开始循环
     * 5、对于数字：直接continue，01都是满足条件的
     * 6、对于+\-：最多出现2次（最前面和e的后面），出现直接可以跟数字和.
     * 7、对于e：最多出现1次，出现的位置不能在头和尾
     * 8、对于.：最多1个点，如果之前出现过 e ,则后续不允许有点，出现以后其前后至少有一个数字 .1
     *
     *
     * 执行用时 :3 ms, 在所有 java 提交中击败了94.47%的用户
     * 内存消耗 :36 MB, 在所有 java 提交中击败了89.15%的用户
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        //1、输入左右可能有空格，先去除左右空格
        s = s.trim();
        //2、对于length==1的情况，直接判断是否是0-9
        if (s.length() == 0) return false;
        if (s.length() == 1) {
            return isNumber(s.charAt(0));
        }

        //3、添加几个计数器减少循环次数
        //e的个数
        int eCount = 0;
        //小数点的个数
        int dCount = 0;
        //+,-的个数
        int oCount = 0;

        //4
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isNumber(c)) {
                //5：直接continue，01都是满足条件的
            } else if (c == '-' || c == '+') {
                //6：最多出现2次（最前面和e的后面），出现直接可以跟数字和.
                if (oCount > 1) return false;
                //对满足条件的取反
                if (!((i == 0 && (isNumber(s.charAt(i + 1)) || s.charAt(i + 1) == '.')) || (i > 0 && i < s.length() - 1 && s.charAt(i - 1) == 'e'))) {
                    return false;
                }
                oCount++;
            } else if (c == 'e') {
                //7、对于e：最多出现1次，出现的位置不能在头和尾
                if (eCount > 0) return false;
                if (!(i > 0 && i < s.length() - 1)) {
                    return false;
                }
                eCount++;
            } else if (c == '.') {
                //8、对于.：最多1个点，如果之前出现过 e ,则后续不允许有点，出现以后其前后至少有一个数字 .1
                if (dCount > 0 || eCount > 0) return false;
                //点前后必须有一个数字
                if (i == 0) {
                    if (!isNumber(s.charAt(i + 1)))
                        return false;
                } else if (i == s.length() - 1) {
                    if (!isNumber(s.charAt(i - 1)))
                        return false;
                } else {
                    if (!isNumber(s.charAt(i + 1)) && !isNumber(s.charAt(i - 1))) {
                        return false;
                    }
                }
                dCount++;
            } else {
                return false;
            }
        }

        return true;
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

}
