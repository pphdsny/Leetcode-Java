package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-08-07.
 * 283. 移动零
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _283_moveZeroes {
    public static void main(String[] args) {
        _283_moveZeroes moveZeroes = new _283_moveZeroes();
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes.moveZeroes(nums);
        Util.printArray(nums);
    }

    /**
     * 解题思路：
     * 1.遍历数组，找到第一个0并标记zeroIndex
     * 2.找到下一个非0，将其与0交互，zeroIndex++
     * 3.找到下一个0，不做任何处理
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int zeroIndex = -1;
        //1
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == 0) {
                //1
                if (zeroIndex == -1) {
                    zeroIndex = i;
                }
                //3
            } else {
                //2
                if (zeroIndex != -1) {
                    nums[zeroIndex] = num;
                    nums[i] = 0;
                    zeroIndex++;
                }
            }
        }
    }
}
