package pp.arithmetic.leetcode;

import pp.arithmetic.model.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wangpeng on 2019-03-21.
 * 653. 两数之和 IV - 输入 BST
 * <p>
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 * <p>
 * 案例 1:
 * <p>
 * 输入:
 * 5
 * / \
 * 3   6
 * / \   \
 * 2   4   7
 * <p>
 * Target = 9
 * <p>
 * 输出: True
 * <p>
 * <p>
 * 案例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 3   6
 * / \   \
 * 2   4   7
 * <p>
 * Target = 28
 * <p>
 * 输出: False
 *
 * @see <a href="https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/">two-sum-iv-input-is-a-bst</a>
 */
public class _653_findTarget {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(6);
        root.right.right = new TreeNode(7);
        _653_findTarget findTarget = new _653_findTarget();
        System.out.println(findTarget.findTarget(root, 9));
        System.out.println(findTarget.findTarget(root, 28));
    }

    private Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

}
