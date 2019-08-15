package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-08-07.
 * 287. 寻找重复数
 * <p>
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * <p>
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n^2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _287_findDuplicate {

    public static void main(String[] args) {
        _287_findDuplicate findDuplicate = new _287_findDuplicate();
//        System.out.println(findDuplicate.findDuplicate(new int[]{1, 3, 4, 2, 2}));
//        System.out.println(findDuplicate.findDuplicate(new int[]{3, 1, 3, 4, 2}));
        System.out.println(findDuplicate.findDuplicate(new int[]{2, 5, 9, 6, 9, 3, 8, 9, 7, 1}));
    }

    /**
     * 题意解读：
     * 1、数组只读==>不能对数组进行重排序==>排序取连续两个相同的
     * 2、O(1)空间==>不能用哈希等进行遍历存储==>哈希取出现次数>1的
     * 3、O(n^2)的时间复杂度==>少于2次循环遍历，可以一次循环或者二分
     * 如果没有上述限制，上面的方法都可行
     * <p>
     * 解题思路：
     * 仔细看题目，发现数组大小n+1，数组数字1-n，一定会存在重复数字
     * 从0开始遍历，最开始一条直线，到后面会形成个环，可参考这张图 https://img-blog.csdn.net/20160101111128525
     * 从图中来看，环和直线相遇的点就是重复数
     * 1.用快慢指针，找到第一次相遇的点
     * 2.将一个指针移至起始点，再次相遇的一定是环和直线相遇的点，也就是重复数
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        // 1.找到第一次相遇点
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // 2.找第二次相遇点
        int ptr1 = nums[0];
        int ptr2 = slow;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }
}
