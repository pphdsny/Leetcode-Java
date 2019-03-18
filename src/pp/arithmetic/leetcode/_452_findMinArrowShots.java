package pp.arithmetic.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by wangpeng on 2018/9/7.
 * 452. 用最少数量的箭引爆气球
 * <p>
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。
 * 由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。
 * 开始坐标总是小于结束坐标。平面内最多存在104个气球。
 * <p>
 * 一支弓箭可以沿着x轴从不同点完全垂直地射出。
 * 在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
 * 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。
 * 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * <p>
 * Example:
 * <p>
 * 输入:
 * [[10,16], [2,8], [1,6], [7,12]]
 * <p>
 * 输出:
 * 2
 * <p>
 * 解释:
 * 对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
 *
 * @see <a href="https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/description/">minimum-number-of-arrows-to-burst-balloons</a>
 */
public class _452_findMinArrowShots {
    public static void main(String[] args) {
        //2
        int minArrowShots1 = findMinArrowShots(new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}});
        System.out.println(minArrowShots1);
        //4
        int minArrowShots2 = findMinArrowShots(new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}});
        System.out.println(minArrowShots2);
        //1
        int minArrowShots3 = findMinArrowShots(new int[][]{{-1111, 2222}});
        System.out.println(minArrowShots3);
        //0
        int minArrowShots4 = findMinArrowShots(new int[][]{});
        System.out.println(minArrowShots4);
        //3
        int minArrowShots5 = findMinArrowShots(new int[][]{{0, 9}, {1, 8}, {7, 8}, {1, 6}, {9, 16}, {7, 13}, {7, 10}, {6, 11}, {6, 9}, {9, 13}});
        System.out.println(minArrowShots5);
        //2
        int minArrowShots6 = findMinArrowShots(new int[][]{{9, 17}, {4, 12}, {4, 8}, {4, 8}, {7, 13}, {3, 4}, {7, 12}, {9, 15}});
        System.out.println(minArrowShots6);
    }

    /**
     * 贪心策略：一支箭尽可能多的覆盖气球的范围就能用最少的箭
     *
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        if (points.length < 2) {
            return points.length;
        }
        //先按照起始位置进行排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        //遍历找到箭能覆盖的区域
        int start = points[0][0];
        int end = points[0][1];
        int count = 1;
        for (int i = 1; i < points.length; i++) {
            int[] point = points[i];
            int pointStart = point[0];
            int pointEnd = point[1];
            if (pointStart > end) {
                count++;
                start = pointStart;
                end = pointEnd;
                continue;
            }
            if (pointStart > start) {
                start = pointStart;
            }
            if (pointEnd < end) {
                end = pointEnd;
            }
        }
        return count;
    }

}
