package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-07-30.
 * 136. 只出现一次的数字
 *
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 *
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 *
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 *
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _136_singleNumber {
    public static void main(String[] args) {
        _136_singleNumber singleNumber = new _136_singleNumber();
        System.out.println(singleNumber.singleNumber(new int[]{2, 2, 1}));
        System.out.println(singleNumber.singleNumber(new int[]{4, 1, 2, 1, 2}));
    }

    /**
     * 思考：
     * 线性的时间复杂度就是O(n)，不适用额外空间也就是最好常量级空间也不要有
     * O(n)的复杂度只允许遍历数组一次，一次遍历如何标识数字出现的次数？不能用额外空间就只能用数组本身进行存储
     * 是不是可以建立数字在数组中位置的映射关系？
     *
     * 解题思路：
     * 映射关系最常见的就是哈希表，比如数字对数组长度取模，但是可能会发生冲突（两个数组取模结果一致），需解决，处理复杂==>放弃
     * 看了提示说位运算，思考了下的确可以，判断只出现一次，其他都出现两次，正好可以使用异或运算，两次结果复原，出现一次的数据停留在bit上
     * 1.取bit=0
     * 2.循环遍历数组，与bit做异或操作
     * 3.将最终的bit返回
     *
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int bit = 0;
        for (int i = 0; i < nums.length; i++) {
            bit = bit ^ nums[i];
        }
        return bit;
    }
}
