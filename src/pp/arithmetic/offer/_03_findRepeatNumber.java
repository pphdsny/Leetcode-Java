package pp.arithmetic.offer;

import java.util.HashMap;

/**
 * Created by wangpeng on 2020-07-28.
 * 剑指 Offer 03. 数组中重复的数字
 * <p>
 * 找出数组中重复的数字。
 * <p>
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *  
 * <p>
 * 限制：
 * <p>
 * 2 <= n <= 100000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _03_findRepeatNumber {

    public static void main(String[] args) {
        _03_findRepeatNumber findRepeatNumber = new _03_findRepeatNumber();
        int repeatNumber = findRepeatNumber.findRepeatNumber(new int[]{2, 3, 0, 1, 2, 3, 4, 5, 3});
        System.out.println(repeatNumber);
        int repeatNumber2 = findRepeatNumber.findRepeatNumber2(new int[]{2, 3, 0, 1, 2, 3, 4, 5, 3});
        System.out.println(repeatNumber2);
        int repeatNumber3 = findRepeatNumber.findRepeatNumber3(new int[]{2, 3, 0, 1, 2, 3, 4, 5, 3});
        System.out.println(repeatNumber3);
    }

    /**
     * 解题思路：
     * 方案一：最直接的方式，使用一个HashMap进行存储遍历过的数字，性能肯定不会差，内存占用会高
     * <p>
     * 执行用时：13 ms, 在所有 Java 提交中击败了8.98%的用户
     * 内存消耗：48.7 MB, 在所有 Java 提交中击败了100.00%的用户
     * <p>
     * 执行结果有点出人意料，用时偏高
     * <p>
     * 优化方案二：{@link _03_findRepeatNumber#findRepeatNumber2(int[])}
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.getOrDefault(num, false)) {
                return num;
            }
            map.put(num, true);
        }

        return 0;
    }

    /**
     * 针对方案一的提交结果，看看哪些地方可以优化
     * 分析可能是HashMao扩容导致的耗时，用同等大小的数组，保存出现次数
     *
     * 执行用时：2 ms, 在所有 Java 提交中击败了70.22%的用户
     * 内存消耗：46.9 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * 结果满足要求，印证了HashMap扩容存在耗时
     *
     * 最后参考一个最优方案,不需要额外储存空间：{@link _03_findRepeatNumber#findRepeatNumber3(int[])}
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber2(int[] nums) {
        int[] numCounts = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (numCounts[num] != 0) {
                return num;
            }
            numCounts[num]++;
        }

        return 0;
    }

    /**
     * 方案三：
     * 利用数组本身去存储遍历过程的结果，数组第i位就是i，如果后面的有相应的i，则存在重复元素
     * 此方案前提条件：" 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内 "，不然数组可能会越界
     * 此方案还有个劣势：修改了原数组
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了91.54%的用户
     * 内存消耗：47.9 MB, 在所有 Java 提交中击败了100.00%的用户
     * @param nums
     * @return
     */
    public int findRepeatNumber3(int[] nums) {
        int i = 0;
        while(i < nums.length){
            if(i != nums[i]){
                int tmp = nums[nums[i]];
                if(tmp == nums[i]){
                    return tmp;
                }
                nums[nums[i]] = nums[i];
                nums[i] = tmp;
            }else{
                i++;
            }
        }

        return -1;

    }
}
