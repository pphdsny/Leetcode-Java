package pp.arithmetic.leetcode;

import javafx.util.Pair;
import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

import java.util.*;

/**
 * Created by wangpeng on 2018/9/14.
 * 199. 二叉树的右视图
 * <p>
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 * <p>
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 *
 * @see <a href="https://leetcode-cn.com/problems/binary-tree-right-side-view/description/">binary-tree-right-side-viewF</a>
 */
public class _199_rightSideView {
    public static void main(String[] args) {
        TreeNode treeNode = Util.generateTreeNode();
        Util.printTree(treeNode);
//        List<Integer> list = rightSideView(treeNode);
        List<Integer> list = rightSideView2(treeNode);
        Util.printList(list);
    }

    /**
     * 很明显需要进行层次遍历，每一层最右边的就是能看到的项。
     * 我是如何知道每一层已经结束了呢？
     *
     * @param root
     * @return
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.add(new Pair<>(root, 0));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> poll = queue.poll();
            TreeNode node = poll.getKey();
            Integer depth = poll.getValue();
            //下一个即将出来的节点
            if (queue.isEmpty() || depth != queue.peek().getValue()) {
                result.add(node.val);
            }

            if (node.left != null) {
                queue.add(new Pair<>(node.left, depth + 1));
            }
            if (node.right != null) {
                queue.add(new Pair<>(node.right, depth + 1));
            }
        }
        return result;
    }

    /**
     * 写法更加清晰，效率也更高，没有多余的空间开销
     *
     * @param root
     * @return
     */
    public static List<Integer> rightSideView2(TreeNode root) {
        List<Integer> ret = new LinkedList<>();
        rightSideView(root, 0, ret);
        return ret;
    }

    private static void rightSideView(TreeNode node, int level, List<Integer> ret) {
        if (node == null)
            return;
        if (level == ret.size())
            ret.add(node.val);
        rightSideView(node.right, level + 1, ret);
        rightSideView(node.left, level + 1, ret);
    }
}
