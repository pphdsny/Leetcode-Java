package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-03-25.
 * 457. 环形数组循环
 * <p>
 * 给定一个含有正整数和负整数的环形数组 nums。 如果某个索引中的数 k 为正数，则向前移动 k 个索引。
 * 相反，如果是负数 (-k)，则向后移动 k 个索引。
 * 因为数组是环形的，所以可以假设最后一个元素的下一个元素是第一个元素，而第一个元素的前一个元素是最后一个元素。
 * <p>
 * 确定 nums 中是否存在循环（或周期）。循环必须在相同的索引处开始和结束并且循环长度 > 1。
 * 此外，一个循环中的所有运动都必须沿着同一方向进行。换句话说，一个循环中不能同时包括向前的运动和向后的运动。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[2,-1,1,2,2]
 * 输出：true
 * 解释：存在循环，按索引 0 -> 2 -> 3 -> 0 。循环长度为 3 。
 * 示例 2：
 * <p>
 * 输入：[-1,2]
 * 输出：false
 * 解释：按索引 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
 * 示例 3:
 * <p>
 * 输入：[-2,1,-1,-2,-2]
 * 输出：false
 * 解释：按索引 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为按索引 1 -> 2 的运动是向前的运动，而按索引 2 -> 1 的运动是向后的运动。一个循环中的所有运动都必须沿着同一方向进行。
 * <p>
 * <p>
 * 提示：
 * <p>
 * -1000 ≤ nums[i] ≤ 1000
 * nums[i] ≠ 0
 * 1 ≤ nums.length ≤ 5000
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你能写出时间时间复杂度为 O(n) 和额外空间复杂度为 O(1) 的算法吗？
 *
 * @see <a href="https://leetcode-cn.com/problems/circular-array-loop/">circular-array-loop</a>
 */
public class _457_circularArrayLoop {

    public static void main(String[] args) {
        _457_circularArrayLoop loop = new _457_circularArrayLoop();
        System.out.println(loop.circularArrayLoop(new int[]{2, -1, 1, 2, 2}));
        System.out.println(loop.circularArrayLoop(new int[]{-1, 2}));
        System.out.println(loop.circularArrayLoop(new int[]{-2, 1, -1, -2, -2}));
        System.out.println(loop.circularArrayLoop(new int[]{1, 2, 2, -1}));
        System.out.println(loop.circularArrayLoop(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, -5}));
    }

    /**
     * 利用快慢指针，如有满足条件的循环存在，一定会相遇
     * 此题解法中需要注意剔除不满足条件的情况，而且循环起始点不一定是0
     *
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {
        int len = nums.length;
        int slowI, fastI;

        for (int i = 0; i < len; i++) {
            slowI = i;
            fastI = getNextIndex(nums, i);
            while (nums[i] * nums[fastI] > 0
                    && nums[i] * nums[getNextIndex(nums, fastI)] > 0) {
                slowI = getNextIndex(nums, slowI);
                fastI = getNextNextIndex(nums, fastI);
                if (slowI == fastI) {
                    if (slowI == getNextIndex(nums, slowI)) {
                        break;
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int getNextNextIndex(int[] nums, int index) {
        int nextIndex = getNextIndex(nums, index);
        int nextNextIndex = getNextIndex(nums, nextIndex);
        return nextNextIndex;
    }

    private int getNextIndex(int[] nums, int index) {
        int len = nums.length;
        int nextIndex = index + nums[index];
        if (nextIndex < 0) {
            while (nextIndex < 0) {
                nextIndex = len + nextIndex;
            }
        } else {
            while (nextIndex >= len) {
                nextIndex = nextIndex - len;
            }
        }
        return nextIndex;
    }
}
