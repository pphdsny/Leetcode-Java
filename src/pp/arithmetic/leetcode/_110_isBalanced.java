package pp.arithmetic.leetcode;

import javafx.util.Pair;
import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2019-12-12.
 * 110. 平衡二叉树
 *
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _110_isBalanced {

    public static void main(String[] args) {
        _110_isBalanced isBalanced = new _110_isBalanced();
        TreeNode treeNode = Util.generateTreeNode(new Integer[]{3, 9, 20, null, null, 15, 7});
        System.out.println(isBalanced.isBalanced(treeNode));
        TreeNode treeNode2 = Util.generateTreeNode(new Integer[]{1,2,2,3,3,null,null,4,4});
        System.out.println(isBalanced.isBalanced(treeNode2));
    }

    /**
     * 解题思路：
     * 1、递归计算左右子树的高度
     * 2、取左右子树的最大高度+1，即是该根节点的高度
     * 3、由于每个节点都需要满足高度平衡二叉树的条件，所以递归返回一个Pair<Boolean,Integer>
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return dfs(root).getKey();
    }

    private Pair<Boolean, Integer> dfs(TreeNode root) {
        if (root == null) return new Pair<>(true, 0);
        Pair<Boolean, Integer> left = dfs(root.left);
        Pair<Boolean, Integer> right = dfs(root.right);
        return new Pair<>(left.getKey() && right.getKey() && (Math.abs(left.getValue() - right.getValue()) <= 1), Math.max(left.getValue(), right.getValue()) + 1);
    }
}
