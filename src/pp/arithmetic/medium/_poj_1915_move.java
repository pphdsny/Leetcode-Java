package pp.arithmetic.medium;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created by wangpeng on 2018/10/16.
 * poj1915.骑士移动
 * <p>
 * 已知一个n*n的棋盘，在这个棋盘上设置两个位置，起始位置(beginx, beginy)与终点位置(endx, endy)，
 * x代表是第x行，y代表是第y列，骑士每次可以按照下图的8个方向进行跳跃，
 * 求起始位置跳 跃至终点位置最少用几步?(n最大不超过300)。
 *
 * @see <a href="http://poj.org/problem?id=1915">poj-1915</a>
 */
public class _poj_1915_move {

    public static void main(String[] args) {
        int max = 300;
        int beginX = new Random().nextInt(max);
        int beginY = new Random().nextInt(max);
        int endX = new Random().nextInt(max);
        int endY = new Random().nextInt(max);
        System.out.println(beginX + "," + beginY + "," + endX + "," + endY);
        int move = move(beginX, beginY, endX, endY, max);
//        int move = move(1, 1, 2, 3, 4);
        System.out.println(move);
    }

    private static final int[] dx = new int[]{-1, 0, 1, 1, 1, 0, -1, -1};
    private static final int[] dy = new int[]{-1, -1, -1, 0, 1, 1, 1, 0};

    public static int move(int beginX, int beginY,
                           int endX, int endY, int n) {

        Queue<Location> queue = new LinkedList();
        int[][] mark = new int[n][n];
        queue.add(new Location(beginX, beginY, 1));
        mark[beginX][beginY] = 1;
        //不能用递归，当N=300时候，会抛StackOverflowException
        while (!queue.isEmpty()) {
            Location poll = queue.poll();
            if (poll.x == endX && poll.y == endY) {
                return poll.step;
            }
            for (int i = 0; i < 8; i++) {
                int newX = poll.x + dx[i];
                int newY = poll.y + dy[i];
                if (newX < 0 || newY < 0 || newX >= n || newY >= n || mark[newX][newY] == 1) {
                    continue;
                }
                mark[newX][newY] = 1;
                queue.add(new Location(newX, newY, poll.step + 1));
            }
        }
        return -1;
    }

    private static class Location {
        public int x;
        public int y;
        public int step;

        public Location(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}
