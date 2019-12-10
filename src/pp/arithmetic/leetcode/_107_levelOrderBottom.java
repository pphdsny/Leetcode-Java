package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by wangpeng on 2019-12-10.
 * 107. 二叉树的层次遍历 II
 *
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _107_levelOrderBottom {

    public static void main(String[] args) {
        _107_levelOrderBottom levelOrderBottom = new _107_levelOrderBottom();
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        List<List<Integer>> lists = levelOrderBottom.levelOrderBottom(node);
        for (int i = 0; i < lists.size(); i++) {
            Util.printList(lists.get(i));
        }
    }

    /**
     * 解题思路：
     * 树的问题就是遍历，本题用树的广度遍历（BFS）
     * BFS遍历依赖队列保存一层的节点
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> retList = new ArrayList<>();
        if (root == null) return retList;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (queue.peek() != null) {
            List<Integer> items = new ArrayList<>();
            List<TreeNode> treeList = new ArrayList<>();
            TreeNode poll = queue.poll();
            while (poll != null) {
                items.add(poll.val);
                if (poll.left != null) treeList.add(poll.left);
                if (poll.right != null) treeList.add(poll.right);
                poll = queue.poll();
            }
            if (!treeList.isEmpty()) {
                queue.addAll(treeList);
            }
            if (!items.isEmpty()) {
                retList.add(0, items);
            }
        }
        return retList;
    }
}
