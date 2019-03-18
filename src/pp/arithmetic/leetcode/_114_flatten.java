package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2018/9/14.
 * 114.二叉树展开为链表
 * 给定一个二叉树，原地将它展开为链表。
 * <p>
 * 例如，给定二叉树
 * <p>
 * 1
 * / \
 * 2   5
 * / \   \
 * 3   4   6
 * 将其展开为：
 * <p>
 * 1
 * \
 * 2
 * \
 * 3
 * \
 * 4
 * \
 * 5
 * \
 * 6
 *
 * @see <a href="https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/description/">flatten-binary-tree-to-linked-list</a>
 */
public class _114_flatten {
    public static void main(String[] args) {
//        TreeNode treeNode = Util.generateTreeNode();
//        Util.printTree(treeNode);
//        Util.printDivideLine();
//        flatten2(treeNode);
//        Util.printTree(treeNode);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            flatten(Util.generateTreeNode());
        }
        long end = System.currentTimeMillis();
        //55ms
        System.out.println("flatten(end-start):" + (end - start));
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            flatten2(Util.generateTreeNode());
        }
        end = System.currentTimeMillis();
        //35ms
        System.out.println("flatten2(end-start):" + (end - start));

    }

    private static void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        if (root.left != null) {
            TreeNode right = root.right;
            root.right = root.left;
            root.left = null;
            if (right != null) {
                TreeNode temp = root.right;
                while (temp.right != null) {
                    temp = temp.right;
                }
                temp.right = right;
            }
        }
    }

    private static void flatten2(TreeNode root) {
        dfs(root);
    }

    private static TreeNode dfs(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        TreeNode leftNode = dfs(root.left);
        TreeNode rightNode = dfs(root.right);
        if (leftNode != null) {
            leftNode.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        return rightNode == null ? leftNode : rightNode;
    }

}
