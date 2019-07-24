package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by wangpeng on 2019-07-24.
 * 94. 二叉树的中序遍历
 * <p>
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _94_inorderTraversal {

    public static void main(String[] args) {
        TreeNode treeNode = Util.generateTreeNode();
        Util.printTree(treeNode);
        _94_inorderTraversal inorderTraversal = new _94_inorderTraversal();
        //递归算法
        Util.printList(inorderTraversal.inorderTraversal(treeNode));
        Util.printList(inorderTraversal.inorderTraversal2(treeNode));

    }

    /**
     * 解题思路：
     * 按题目所说，递归算法{@link _94_inorderTraversal#inorderTraversal2(TreeNode)}很简单，我们试着迭代算法完成
     * 模拟递归，用一个栈保存遍历结果
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            //左
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            //中
            result.add(curr.val);
            //右
            curr = curr.right;
        }
        return result;
    }

    /**
     * 递归算法
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        recursion(root, result);
        return result;
    }

    private void recursion(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        //左
        recursion(root.left, result);
        //中
        result.add(root.val);
        //右
        recursion(root.right, result);
    }
}
