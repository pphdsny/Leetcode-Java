package pp.arithmetic.leetcode;

import javafx.util.Pair;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2019-09-03.
 * 543. 二叉树的直径
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _543_diameterOfBinaryTree {

    public static void main(String[] args) {
        _543_diameterOfBinaryTree diameterOfBinaryTree = new _543_diameterOfBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(diameterOfBinaryTree.diameterOfBinaryTree(root));

    }

    private int ret = 0;

    /**
     * 解题思路：
     * 对于树的问题，正常思路就是遍历，此题也不例外
     * 如题所示，找到最长的路径，有两种可能：
     *  一、根节点+左右子树节点
     *  二、根节点+最长的子树节点作为其父节点的子节点
     * 递归遍历过程中，用一个全局变量保存遍历过程中的最大值
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return ret;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        int count = left + right;
        ret = Math.max(ret, count);
        return Math.max(left, right) + 1;
    }
}
