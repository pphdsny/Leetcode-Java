package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2019-12-12.
 * 112. 路径总和
 *
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _112_hasPathSum {

    public static void main(String[] args) {
        _112_hasPathSum hasPathSum = new _112_hasPathSum();
        TreeNode treeNode = Util.generateTreeNode(new Integer[]{5, 4, 7, 11, null, 13, 4, 7, 2, null, null, null, 1});
        System.out.println(hasPathSum.hasPathSum(treeNode,22));
        System.out.println(hasPathSum.hasPathSum(Util.generateTreeNode(new Integer[]{1,2}),1));
    }

    /**
     * 解题思路：
     * 1、sum减去当前根节点的val，将新的sum传递给左右子树
     * 2、左右子树重复步骤1
     * 3、如最终的节点==null并且sum==0则找到目标路径
     *
     * 注意：叶节点的定义
     *
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        return dfs(root, sum);
    }

    private boolean dfs(TreeNode root, int sum) {
        if (root == null) return sum == 0;
        int newSum = sum - root.val;
        if (root.left == null && root.right == null) return newSum == 0;
        boolean left = dfs(root.left, newSum);
        boolean right = dfs(root.right, newSum);
        if (root.left != null && root.right != null) return left || right;
        if (root.left != null) return left;
        if (root.right != null) return right;
        return false;
    }
}
