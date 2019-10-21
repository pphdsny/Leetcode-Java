package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-10-21.
 * 67. 二进制求和
 * <p>
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _67_addBinary {

    public static void main(String[] args) {
        _67_addBinary addBinary = new _67_addBinary();
        System.out.println(addBinary.addBinary("11", "1"));
        System.out.println(addBinary.addBinary("1010", "1011"));
        System.out.println(addBinary.addBinary("1111", "1111"));
    }

    /**
     * 解题思路：
     * 从低位开始累加，注意两边字符串不一致，提高执行效率不要使用StringBuilder
     *
     * 执行用时 :1 ms, 在所有 java 提交中击败了100.00%的用户
     * 内存消耗 :36 MB, 在所有 java 提交中击败了55.45%的用户
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        char[] result = new char[Math.max(i, j) + 1];
        int pos = result.length - 1;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            //>>1 代表 /2，进位
            carry = sum >> 1;
            //sum & 0x01 ==> 进位后只取低位
            result[pos--] = (char) ((sum & 0x01) + '0');
        }
        if (carry > 0) { //最后有进位，直接进行数据拼接，防止数组越界
            return "1" + String.valueOf(result);
        }
        return String.valueOf(result);
    }
}

