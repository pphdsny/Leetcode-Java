package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-03-04.
 * 42. 接雨水
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png"></img>
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * @see <a href="https://leetcode-cn.com/problems/trapping-rain-water/">trapping-rain-water</a>
 */
public class _42_trap {

    public static void main(String[] args) {
        _42_trap trap = new _42_trap();
        int trap1 = trap.trap(new int[]{0, 1, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        System.out.println(trap1);
        System.out.println(trap.trap(new int[]{2, 6, 3, 8, 2, 7, 2, 5, 0}));
    }

    public int trap(int[] height) {
        int n = height.length, idx = 0, lefth = 0, righth = 0, area = 0;
        for (int i = 0; i < n; i++)
            idx = height[idx] <= height[i] ? i : idx;
        for (int i = 0; i < idx; i++) {
            if (height[i] < lefth)
                area += lefth - height[i];
            else
                lefth = height[i];
        }
        for (int i = n - 1; i > idx; i--) {
            if (height[i] < righth)
                area += righth - height[i];
            else
                righth = height[i];
        }
        return area;
    }
}
