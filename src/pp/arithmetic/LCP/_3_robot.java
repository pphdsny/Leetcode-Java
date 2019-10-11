package pp.arithmetic.LCP;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangpeng on 2019-10-09.
 * LCP 3. 机器人大冒险
 * <p>
 * 力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。小伙伴事先给机器人输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：
 * <p>
 * U: 向y轴正方向移动一格
 * R: 向x轴正方向移动一格。
 * 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
 * <p>
 * 给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：command = "URR", obstacles = [], x = 3, y = 2
 * 输出：true
 * 解释：U(0, 1) -> R(1, 1) -> R(2, 1) -> U(2, 2) -> R(3, 2)。
 * 示例 2：
 * <p>
 * 输入：command = "URR", obstacles = [[2, 2]], x = 3, y = 2
 * 输出：false
 * 解释：机器人在到达终点前会碰到(2, 2)的障碍物。
 * 示例 3：
 * <p>
 * 输入：command = "URR", obstacles = [[4, 2]], x = 3, y = 2
 * 输出：true
 * 解释：到达终点后，再碰到障碍物也不影响返回结果。
 *  
 * <p>
 * 限制：
 * <p>
 * 2 <= command的长度 <= 1000
 * command由U，R构成，且至少有一个U，至少有一个R
 * 0 <= x <= 1e9, 0 <= y <= 1e9
 * 0 <= obstacles的长度 <= 1000
 * obstacles[i]不为原点或者终点
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/programmable-robot
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _3_robot {

    public static void main(String[] args) {
        _3_robot robot = new _3_robot();
        System.out.println(robot.robot("URR", new int[][]{}, 3, 2));
        System.out.println(robot.robot("URR", new int[][]{{2, 2}}, 3, 2));
        System.out.println(robot.robot("URR", new int[][]{{4, 2}}, 3, 2));
        System.out.println(robot.robot("URRURRR", new int[][]{{7, 7}, {0, 5}, {2, 7}, {8, 6}, {8, 7}, {6, 5}, {4, 4}, {0, 3}, {3, 6}}, 4915, 1966));
        //优化解题
        System.out.println(robot.robot2("URR", new int[][]{}, 3, 2));
        System.out.println(robot.robot2("URR", new int[][]{{2, 2}}, 3, 2));
        System.out.println(robot.robot2("URR", new int[][]{{4, 2}}, 3, 2));
        System.out.println(robot.robot2("URRURRR", new int[][]{{7, 7}, {0, 5}, {2, 7}, {8, 6}, {8, 7}, {6, 5}, {4, 4}, {0, 3}, {3, 6}}, 4915, 1966));
    }

    /**
     * 解题思路：
     * 简单直接能出结果，但是提交超时，主要耗时在障碍物的循环判断上，当障碍物太多了就比较耗时了
     * 比如这个case：https://leetcode-cn.com/submissions/detail/32424313/testcase/
     * <p>
     * 优化求解：{@link _3_robot#robot2(String, int[][], int, int)}
     *
     * @param command
     * @param obstacles
     * @param x
     * @param y
     * @return
     */
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        int index = 0;
        int length = command.length();
        int SX = 0, SY = 0;
        while (true) {
            char c = command.charAt(index % length);
            if (c == 'U') {
                SY++;
            } else if (c == 'R') {
                SX++;
            } else {
                break;
            }
            if (SX == x && SY == y) {
                return true;
            }
            //不考虑内存的话，可以考虑用map保存障碍物位置，本题提交不过x,y太大
            if (isInObstacles(SX, SY, obstacles) || SX > x || SY > y) {
                break;
            }
            index++;
        }

        return false;
    }

    private boolean isInObstacles(int x, int y, int[][] obstacles) {
        if (obstacles.length == 0) return false;
        for (int i = 0; i < obstacles.length; i++) {
            if (x == obstacles[i][0] && y == obstacles[i][1]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解题思路：
     * 1、鉴于指令是不断走循环的，先算出一个循环内横向走的坐标xx和纵向走的坐标yy，后续的可以通过规则落到第一圈的坐标上
     * 2、利用map将一个循环走的坐标保存下来==>xx+"_"+yy,true
     * 3、计算走到目标坐标需要的循环次数，映射到第一圈中是否包含该坐标
     * 4、同理，循环障碍物，看映射到第一圈中是否包含该坐标
     *
     * 映射规则：x ==> x - circle * xx
     * <p>
     * 执行用时 : 3 ms, 在所有 Java 提交中击败了69.76%的用户
     * 内存消耗 :36 MB, 在所有 Java 提交中击败了100.00%的用户
     *
     * @param command
     * @param obstacles
     * @param x
     * @param y
     * @return
     */
    public boolean robot2(String command, int[][] obstacles, int x, int y) {
        int xx = 0, yy = 0;
        Map<String, Boolean> set = new HashMap<>();
        set.put(xx + "_" + yy, true);
        //1
        for (char c : command.toCharArray()) {
            switch (c) {
                case 'U':
                    yy++;
                    break;
                case 'R':
                    xx++;
                    break;
            }
            //2
            set.put(xx + "_" + yy, true);
        }
        //3
        int circle = Math.min(x / xx, y / yy);
        if (!set.getOrDefault((x - circle * xx) + "_" + (y - circle * yy), false)) return false;

        //4
        for (int[] item : obstacles) {
            if (item.length < 2) continue;
            if (item[0] > x || item[1] > y) continue;
            circle = Math.min(item[0] / xx, item[1] / yy);
            if (set.getOrDefault((item[0] - circle * xx) + "_" + (item[1] - circle * yy), false)) return false;
        }

        return true;
    }

}
