package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2018/9/26.
 * 450.删除二叉搜索树中的节点
 * <p>
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * <p>
 * 示例:
 * <p>
 * root = [5,3,6,2,4,null,7]
 * key = 3
 * <p>
 * 5
 * / \
 * 3   6
 * / \   \
 * 2   4   7
 * <p>
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * <p>
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * <p>
 * 5
 * / \
 * 4   6
 * /     \
 * 2       7
 * <p>
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 * <p>
 * 5
 * / \
 * 2   6
 * \   \
 * 4   7
 *
 * @see <a href="https://leetcode-cn.com/problems/delete-node-in-a-bst/description/">delete-node-in-a-bst</a>
 */
public class _450_deleteNode {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode left1 = new TreeNode(3);
        TreeNode right1 = new TreeNode(6);
        root.left = left1;
        root.right = right1;
        TreeNode left21 = new TreeNode(2);
        TreeNode right21 = new TreeNode(4);
        left1.left = left21;
        left1.right = right21;
        TreeNode right23 = new TreeNode(7);
        right1.right = right23;
        Util.printTree(root);
        TreeNode treeNode = deleteNode(root, 3);
        Util.printDivideLine();
        Util.printTree(treeNode);
    }

    static TreeNode parent;

    public static TreeNode deleteNode(TreeNode root, int key) {
        parent = null;
        TreeNode node = find(root, key);
        if (node == null) {
            return root;
        }
        if (node.left != null && node.right != null) {
            //左右子树都存在的
            TreeNode successor = findSuccessor(node);
            doDelete(parent, successor);
            node.val = successor.val;
            return root;
        }
        if (parent != null) {
            //要删除的不是根节点
            doDelete(parent, node);
            return root;
        }
        if (node.left != null) {
            root = node.left;
        } else {
            root = node.right;
        }
        return root;
    }

    private static void doDelete(TreeNode parent, TreeNode node) {
        if (node.val < parent.val) {
            if (node.left != null && node.right == null) {
                parent.left = node.left;
            } else if (node.left == null && node.right != null) {
                parent.left = node.right;
            } else {
                parent.left = null;
            }
        } else if (node.val > parent.val) {
            if (node.left != null && node.right == null) {
                parent.right = node.left;
            } else if (node.left == null && node.right != null) {
                parent.right = node.right;
            } else {
                parent.right = null;
            }
        }
    }

    private static TreeNode findSuccessor(TreeNode node) {
        parent = node;
        TreeNode ptr = node.right;
        while (ptr.left != null) {
            parent = ptr;
            ptr = ptr.left;
        }
        return ptr;
    }

    private static TreeNode find(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        TreeNode node = root;
        while (node != null) {
            if (node.val == key) {
                break;
            } else {
                parent = node;
                if (node.val > key) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }
        return node;
    }
}
