package pp.arithmetic.LCP;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2019-10-11.
 *
 * 力扣决定给一个刷题团队发LeetCoin作为奖励。同时，为了监控给大家发了多少LeetCoin，力扣有时候也会进行查询。
 *
 *  
 *
 * 该刷题团队的管理模式可以用一棵树表示：
 *
 * 团队只有一个负责人，编号为1。除了该负责人外，每个人有且仅有一个领导（负责人没有领导）；
 * 不存在循环管理的情况，如A管理B，B管理C，C管理A。
 *  
 *
 * 力扣想进行的操作有以下三种：
 *
 * 给团队的一个成员（也可以是负责人）发一定数量的LeetCoin；
 * 给团队的一个成员（也可以是负责人），以及他/她管理的所有人（即他/她的下属、他/她下属的下属，……），发一定数量的LeetCoin；
 * 查询某一个成员（也可以是负责人），以及他/她管理的所有人被发到的LeetCoin之和。
 *  
 *
 * 输入：
 *
 * N表示团队成员的个数（编号为1～N，负责人为1）；
 * leadership是大小为(N - 1) * 2的二维数组，其中每个元素[a, b]代表b是a的下属；
 * operations是一个长度为Q的二维数组，代表以时间排序的操作，格式如下：
 * operations[i][0] = 1: 代表第一种操作，operations[i][1]代表成员的编号，operations[i][2]代表LeetCoin的数量；
 * operations[i][0] = 2: 代表第二种操作，operations[i][1]代表成员的编号，operations[i][2]代表LeetCoin的数量；
 * operations[i][0] = 3: 代表第三种操作，operations[i][1]代表成员的编号；
 * 输出：
 *
 * 返回一个数组，数组里是每次查询的返回值（发LeetCoin的操作不需要任何返回值）。由于发的LeetCoin很多，请把每次查询的结果模1e9+7 (1000000007)。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：N = 6, leadership = [[1, 2], [1, 6], [2, 3], [2, 5], [1, 4]], operations = [[1, 1, 500], [2, 2, 50], [3, 1], [2, 6, 15], [3, 1]]
 * 输出：[650, 665]
 * 解释：团队的管理关系见下图。
 * 第一次查询时，每个成员得到的LeetCoin的数量分别为（按编号顺序）：500, 50, 50, 0, 50, 0;
 * 第二次查询时，每个成员得到的LeetCoin的数量分别为（按编号顺序）：500, 50, 50, 0, 50, 15.
 *
 *
 *  <image src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/09/09/coin_example_1.jpg" />
 *
 * 限制：
 *
 * 1 <= N <= 50000
 * 1 <= Q <= 50000
 * operations[i][0] != 3 时，1 <= operations[i][2] <= 5000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/coin-bonus
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _5_bonus {

    public static void main(String[] args) {
        _5_bonus bonus = new _5_bonus();
        Util.printArray(bonus.bonus(6, new int[][]{{1, 2}, {1, 6}, {2, 3}, {2, 5}, {1, 4}}, new int[][]{{1, 1, 500}, {2, 2, 50}, {3, 1}, {2, 6, 15}, {3, 1}}));
    }

    /**
     * 解题思路：
     * 1、构建树及依赖关系
     * 2、按操作赋值
     * 3、保存取值结果
     *
     * 提交超时：testCase :https://leetcode-cn.com/submissions/detail/32797078/
     *
     * @param n
     * @param leadership
     * @param operations
     * @return
     */
    public int[] bonus(int n, int[][] leadership, int[][] operations) {
        List<Integer> retList = new ArrayList<>();
        //创建树
        CoinTree[] trees = new CoinTree[n + 1];
        for (int i = 1; i <= n; i++) {
            trees[i] = new CoinTree(i);
        }
        //构造依赖关系
        for (int i = 0; i < leadership.length; i++) {
            int[] item = leadership[i];
            trees[item[0]].child.add(trees[item[1]]);
        }
        //操作
        for (int i = 0; i < operations.length; i++) {
            int[] item = operations[i];
            //操作类型
            int operation = item[0];
            switch (operation){
                case 1://给某个人发币
                    addPersonCoin(trees[item[1]],item[2]);
                    break;
                case 2://给某个人和他的团队发币
                    addTeamCoin(trees[item[1]],item[2]);
                    break;
                case 3://计算某个人和他的团队币总和，并记录返回
                    retList.add(getTeamCoin(trees[item[1]]));
                    break;
            }
        }

        //list to array
        int[] retArr = new int[retList.size()];
        for (int i = 0; i < retList.size(); i++) {
            retArr[i] = retList.get(i);
        }

        return retArr;
    }

    private int getTeamCoin(CoinTree tree){
        int result = tree.coinCount;
        for (int i = 0; i < tree.child.size(); i++) {
            result+=getTeamCoin(tree.child.get(i));
        }
        return result;
    }

    private void addPersonCoin(CoinTree tree, int coin) {
        tree.coinCount+=coin;
    }

    private void addTeamCoin(CoinTree tree, int coin) {
        addPersonCoin(tree,coin);
        for (int i = 0; i < tree.child.size(); i++) {
            addTeamCoin(tree.child.get(i),coin);
        }
    }

    class CoinTree{
        int value;
        List<CoinTree> child;
        int coinCount = 0;

        public CoinTree(int value){
            this.value = value;
            child = new ArrayList<>();
        }
    }
}
