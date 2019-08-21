package pp.arithmetic.leetcode;

import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2019-08-21.
 * 337. 打家劫舍 III
 * <p>
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _337_rob {

    public static void main(String[] args) {
        _337_rob rob = new _337_rob();
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(4);
        treeNode.right = new TreeNode(5);
        treeNode.left.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(3);
        treeNode.right.right = new TreeNode(1);
        System.out.println(rob.rob(treeNode));
        //[4,1,null,2,null,3]
        TreeNode r1 = new TreeNode(4);
        r1.left = new TreeNode(1);
        r1.left.left = new TreeNode(2);
        r1.left.left.left = new TreeNode(3);
        System.out.println(rob.rob(r1));
    }

    /**
     * 按题意，不能相连=根节点和左右子树选择只能二选一，每个节点都有选和不选，总共组合个数2^n次，全部循环明显不合适
     * 按经验，树的解题思路就是DFS递归
     * 1.求解左右子树的最大金额（左右子树需要求解2遍，选择了根节点和没有选择根节点的）
     * 2.选择根节点的最大值max1=max(左)+max(右)+跟，没有选择根节点的最大值max2=max(左)+max(右)
     * 3.比较max1和max2，求出最大值
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        int[] max = doRob(root);
        return Math.max(max[0], max[1]);
    }

    /**
     * 0代表不含根节点，1代表含根节点
     *
     * @param root
     * @return
     */
    private int[] doRob(TreeNode root) {
        int[] res = new int[2];
        if (root == null)
            return res;
        int[] left = doRob(root.left);
        int[] right = doRob(root.right);
        //不包含根节点，最大值为两个子树的最大值之和
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        //包含根节点，最大值为两个子树不包含根节点的最大值加上根节点的值
        res[1] = left[0] + right[0] + root.val;
        return res;
    }
}
