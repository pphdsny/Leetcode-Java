package pp.arithmetic.offer;

/**
 * Created by wangpeng on 2020-08-06.
 * <p>
 * 剑指 Offer 13. 机器人的运动范围
 * <p>
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 * <p>
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _13_movingCount {

    public static void main(String[] args) {
        _13_movingCount movingCount = new _13_movingCount();
        System.out.println(movingCount.movingCount(2,3,1));
        System.out.println(movingCount.movingCount(3,1,0));
        System.out.println(movingCount.movingCount(1,2,1));
        System.out.println(movingCount.movingCount(10,10,2));
        System.out.println(movingCount.movingCount(16,8,4));
    }

    private int retVal = 0;

    /**
     * 解题思路：
     * 使用int[m][n]大小的数组保存行进记录，上下左右进行DFS，不满足条件的跳过
     * 需要注意的可能中间某些行和列相加也满足条件，所以需要整个行和列都需要遍历完（不需要考虑，题中是从0,0开始的）
     *
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int movingCount(int m, int n, int k) {
        if (k < 0 || m <=0 || n<=0) return 0;
        retVal = 0;
        int[][] history = new int[m][n];
        dfs(0,0,m,n,k,history);
        return retVal;
    }

    private void dfs(int cx, int cy, int m, int n, int k, int[][] history) {
        if (cx < 0 || cx >= m || cy < 0 || cy >= n) return;
        if (add(cx, cy) > k) return;
        if (history[cx][cy] == 1) return;
        history[cx][cy] = 1;
        retVal++;
        dfs(cx, cy + 1, m, n, k, history);
        dfs(cx + 1, cy, m, n, k, history);
        dfs(cx, cy - 1, m, n, k, history);
        dfs(cx - 1, cy, m, n, k, history);
    }

    private int add(int x, int y) {
        int retVal = 0;
        retVal += x / 100;
        retVal += (x - x / 100 * 100) / 10;
        retVal += x - x / 100 * 100 - (x - x / 100 * 100) / 10 * 10;
        retVal += y / 100;
        retVal += (y - y / 100 * 100) / 10;
        retVal += y - y / 100 * 100 - (y - y / 100 * 100) / 10 * 10;
        return retVal;
    }
}
