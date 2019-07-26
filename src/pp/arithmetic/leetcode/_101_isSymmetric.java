package pp.arithmetic.leetcode;

import pp.arithmetic.model.TreeNode;

import java.util.Stack;

/**
 * Created by wangpeng on 2019-07-26.
 * 101. 对称二叉树
 * <p>
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * 说明:
 * <p>
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _101_isSymmetric {

    public static void main(String[] args) {
        _101_isSymmetric isSymmetric = new _101_isSymmetric();
        //UT1
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(2);
        treeNode.left.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(4);
        treeNode.right.right = new TreeNode(3);
        System.out.println(isSymmetric.isSymmetric(treeNode));
        System.out.println(isSymmetric.isSymmetric2(treeNode));
        //UT2
        TreeNode treeNode1 = new TreeNode(1);
        treeNode1.left = new TreeNode(2);
        treeNode1.right = new TreeNode(2);
        treeNode1.left.right = new TreeNode(3);
        treeNode1.right.right = new TreeNode(3);
        System.out.println(isSymmetric.isSymmetric(treeNode1));
        System.out.println(isSymmetric.isSymmetric2(treeNode1));
    }

    /**
     * 解题思路：
     * 树的两种方法：递归和迭代
     * <p>
     * 递归：
     * 1.左子树是镜像对称
     * 2.右子树是镜像对称
     * 3.自己是镜像对称
     * 选择递归入参：左子树，右子树，条件：左子树value==右子树value，
     * 需要注意因为是镜像，所以递归的时候左右子树取相对的left和right
     * <p>
     * 迭代解法：{@link _101_isSymmetric#isSymmetric2(TreeNode)}
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isEqual(root.left, root.right);
    }

    private boolean isEqual(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        //左
        boolean leftEqual = isEqual(left.left, right.right);
        //右
        boolean rightEqual = isEqual(left.right, right.left);
        //自己
        boolean selfEqual = left.val == right.val;
        return leftEqual && rightEqual && selfEqual;
    }

    /**
     * 迭代：
     * 通过栈保存遍历结果
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();
        TreeNode left = root.left;
        TreeNode right = root.right;

        while (true) {
            //左
            while (left != null && right != null) {
                leftStack.add(left);
                rightStack.add(right);
                left = left.left;
                right = right.right;
            }
            if (left != null || right != null) {
                return false;
            }
            if (leftStack.isEmpty() && rightStack.isEmpty()) {
                break;
            }
            //自己
            TreeNode leftPop = leftStack.pop();
            TreeNode rightPop = rightStack.pop();
            if (leftPop.val != rightPop.val) {
                return false;
            }
            //右
            left = leftPop.right;
            right = rightPop.left;
        }

        return true;
    }
}
