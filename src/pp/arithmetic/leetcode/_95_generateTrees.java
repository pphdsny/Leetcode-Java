package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2019-04-24.
 * 95. 不同的二叉搜索树 II
 * <p>
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * 解释:
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 *
 * @see <a href="https://leetcode-cn.com/problems/unique-binary-search-trees-ii/">unique-binary-search-trees-ii</a>
 */
public class _95_generateTrees {
    public static void main(String[] args) {
        _95_generateTrees trees = new _95_generateTrees();
        List<TreeNode> treeNodes = trees.generateTrees(3);
        for (int i = 0; i < treeNodes.size(); i++) {
            Util.printTree(treeNodes.get(i));
        }
    }

    /**
     * 二叉搜索树（二叉查找树）：根节点，比左子树大，比右子树小
     * 解题思路：
     * 1、确定根节点
     * 2、确定左子树列表（list）
     * 3、确定右子树列表（list）
     * 4、循环左右子树，拿到树的list
     *
     * 对于树的题目，最根本的解题方案就是递归左右子树
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return generateNodeList(1, n);
    }

    private List<TreeNode> generateNodeList(int si, int ei) {
        List<TreeNode> res = new ArrayList<>();
        if (si > ei) {
            res.add(null);
            return res;
        }
        if (si == ei) {
            res.add(new TreeNode(si));
            return res;
        }
        for (int i = si; i <= ei; i++) {
            List<TreeNode> leftSubTrees = generateNodeList(si, i - 1);
            List<TreeNode> rightSubTrees = generateNodeList(i + 1, ei);
            for (TreeNode left : leftSubTrees) {
                for (TreeNode right : rightSubTrees) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    res.add(node);
                }
            }
        }
        return res;
    }
}
