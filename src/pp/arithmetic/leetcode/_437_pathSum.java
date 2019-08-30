package pp.arithmetic.leetcode;

import pp.arithmetic.model.TreeNode;

import java.util.*;

/**
 * Created by wangpeng on 2019-08-29.
 * 437. 路径总和 III
 * <p>
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * <p>
 * 找出路径和等于给定数值的路径总数。
 * <p>
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 * <p>
 * 示例：
 * <p>
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * <p>
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 * <p>
 * 返回 3。和等于 8 的路径有:
 * <p>
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _437_pathSum {
    public static void main(String[] args) {
        _437_pathSum pathSum = new _437_pathSum();
        //[10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.left = new TreeNode(1);
        System.out.println(pathSum.pathSum(root, 8));
        //[-2,null,-3],sum=-5
        TreeNode r1 = new TreeNode(-2);
        r1.right = new TreeNode(-3);
        System.out.println(pathSum.pathSum(r1, -5));
        //[1,-2,-3,1,3,-2,null,-1],sum=3
        TreeNode r2 = new TreeNode(1);
        r2.left = new TreeNode(-2);
        r2.right = new TreeNode(-3);
        r2.left.left = new TreeNode(1);
        r2.left.right = new TreeNode(3);
        r2.left.left.left = new TreeNode(-1);
        r2.right.left = new TreeNode(-2);
        System.out.println(pathSum.pathSum(r2, 3));
        //[0,1,1] sum=1
        TreeNode r3 = new TreeNode(0);
        r3.left = new TreeNode(1);
        r3.right = new TreeNode(1);
        System.out.println(pathSum.pathSum(r3, 1));
    }

    int result = 0;

    /**
     * 解题思路：
     * 1.中序遍历，将遍历结果保存在list中
     * 2.从list的末尾开始累加，如和==sum，则结果+1
     *
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        result = 0;
        List<TreeNode> list = new LinkedList<>();
        dfs(root, list, sum);
        return result;
    }

    private void dfs(TreeNode root, List<TreeNode> list, int sum) {
        if (root == null) return;
        int tempSum = 0;
        list.add(root);
        for (int i = list.size() - 1; i >= 0; i--) {
            tempSum += list.get(i).val;
            if (tempSum == sum) {
                result++;
            }
        }
        dfs(root.left, list, sum);
        dfs(root.right, list, sum);
        list.remove(root);
    }

}
