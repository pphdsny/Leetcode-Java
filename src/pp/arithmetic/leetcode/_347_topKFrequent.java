package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.*;

/**
 * Created by wangpeng on 2019-08-22.
 * 347. 前 K 个高频元素
 * <p>
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * <p>
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _347_topKFrequent {

    public static void main(String[] args) {
        _347_topKFrequent topKFrequent = new _347_topKFrequent();
        Util.printList(topKFrequent.topKFrequent(new int[]{1, 2, 1, 2, 1, 4}, 2));
        Util.printList(topKFrequent.topKFrequent(new int[]{4, 1, -1, 2, -1, 2, 3}, 2));
        Util.printList(topKFrequent.topKFrequent1(new int[]{1, 2, 1, 2, 1, 4}, 2));
        Util.printList(topKFrequent.topKFrequent1(new int[]{4, 1, -1, 2, -1, 2, 3}, 2));
    }

    /**
     * 解题思路：
     * 1.遍历count使用HashMap进行保存
     * 2.从遍历的count中找到最K大的数字，此时使用了PriorityQueue进行保存，队列顶部就是最小的
     * 3.将PriorityQueue结果转换为List返回
     *
     * 执行用时 :104 ms, 在所有 Java 提交中击败了16.20%的用户
     * 内存消耗 :45.8 MB, 在所有 Java 提交中击败了57.61%的用户
     *
     * 时间复杂度O(NLogN)
     *
     * 更优解法：{@link _347_topKFrequent#topKFrequent1(int[], int)}
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> map.get(n1) - map.get(n2));
        for (int n : map.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }
        List<Integer> retList = new LinkedList();
        while (!heap.isEmpty())
            retList.add(heap.poll());
        Collections.reverse(retList);
        return retList;
    }


    /**
     * 更优解法，优化了建堆和排序的消耗
     *
     * 执行用时 :28 ms, 在所有 Java 提交中击败了89.78%的用户
     * 内存消耗 :46.8 MB, 在所有 Java 提交中击败了41.99%的用户
     *
     * 时间复杂度O(N)
     *
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<Integer>[] count = new ArrayList[nums.length + 1];
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (count[freq] == null) {
                count[freq] = new ArrayList<>();
            }
            count[freq].add(key);
        }

        List<Integer> result = new ArrayList<>(k);
        int remain = k;

        for (int i = nums.length; i > 0 && remain > 0; i--) {
            if (count[i] != null) {
                if (remain > count[i].size()) {
                    result.addAll(count[i]);
                    remain -= count[i].size();
                } else {
                    result.addAll(count[i].subList(0, remain));
                    remain = 0;
                }
            }
        }

        return result;
    }


}

