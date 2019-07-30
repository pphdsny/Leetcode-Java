package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2019-07-30.
 * 124. 二叉树中的最大路径和
 * <p>
 * 给定一个非空二叉树，返回其最大路径和。
 * <p>
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * <p>
 * 1
 * / \
 * 2   3
 * <p>
 * 输出: 6
 * 示例 2:
 * <p>
 * <p>
 * 输入: [-10,9,20,null,null,15,7]
 * <p>
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * <p>
 * 输出: 42
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _124_maxPathSum {

    public static void main(String[] args) {
        _124_maxPathSum maxPathSum = new _124_maxPathSum();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(maxPathSum.maxPathSum(root));
        TreeNode root1 = new TreeNode(-10);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println(maxPathSum.maxPathSum(root1));
        System.out.println(maxPathSum.maxPathSum(new TreeNode(-3)));
        System.out.println(maxPathSum.maxPathSum(Util.generateTreeNode()));
        //[2,-1]
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(-1);
        System.out.println(maxPathSum.maxPathSum(root2));
    }

    private int maxPath = Integer.MIN_VALUE;

    /**
     * 解题思路：
     * 树的解题离不开递归遍历
     * 1.寻找左子树的最大路径和（负数抛弃）
     * 2.寻找右子树的最大路径和（负数抛弃）
     * 3.如根节点是整数（含0），合并左右子树中的正数（负数不要），比较已存在得最大值
     * 4.返回结果是根节点、根节点+左子树、根节点+右子树的最大值
     * <p>
     * 注意：
     * 1.因为求的是连续路径，如果子树的最大值中根节点未参数计算，不应该加入子树父节点的计算
     * 2.可能存在左、右子树都是可以用的，但是路径不能走回头路，只能返回左右子树和根节点的最大值
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        maxPath = Integer.MIN_VALUE;
        dfs(root);
        return maxPath;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        //1
        int leftMax = dfs(root.left);
        //2
        int rightMax = dfs(root.right);
        //3
        int max = root.val;
        if (leftMax > 0) max += leftMax;
        if (rightMax > 0) max += rightMax;
        maxPath = Math.max(maxPath, max);
        //4
        return Math.max(root.val, Math.max(root.val + leftMax, root.val + rightMax));
    }
}
