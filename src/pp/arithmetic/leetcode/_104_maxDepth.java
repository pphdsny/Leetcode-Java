package pp.arithmetic.leetcode;

import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2018/9/27.
 * 104.二叉树的最大深度
 * <p>
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 *
 * @see <a href="https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/description/">maximum-depth-of-binary-tree</a>
 */
public class _104_maxDepth {
    public static void main(String[] args) {
        TreeNode srcRoot = new TreeNode(3);
        TreeNode leftTree = new TreeNode(9);
        TreeNode rightTree = new TreeNode(20);
        srcRoot.left = leftTree;
        srcRoot.right = rightTree;
        leftTree.left = new TreeNode(15);
        leftTree.left.left = new TreeNode(10);
        int maxDepth = maxDepth(srcRoot);
        System.out.println(maxDepth);
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

}
