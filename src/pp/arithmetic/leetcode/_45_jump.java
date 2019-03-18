package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/9/7.
 * 45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 * <p>
 * 假设你总是可以到达数组的最后一个位置。
 *
 * @see <a href="https://leetcode-cn.com/problems/jump-game-ii/description/">jump-game-ii</a>
 */
public class _45_jump {
    public static void main(String[] args) {
        int b1 = jump(new int[]{2, 3, 1, 1, 4});
        //2
        System.out.println(b1);
        //1
        int b2 = jump(new int[]{1, 2});
        System.out.println(b2);
        //1
        int b3 = jump(new int[]{2, 0, 0});
        System.out.println(b3);
        //0
        int b4 = jump(new int[]{0});
        System.out.println(b4);
    }

    /**
     * 贪心规律：每步走的更远，那么用的步数就越少
     *
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }
        //1,2
        //2, 3, 1, 1, 4
        int step = 1;
        //之前最大的
        int pre = nums[0];
        //当前能走最远的
        int cur = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (i > cur) {
                step++;
                cur = pre;
            }
            if (i + nums[i] > pre) {
                pre = i + nums[i];
            }
        }
        return step;
    }
}
