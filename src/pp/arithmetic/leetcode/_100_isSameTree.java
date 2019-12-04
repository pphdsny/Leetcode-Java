package pp.arithmetic.leetcode;

import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2019-12-04.
 * 100. 相同的树
 *
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 示例 1:
 *
 * 输入:       1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 *
 * 输出: true
 * 示例 2:
 *
 * 输入:      1          1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 *
 * 输出: false
 * 示例 3:
 *
 * 输入:       1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 *
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/same-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _100_isSameTree {

    public static void main(String[] args) {
        _100_isSameTree isSameTree = new _100_isSameTree();
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
//        q.right = new TreeNode(3);

        System.out.println(isSameTree.isSameTree(p,q));
    }

    /**
     * 解题思路：
     * 典型的树的深度遍历（DFS）
     * 1、判断左子树是否相同
     * 2、判断右子树是否相同
     * 3、判断父节点是否相同
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p== null && q == null) return true;
        if (p == null || q == null) return false;
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right) && p.val == q.val;
    }
}
