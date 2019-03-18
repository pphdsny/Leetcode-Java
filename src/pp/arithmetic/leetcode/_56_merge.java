package pp.arithmetic.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wangpeng on 2019-03-04.
 * 56. 合并区间
 * <p>
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * @see <a href="https://leetcode-cn.com/problems/merge-intervals/">merge-intervals</a>
 */
public class _56_merge {

    public static void main(String[] args) {
        _56_merge merge = new _56_merge();
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(1, 2));
        list.add(new Interval(10, 12));
        list.add(new Interval(1, 5));
        list.add(new Interval(3, 4));
        list.add(new Interval(6, 9));
        list.add(new Interval(7, 8));

        List<Interval> merge1 = merge.merge(list);
        System.out.println(merge1);
    }

    private List<Interval> merge(List<Interval> intervals) {
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        List<Interval> retList = new ArrayList<>();
        for (int i = 0; i < intervals.size(); i++) {
            Interval item = intervals.get(i);
            Interval preItem;
            if (retList.size() == 0) {
                retList.add(new Interval(item.start, item.end));
            } else {
                preItem = retList.get(retList.size() - 1);
                if (item.start <= preItem.end) {
                    preItem.end = Math.max(item.end, preItem.end);
                } else {
                    retList.add(new Interval(item.start, item.end));
                }
            }
        }

        return retList;
    }

    private static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
