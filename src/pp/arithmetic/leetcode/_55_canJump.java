package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/9/6.
 * 55. 跳跃游戏
 * <p>
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个位置。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 * 示例 2:
 * <p>
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 * @see <a href="https://leetcode-cn.com/problems/jump-game/description/">jump-game</a>
 */
public class _55_canJump {

    public static void main(String[] args) {
        boolean b1 = canJump(new int[]{2, 3, 1, 1, 4});
        System.out.println(b1);
        boolean b2 = canJump(new int[]{3, 2, 1, 0, 4});
        System.out.println(b2);
        boolean b3 = canJump(new int[]{2, 0, 0});
        System.out.println(b3);
        boolean b4 = canJump(new int[]{0});
        System.out.println(b4);
    }

    /**
     * 贪心算法：每一步都跳的更远（index+num[i]最大），就更有机会跳到最后
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int[] index = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            index[i] = i + nums[i];
        }
        int jump = 0;
        int maxIndex = index[0];
        while (jump < nums.length && jump <= maxIndex) {
            if (index[jump] > maxIndex) {
                maxIndex = index[jump];
            }
            jump++;
        }
        if (jump == nums.length) {
            return true;
        }
        return false;
    }

    /**
     * 官方解答，更优，不需要额外的存储空间，循环也少一半
     *
     * @param nums
     * @return
     */
    public static boolean canJumpOther(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
}
