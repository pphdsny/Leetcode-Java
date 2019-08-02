package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2019-08-02.
 * 226. 翻转二叉树
 *
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 * 备注:
 * 这个问题是受到 Max Howell 的 原问题 启发的 ：
 *
 * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _226_invertTree {

    public static void main(String[] args) {
        _226_invertTree invertTree = new _226_invertTree();
        TreeNode treeNode = Util.generateTreeNode();
        Util.printTree(treeNode);
        TreeNode treeNode1 = invertTree.invertTree(treeNode);
        Util.printTree(treeNode1);
    }

    /**
     * 解题思路：
     * 树的经典解题：左、右、自己，递归遍历，拿到翻转后的左右子树，将root的左右子树坐下替换
     * 1.翻转左子树
     * 2.翻转右子树
     * 3.替换root的左右子树（翻转后）
     *
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root ==null) return null;
        //1
        TreeNode left = invertTree(root.left);
        //2
        TreeNode right = invertTree(root.right);
        //3
        root.left = right;
        root.right = left;
        return root;
    }
}
