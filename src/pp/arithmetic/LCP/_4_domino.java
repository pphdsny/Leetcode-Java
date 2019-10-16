package pp.arithmetic.LCP;

/**
 * Created by wangpeng on 2019-10-14.
 * LCP 4. 覆盖
 * <p>
 * 你有一块棋盘，棋盘上有一些格子已经坏掉了。你还有无穷块大小为1 * 2的多米诺骨牌，你想把这些骨牌不重叠地覆盖在完好的格子上，请找出你最多能在棋盘上放多少块骨牌？这些骨牌可以横着或者竖着放。
 * <p>
 *  
 * <p>
 * 输入：n, m代表棋盘的大小；broken是一个b * 2的二维数组，其中每个元素代表棋盘上每一个坏掉的格子的位置。
 * <p>
 * 输出：一个整数，代表最多能在棋盘上放的骨牌数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2, m = 3, broken = [[1, 0], [1, 1]]
 * 输出：2
 * 解释：我们最多可以放两块骨牌：[[0, 0], [0, 1]]以及[[0, 2], [1, 2]]。（见下图）
 * <p>
 *  <image src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/09/09/domino_example_1.jpg" />
 *  
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 3, m = 3, broken = []
 * 输出：4
 * 解释：下 图是其中一种可行的摆放方式
 * <p>
 *  <image src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/09/09/domino_example_2.jpg" />
 * <p>
 * 限制：
 * <p>
 * 1 <= n <= 8
 * 1 <= m <= 8
 * 0 <= b <= n * m
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/broken-board-dominoes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _4_domino {

    public static void main(String[] args) {
        _4_domino domino = new _4_domino();
        System.out.println(domino.domino(2, 3, new int[][]{{1, 0}, {1, 1}}));
        System.out.println(domino.domino(3, 3, new int[][]{}));
        System.out.println(domino.domino(8, 8, new int[][]{
                {1, 0}, {2, 5}, {3, 1}, {3, 2}, {3, 4}, {4, 0}, {4, 3}, {4, 6}, {4, 7}, {5, 3}, {5, 5}, {5, 6}, {6, 3}, {7, 2}, {7, 7}
        }));
    }

    int ret = 0; //摆放计数
    int max = 0; //最大值计数

    /**
     * 解题思路：
     * 1、构建棋盘，将棋盘上正常的为0，坏了的位置为2，摆放了骨牌的为1
     * 2、对于一个位置和骨牌有三种组合：横着放、竖着放、不放，如需计算正确的值需要所有的情况都计算到，所以回溯实现
     * <p>
     * 执行耗时不是很好，但是自己写出来的好理解
     * 执行用时 :1297 ms, 在所有 java 提交中击败了12.90%的用户
     * 内存消耗 :35.6 MB, 在所有 java 提交中击败了100.00%的用户
     *
     * @param n
     * @param m
     * @param broken
     * @return
     */
    public int domino(int n, int m, int[][] broken) {
        ret = 0;
        max = 0;
        //构造棋盘
        int[][] map = new int[n][m];
        for (int i = 0; i < broken.length; i++) {
            int[] item = broken[i];
            map[item[0]][item[1]] = 2;
        }
        dfs(map, 0, 0);

        return max;
    }

    /**
     * 循环的过程是横向一行行逐格摆放，如到一行的末尾无法摆放则换行，如超过行数则计算摆放的最大个数
     *
     * @param map
     * @param row
     * @param col
     */
    private void dfs(int[][] map, int row, int col) {
        if (row >= map.length) { //如超过行数则计算摆放的最大个数
            max = Math.max(max, ret);
            return;
        }
        if (col >= map[row].length) {//如到一行的末尾无法摆放则换行
            dfs(map, row + 1, 0);
            return;
        }
        if (map[row][col] > 0) {//遇坏格子则跳过
            dfs(map, row, col + 1);
            return;
        }
        //试着横着放
        boolean h = false;
        if (col < map[row].length - 1 && map[row][col + 1] == 0) {
            h = true;
            map[row][col]++;
            map[row][col + 1]++;
            ret++;
            dfs(map, row, col + 2);
            //横向状态重置
            ret--;
            map[row][col]--;
            map[row][col + 1]--;
        }
        //试着竖着放
        boolean v = false;
        if (row < map.length - 1 && map[row + 1][col] == 0) {
            v = true;
            map[row][col]++;
            map[row + 1][col]++;
            ret++;
            dfs(map, row, col + 1);
            //竖向状态重置
            ret--;
            map[row][col]--;
            map[row + 1][col]--;
        }
        //如横着和竖着都不行，试着不放，跳2格
        if (!h && !v) {
            dfs(map, row, col + 2);
        }
    }
}
