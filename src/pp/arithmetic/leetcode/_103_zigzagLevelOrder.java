package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

import java.util.*;

/**
 * Created by wangpeng on 2018-12-26.
 * 103. 二叉树的锯齿形层次遍历
 * <p>
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层次遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 *
 * @see <a href="https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/">binary-tree-zigzag-level-order-traversal</a>
 */
public class _103_zigzagLevelOrder {

    public static void main(String[] args) {
        TreeNode treeNode = Util.generateTreeNode();
//        TreeNode treeNode = new TreeNode(3);
//        TreeNode treeNode1 = new TreeNode(9);
//        TreeNode treeNode2 = new TreeNode(20);
//        treeNode.left = treeNode1;
//        treeNode.right = treeNode2;
//        treeNode2.left = new TreeNode(15);
//        treeNode2.right = new TreeNode(7);


        Util.printTree(treeNode);
        List<List<Integer>> lists = zigzagLevelOrder(treeNode);
        for (int i = 0; i < lists.size(); i++) {
            Util.printList(lists.get(i));
        }
    }

    /**
     * 双stack，一个从左到右，一个从右到左添加，正好满足需求
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> retList = new ArrayList<>();
        if (root == null) return retList;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            List<Integer> itemList1 = new ArrayList<>();
            while (!stack1.isEmpty()) {
                TreeNode top = stack1.pop();
                itemList1.add(top.val);
                if (top.left != null) stack2.push(top.left);
                if (top.right != null) stack2.push(top.right);
            }
            if (!itemList1.isEmpty()) retList.add(itemList1);
            List<Integer> itemList2 = new ArrayList<>();
            while (!stack2.isEmpty()) {
                TreeNode top = stack2.pop();
                itemList2.add(top.val);
                if (top.right != null) stack1.push(top.right);
                if (top.left != null) stack1.push(top.left);
            }
            if (!itemList2.isEmpty()) retList.add(itemList2);
        }
        return retList;
    }
}
