package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/9/6.
 * 376. 摆动序列
 * <p>
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。
 * 第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 * <p>
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。
 * 相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 * <p>
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。
 * 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,7,4,9,2,5]
 * 输出: 6
 * 解释: 整个序列就是一个摆动序列。
 * <p>
 * 输入: [1,17,5,10,13,15,10,5,16,8]
 * 输出: 7
 * 解释: 它的几个子序列满足摆动序列。其中一个是[1,17,10,13,10,16,8]。
 * <p>
 * 输入: [1,2,3,4,5,6,7,8,9]
 * 输出: 2
 * <p>
 * 进阶:
 * 你能否用 O(n) 时间复杂度完成此题?
 *
 * @see <a herf="https://leetcode-cn.com/problems/wiggle-subsequence/description/">wiggle-subsequence</a>
 */
public class _376_wiggleMaxLength {

    public static void main(String[] args) {
        int i1 = wiggleMaxLength(new int[]{1, 7, 4, 2, 3, 1, 9, 2, 5});
        System.out.println(i1);
        int i2 = wiggleMaxLength(new int[]{1, 17, 5, 10, 13, 15, 10, 5, 16, 8});
        System.out.println(i2);
        int i3 = wiggleMaxLength(new int[]{1, 2});
        System.out.println(i3);
    }

    /**
     * 贪心策略：对于连续增长或者下降的队列，最后一次反转时候的数字获得摇摆的概率会更大
     * 第一次考虑的时候，用一个栈去存储摇摆序列顺序，栈的大小即为摇摆序列的长度。
     * 后来参考别人想到，只需要长度的话，那维护一个自增的常量即可。
     *
     * @param nums
     * @return
     */
    public static int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        final int BEGIN = 0;
        final int UP = 1;
        final int DOWN = 2;
        int status = BEGIN;
        int maxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            switch (status) {
                case BEGIN:
                    if (nums[i] > nums[i - 1]) {
                        status = UP;
                        maxLength++;
                    } else if ((nums[i] < nums[i - 1])) {
                        status = DOWN;
                        maxLength++;
                    }
                    break;
                case UP:
                    if (nums[i] > nums[i - 1]) {
                        status = UP;
                    } else if ((nums[i] < nums[i - 1])) {
                        status = DOWN;
                        maxLength++;
                    }
                    break;
                case DOWN:
                    if (nums[i] > nums[i - 1]) {
                        status = UP;
                        maxLength++;
                    } else if ((nums[i] < nums[i - 1])) {
                        status = DOWN;
                    }
                    break;
            }
        }
        return maxLength;
    }
}
