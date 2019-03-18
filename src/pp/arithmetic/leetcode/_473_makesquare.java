package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        long start = System.currentTimeMillis();
        boolean makesquare = makesquare(new int[]{1, 1, 2, 2, 2});
        System.out.println(makesquare);
        boolean makesquare2 = makesquare(new int[]{3, 3, 3, 3, 4});
        System.out.println(makesquare2);
        boolean makesquare3 = makesquare(new int[]{12, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60});
        System.out.println(makesquare3);
        long end = System.currentTimeMillis();
        System.out.println("start-end:" + (end - start));
        Util.printDivideLine();
        start = System.currentTimeMillis();
        boolean makesquare_2 = makesquare2(new int[]{1, 1, 2, 2, 2});
        System.out.println(makesquare_2);
        boolean makesquare2_2 = makesquare2(new int[]{3, 3, 3, 3, 4});
        System.out.println(makesquare2_2);
        boolean makesquare3_2 = makesquare2(new int[]{12, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60});
        System.out.println(makesquare3_2);
        end = System.currentTimeMillis();
        System.out.println("start-end:" + (end - start));

    }

    /**
     * 最直接的方式，不断遍历，不断回溯重试，这个消耗很大。
     * 需要进行一些优化，去掉一些不必要的回溯情况
     *
     * @param nums
     * @return
     */
    public static boolean makesquare(int[] nums) {
        if (nums.length < 4) {
            return false;
        }
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

    public static boolean makesquare2(int[] nums) {
        if (nums == null || nums.length < 4) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 4 != 0) {
            return false;
        }
        int target = sum / 4;
        List<Integer> ok_subset = new ArrayList<>();
        List<Integer> ok_half = new ArrayList<>();
        int all = 1 << nums.length;
        for (int i = 0; i < all; i++) {
            int tempSum = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) != 0) {
                    tempSum += nums[j];
                }
            }
            if (tempSum == target) {
                ok_subset.add(i);
            }
        }
        for (int i = 0; i < ok_subset.size(); i++) {
            for (int j = i + 1; j < ok_subset.size(); j++) {
                if ((ok_subset.get(i) & ok_subset.get(j)) == 0) {
                    ok_half.add((ok_subset.get(i) | ok_subset.get(j)));
                }
            }
        }
        for (int i = 0; i < ok_half.size(); i++) {
            for (int j = i + 1; j < ok_half.size(); j++) {
                if ((ok_half.get(i) & ok_half.get(j)) == 0) {
                    return true;
                }
            }
        }
        return false;
    }

}
