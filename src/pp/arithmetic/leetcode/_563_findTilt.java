package pp.arithmetic.leetcode;

import javafx.util.Pair;
import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2018/10/31.
 * 563. 二叉树的坡度
 * <p>
 * 给定一个二叉树，计算整个树的坡度。
 * <p>
 * 一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。
 * <p>
 * 整个树的坡度就是其所有节点的坡度之和。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * 1
 * /   \
 * 2     3
 * 输出: 1
 * 解释:
 * 结点的坡度 2 : 0
 * 结点的坡度 3 : 0
 * 结点的坡度 1 : |2-3| = 1
 * 树的坡度 : 0 + 0 + 1 = 1
 * 注意:
 * <p>
 * 任何子树的结点的和不会超过32位整数的范围。
 * 坡度的值不会超过32位整数的范围
 *
 * @see <a href="https://leetcode-cn.com/problems/binary-tree-tilt/description/">binary-tree-tilt</a>
 */
public class _563_findTilt {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        treeNode1.left = new TreeNode(4);
        treeNode2.right = new TreeNode(5);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        Util.printTree(treeNode);
        int tilt = findTilt(treeNode);
        System.out.println(tilt);
    }

    public static int findTilt(TreeNode root) {
        Pair<Integer, Integer> generate = generate(root);
        return generate.getKey();
    }

    private static Pair<Integer, Integer> generate(TreeNode root) {
        if (root == null) {
            return new Pair<>(0, 0);
        }
        if (root.left == null && root.right == null) {
            return new Pair<>(0, root.val);
        }
        Pair<Integer, Integer> left = generate(root.left);
        Pair<Integer, Integer> right = generate(root.right);
        int tilt = left.getKey() + right.getKey() + Math.abs(left.getValue() - right.getValue());
        return new Pair<>(tilt, root.val + left.getValue() + right.getValue());
    }

}
