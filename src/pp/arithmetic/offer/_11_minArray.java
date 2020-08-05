package pp.arithmetic.offer;

/**
 * Created by wangpeng on 2020-08-05.
 *
 *
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _11_minArray {

    public static void main(String[] args) {
        _11_minArray minArray = new _11_minArray();
        System.out.println(minArray.minArray(new int[]{3,4,5,1,2}));
        System.out.println(minArray.minArray(new int[]{2,2,2,0,1}));
    }

    /**
     * 解题思路：
     * 递增数组经过一次旋转，从递增到递减的转折点，则是最小的
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) return 0;
        int retVal = numbers[0];
        int preVal = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            int number = numbers[i];
            if (number >= preVal){
                preVal = number;
            }else{
                retVal = number;
                break;
            }
        }

        return retVal;
    }
}
