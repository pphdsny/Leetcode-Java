package pp.arithmetic.medium;

import java.util.Arrays;

/**
 * Created by wangpeng on 2018/9/11.
 * 473.火柴拼正方形
 * 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。
 * 不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。
 * <p>
 * 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,1,2,2,2]
 * 输出: true
 * <p>
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 * 示例 2:
 * <p>
 * 输入: [3,3,3,3,4]
 * 输出: false
 * <p>
 * 解释: 不能用所有火柴拼成一个正方形。
 * 注意:
 * <p>
 * 给定的火柴长度和在 0 到 10^9之间。
 * 火柴数组的长度不超过15。
 *
 * @see <a href="https://leetcode-cn.com/problems/matchsticks-to-square/description/">matchsticks-to-square</a>
 */
public class _473_makesquare {

    public static void main(String[] args) {
        boolean makesquare = makesquare(new int[]{1, 1, 2, 2, 2});
        System.out.println(makesquare);
        boolean makesquare2 = makesquare(new int[]{3, 3, 3, 3, 4});
        System.out.println(makesquare2);
    }

    /**
     * 最直接的方式，不断遍历，不断回溯重试，这个消耗很大。
     * 需要进行一些优化，去掉一些不必要的回溯情况
     *
     * @param nums
     * @return
     */
    public static boolean makesquare(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 4 != 0) {
            return false;
        }
        Arrays.sort(nums);
        sort(nums);
        int[] bucket = new int[]{0, 0, 0, 0};
        return generate(nums, 0, sum / 4, bucket);
    }

    private static boolean generate(int[] nums,
                                    int index,
                                    int target,
                                    int[] bucket) {
        if (index >= nums.length) {
            return bucket[0] == target && bucket[1] == target
                    && bucket[2] == target && bucket[3] == target;
        }
        for (int i = 0; i < 4; i++) {
            if (bucket[i] + nums[index] > target) {
                continue;
            }
            bucket[i] += nums[index];
            boolean generate = generate(nums, index + 1, target, bucket);
            if (generate) {
                return true;
            }
            bucket[i] -= nums[index];
        }
        return false;
    }

    private static void sort(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

}
