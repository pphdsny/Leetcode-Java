package pp.arithmetic.leetcode;

import javafx.util.Pair;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2019-07-24.
 * 98. 验证二叉搜索树
 * <p>
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _98_isValidBST {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(2);
        treeNode.left = new TreeNode(1);
        treeNode.right = new TreeNode(3);
        _98_isValidBST isValidBST = new _98_isValidBST();
        System.out.println(isValidBST.isValidBST(treeNode));
        TreeNode treeNode1 = new TreeNode(5);
        treeNode1.left = new TreeNode(1);
        treeNode1.right = new TreeNode(4);
        treeNode1.right.left = new TreeNode(3);
        treeNode1.right.right = new TreeNode(6);
        System.out.println(isValidBST.isValidBST(treeNode1));
        //[10,5,15,null,null,6,20]
        TreeNode treeNode2 = new TreeNode(10);
        treeNode2.left = new TreeNode(5);
        treeNode2.left.right = new TreeNode(15);
//        treeNode2.left.right = new TreeNode(6);
//        treeNode2.right = new TreeNode(20);
        System.out.println(isValidBST.isValidBST(treeNode2));
    }

    /**
     * 解题思路：
     * 树的解题思路：递归
     * 1.左子树满足条件
     * 2.右子树满足条件
     * 3.自己满足条件(大于左子树max，跟小于又子树min)
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return valid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean valid(TreeNode root, int min, int max) {
        if (root == null) return true;
        if (root.val <= min || root.val >= max) return false;
        return valid(root.left, min, root.val) && valid(root.right, root.val, max);
    }

}
