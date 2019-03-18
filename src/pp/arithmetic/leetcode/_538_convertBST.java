package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2018/9/27.
 * 538.把二叉搜索树转换为累加树
 * <p>
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * <p>
 * 例如：
 * <p>
 * 输入: 二叉搜索树:
 * 5
 * /   \
 * 2     13
 * <p>
 * 输出: 转换为累加树:
 * 18
 * /   \
 * 20     13
 *
 * @see <a href="https://leetcode-cn.com/problems/convert-bst-to-greater-tree/description/">convert-bst-to-greater-tree</a>
 */
public class _538_convertBST {
    public static void main(String[] args) {
        //[2,0,3,-4,1]
        TreeNode srcRoot = new TreeNode(2);
        TreeNode leftTree = new TreeNode(0);
        TreeNode rightTree = new TreeNode(3);
        srcRoot.left = leftTree;
        srcRoot.right = rightTree;
        leftTree.left = new TreeNode(-4);
        leftTree.right = new TreeNode(1);
        Util.printTree(srcRoot);
        convertBST(srcRoot);
        Util.printDivideLine();
        Util.printTree(srcRoot);
    }

    private static int totalSum;

    public static TreeNode convertBST(TreeNode root) {
        totalSum = 0;
        dfs(root);
        return root;
    }

    private static void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.right);
        totalSum += node.val;
        node.val = totalSum;
        dfs(node.left);
    }
}
