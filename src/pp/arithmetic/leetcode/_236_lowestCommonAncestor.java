package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2018/9/14.
 * 236.二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 *           _______3______
 *          /              \
 *       ___5__          ___1__
 *      /      \        /      \
 *      6      _2_      0       8
 *            /   \
 *           7    4
 * 示例 1:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * 说明:
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 *
 * @see <a href="https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/description/">lowest-common-ancestor-of-a-binary-tree</a>
 */
public class _236_lowestCommonAncestor {
    public static void main(String[] args) {
        TreeNode treeNode = Util.generateTreeNode();
        Util.printTree(treeNode);
        TreeNode treeNode1 = lowestCommonAncestor(treeNode, treeNode.left.left, treeNode.left);
        System.out.println(treeNode1.val);
        //更优
        TreeNode treeNode2 = lowestCommonAncestor2(treeNode, treeNode.left.left, treeNode.left);
        System.out.println(treeNode2.val);
    }

    private static boolean isFinish = false;

    /**
     * 时间复杂度：O(n),不过这3*O(n)比方案2要复杂
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> qList = new ArrayList<>();
        List<TreeNode> pList = new ArrayList<>();
        isFinish = false;
        dfs(root, p, pList);
        isFinish = false;
        dfs(root, q, qList);
        TreeNode result = null;
        for (int i = 0; i < qList.size() && i < pList.size(); i++) {
            if (pList.get(i) != qList.get(i)) {
                break;
            }
            result = pList.get(i);
        }
        return result;
    }

    private static void dfs(TreeNode root,
                            TreeNode target,
                            List<TreeNode> path) {
        if (root == null || isFinish) {
            return;
        }
        if (root == target) {
            isFinish = true;
        }
        path.add(root);
        dfs(root.left, target, path);
        dfs(root.right, target, path);
        if (!isFinish) {
            path.remove(path.size() - 1);
        }
    }

    /**
     * 采用的是后序遍历。
     * 如果需要找的两个节点分别在我的左，右，那么我就是最近的父节点
     * 如果只在我的left，那么left就是最近父节点，反之则是在right
     * 时间复杂度：O(n)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode leftNode = lowestCommonAncestor2(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor2(root.right, p, q);
        if (leftNode != null && rightNode != null) {
            return root;
        }
        return leftNode == null ? rightNode : leftNode;
    }
}
