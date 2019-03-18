package pp.arithmetic.leetcode;

import javafx.util.Pair;
import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2018/9/13.
 * 315.计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 * <p>
 * 示例:
 * <p>
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 *
 * @see <a href="https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self/description/">count-of-smaller-numbers-after-self</a>
 */
public class _315_countSmaller {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Integer> list = countSmaller(new int[]{5, 2, 6, 1});
        long end = System.currentTimeMillis();
        //12ms,效率低下
        System.out.println("(end-start):" + (end - start));
        Util.printList(list);
    }

    /**
     * 最直接的方法遍历一次就知道，不过这个时间复杂度在O(n^2)，肯定过不了，因为这道题的难度是Hard。
     * 猜测最好的办法应该是通过分治思想中的归并排序，在排序过程中就将此题给解出来。那么复杂度就是O(nlogn)
     * 排序最大的问题，就是排好序的数组和原数组的顺序就不一样了，所以得有和原数组相关的对应关系在。
     *
     * @param nums
     * @return
     */
    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> countList = new ArrayList<>();
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(new Pair<>(nums[i], i));
            countList.add(new Integer(0));
        }
        merge(list, countList);
        return countList;
    }

    private static void merge(List<Pair<Integer, Integer>> list,
                              List<Integer> countList) {
        if (list.size() <= 1) {
            return;
        }
        int middle = list.size() / 2;
        List<Pair<Integer, Integer>> leftList = new ArrayList<>(list.subList(0, middle));
        List<Pair<Integer, Integer>> rightList = new ArrayList<>(list.subList(middle, list.size()));
        merge(leftList, countList);
        merge(rightList, countList);
        list.clear();
        mergeTwo(leftList, rightList, list, countList);
    }

    private static void mergeTwo(List<Pair<Integer, Integer>> leftList,
                                 List<Pair<Integer, Integer>> rightList,
                                 List<Pair<Integer, Integer>> list,
                                 List<Integer> countList) {
        int i = 0;
        int j = 0;
        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).getKey() > rightList.get(j).getKey()) {
                list.add(rightList.get(j));
                j++;
            } else {
                list.add(leftList.get(i));
                //更新count
                Integer index = leftList.get(i).getValue();
                countList.set(index, countList.get(index) + j);
                i++;
            }
        }
        while (i < leftList.size()) {
            list.add(leftList.get(i));
            //更新count
            Integer index = leftList.get(i).getValue();
            countList.set(index, countList.get(index) + j);
            i++;
        }
        while (j < rightList.size()) {
            list.add(rightList.get(j));
            j++;
        }
    }
}
