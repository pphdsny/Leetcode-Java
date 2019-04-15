package pp.arithmetic.leetcode;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by wangpeng on 2019-04-15.
 * 27. 移除元素
 * <p>
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 示例 1:
 * <p>
 * 给定 nums = [3,2,2,3], val = 3,
 * <p>
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 * <p>
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 * <p>
 * 注意这五个元素可为任意顺序。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
 * int len = removeElement(nums, val);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 *
 * @see <a href="https://leetcode-cn.com/problems/remove-element/">remove-element</a>
 */
public class _27_removeElement {

    public static void main(String[] args) {
        _27_removeElement element = new _27_removeElement();
        System.out.println(element.removeElement(new int[]{3, 2, 2, 3}, 3));
        System.out.println(element.removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
        System.out.println(element.removeElement(new int[]{3, 1, 0}, 11));
        //解法二
        System.out.println(element.removeElement2(new int[]{3, 2, 2, 3}, 3));
        System.out.println(element.removeElement2(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
        System.out.println(element.removeElement2(new int[]{3, 1, 0}, 11));
    }

    /**
     * 解题思路：
     * 难点在O（1）的空间复杂度，需要原地修改
     * 1、先对数组进行排序
     * 2、找到==val的起始位置和终止位置
     * 3、将终止位置后的数字前移至起始位置
     * <p>
     * 更新一个更简洁的写法 {@link _27_removeElement#removeElement2(int[], int)}
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        //1、排序
        Arrays.sort(nums);
        //2、找到起始和结束位置
        int len = nums.length;
        int si = 0, ei = len - 1;
        while (si <= ei) {
            if (nums[si] == val && nums[ei] == val) {
                //3、前移
                int temp = si;
                for (int i = ei + 1; i < len; i++) {
                    nums[temp] = nums[i];
                    temp++;
                }
                return len - ei + si - 1;
            }
            if (nums[si] != val) {
                si++;
            }
            if (nums[ei] != val) {
                ei--;
            }
        }
        if (len % 2 == 0) {
            return len - ei + si - 1;
        } else {
            return len - ei + si - 2;
        }
    }

    /**
     * 解法二，写法更简洁
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement2(int[] nums, int val) {

        int retLen = 0;
        int insertIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                continue;
            }
            nums[insertIndex++] = nums[i];
            retLen++;
        }

        return retLen;
    }
}
