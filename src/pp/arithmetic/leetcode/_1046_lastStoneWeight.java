package pp.arithmetic.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wangpeng on 2019-06-04.
 * 1046. 最后一块石头的重量
 * <p>
 * 有一堆石头，每块石头的重量都是正整数。
 * <p>
 * 每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * <p>
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 *
 * @see <a href="https://leetcode-cn.com/problems/last-stone-weight/">last-stone-weight</a>
 */
public class _1046_lastStoneWeight {

    public static void main(String[] args) {
        _1046_lastStoneWeight lastStoneWeight = new _1046_lastStoneWeight();
        System.out.println(lastStoneWeight.lastStoneWeight(new int[]{4, 3, 4, 3, 2}));
        System.out.println(lastStoneWeight.lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
        System.out.println(lastStoneWeight.lastStoneWeight(new int[]{3, 7, 2}));
        System.out.println(lastStoneWeight.lastStoneWeight(new int[]{3, 7, 8}));
    }

    /**
     * 解题思路：
     * 1.先将数组转换成有序list
     * 2.从list中取2个最大的数进行粉碎，得到结果>0则插入list，使其还有序
     * 3.重复步骤2直到剩余数<=1
     * <p>
     * 难点：如何构建有序数并对其进行插入排序
     * 1.先快排（O(nlogn)），顺序遍历插入（O(n)） ==>执行用时 : 5 ms, 在Last Stone Weight的Java提交中击败了43.43% 的用户
     * 2.先快排（O(nlogn)），二分插入排序（O(logn)）==>执行用时 : 5 ms, 在Last Stone Weight的Java提交中击败了43.43% 的用户
     * 测试用例数据量并不是很大，两种解题耗时相差不大
     *
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        List<Integer> stoneList = sort(stones);
        while (stoneList.size() > 1) {
            Integer s1 = stoneList.remove(stoneList.size() - 1);
            Integer s2 = stoneList.remove(stoneList.size() - 1);
            int ds = s1 - s2;
            if (ds > 0) {
                insert(stoneList, ds);
            }
        }
        return stoneList.size() > 0 ? stoneList.get(0) : 0;
    }

    private void insertBinary(List<Integer> stoneList, int insert) {
        int si = 0, ei = stoneList.size() - 1, mi = 0;
        while (si <= ei) {
            mi = (si + ei) / 2;
            Integer mNum = stoneList.get(mi);
            if (mNum == insert) {
                stoneList.add(mi, insert);
                return;
            } else if (mNum < insert) {
                si = mi + 1;
            } else {
                ei = mi - 1;
            }
        }
        stoneList.add(si, insert);
    }

    private void insert(List<Integer> stoneList, int insert) {
        int insertIndex = 0;
        for (int i = 0; i < stoneList.size(); i++) {
            if (stoneList.get(i) < insert) {
                insertIndex = i + 1;
            } else {
                break;
            }
        }
        stoneList.add(insertIndex, insert);
    }

    private List<Integer> sort(int[] stones) {
        List<Integer> retList = new LinkedList<>();
        Arrays.sort(stones);
        for (int i = 0; i < stones.length; i++) {
            retList.add(stones[i]);
        }
        return retList;
    }
}
