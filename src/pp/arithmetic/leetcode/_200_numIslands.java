package pp.arithmetic.leetcode;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by wangpeng on 2018/10/12.
 * 200.岛屿的个数
 * <p>
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * <p>
 * 输出: 3
 *
 * @see <a href="https://leetcode-cn.com/problems/number-of-islands/description/">number-of-islands</a>
 */
public class _200_numIslands {

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        int i = numIslands(grid);
        System.out.println(i);
        char[][] grid1 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '1'}
        };
        int i1 = numIslands(grid1);
        System.out.println(i1);
        char[][] grid2 = {
                {}
        };
        int i2 = numIslands(grid2);
        System.out.println(i2);
    }

    private static final int[] dx = new int[]{-1, 0, 1, 0};
    private static final int[] dy = new int[]{0, -1, 0, 1};

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        char[][] mark = new char[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                mark[i][j] = '0';
            }
        }
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1' && mark[i][j] != '1') {
//                    dfs(grid, mark, i, j);
                    Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
                    queue.add(new Pair<>(i, j));
                    bfs(grid, mark, queue);
                    result++;
                }
            }
        }
        return result;
    }

    private static void dfs(char[][] grid, char[][] mark, int x, int y) {
        if (x < 0 || y < 0 || x > grid.length - 1 || y > grid[x].length - 1) {
            return;
        }
        if (grid[x][y] == '0' || mark[x][y] == '1') {
            return;
        }
        mark[x][y] = '1';
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            dfs(grid, mark, newX, newY);
        }
    }

    private static void bfs(char[][] grid, char[][] mark, Queue<Pair<Integer, Integer>> queue) {
        if (queue.isEmpty()) {
            return;
        }
        Pair<Integer, Integer> poll = queue.poll();
        int x = poll.getKey();
        int y = poll.getValue();
        if (x < 0 || y < 0 || x > grid.length - 1 || y > grid[x].length - 1) {
            return;
        }
        if (grid[x][y] == '0' || mark[x][y] == '1') {
            return;
        }
        mark[x][y] = '1';
        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            queue.add(new Pair<>(newX, newY));
        }
        while (!queue.isEmpty()) {
            bfs(grid, mark, queue);
        }
    }
}
