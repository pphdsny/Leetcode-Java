package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2019-12-12.
 * 111. 二叉树的最小深度
 *
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _111_minDepth {

    public static void main(String[] args) {
        _111_minDepth minDepth = new _111_minDepth();
        System.out.println(minDepth.minDepth(Util.generateTreeNode(new Integer[]{3, 9, 20, null, null, 15, 7})));
    }

    /**
     * 解题思路：DFS求解
     * 1、求左子树的最小深度
     * 2、求右子树的最小深度
     * 3、求根节点的最小深度 = Math.min(left,right)+1
     *
     * 需要注意一点：如左/右子树为空，得取有值得叶节点长度
     *
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }
}
