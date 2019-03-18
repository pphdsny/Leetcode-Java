package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2018/9/14.
 * 113. 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 *          5
 *         / \
 *        4   8
 *       /   / \
 *      11  13  4
 *     /  \    / \
 *    7   2   5   1
 * 返回:
 * <p>
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 *
 * @see <a href="https://leetcode-cn.com/problems/path-sum-ii/description/">path-sum-ii</a>
 */
public class _113_pathSum {
    public static void main(String[] args) {
        TreeNode treeNode = Util.generateTreeNode();
        List<List<Integer>> lists = pathSum(treeNode, 22);
        for (int i = 0; i < lists.size(); i++) {
            Util.printList(lists.get(i));
        }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, sum, path, 0, result);
        return result;
    }

    private static void dfs(TreeNode root,
                            int sum,
                            List<Integer> path,
                            int pathSum,
                            List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        pathSum += root.val;
        if (pathSum == sum && root.left == null && root.right == null) {
            result.add(new ArrayList<>(path));
        }
        dfs(root.left, sum, path, pathSum, result);
        dfs(root.right, sum, path, pathSum, result);
        path.remove(path.size() - 1);
        pathSum -= root.val;
    }
}
