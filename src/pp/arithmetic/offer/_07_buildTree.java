package pp.arithmetic.offer;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2020-08-04.
 *
 * 剑指 Offer 07. 重建二叉树
 *
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 *  
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *  
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _07_buildTree {

    public static void main(String[] args) {
        _07_buildTree buildTree = new _07_buildTree();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9,3,15,20,7};
        TreeNode treeNode = buildTree.buildTree(preorder, inorder);
        Util.printTree(treeNode);
    }

    /**
     * 解题思路：
     * 1、知道前序遍历，首位就是根节点
     * 2、由于不存在重复数字，根据根节点找到中序遍历的位置I，I前就是左子树的中序，I后就是右子树的中序
     * 3、在前序数组中，根据左右子树中序的长度，能找到左右子树对应的前序遍历数组
     * 4、循环1-3，得到左右子树的对应的前序&中序数组，最终得到构建的树
     *
     * 优化建议：得到左右子树对应的数组的时候，有两种方案:
     * 一、拷贝新数组
     * 二、原数组上理由index指针获取结果（性能和效率都更高）
     *
     * 本题解基于方案二
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        return dfs(preorder.length,preorder,0,inorder,0);
    }

    // 从前序和中序构造二叉树，前序和中序是大数组中的一段[start, start + count)
    private TreeNode dfs(int count, int[] preOrder, int preStart, int[] inOrder, int inStart) {
        if (count <= 0) return null;

        int rootValue = preOrder[preStart];
        TreeNode root = new TreeNode(rootValue);

        // 从inorder中找到root值，(inorder)左边就是左子树，(inorder)右边就是右子树
        // 然后在preorder中，数出与inorder中相同的个数即可
        int pos = inStart + count - 1;
        for (; pos >= inStart; --pos) {
            if (inOrder[pos] == rootValue) {
                break;
            }
        }
        int leftCount = pos - inStart;
        int rightCount = inStart + count - pos - 1;

        if (leftCount > 0) {
            int leftInStart = inStart;
            int leftPreStart = preStart + 1;
            root.left = dfs(leftCount, preOrder, leftPreStart, inOrder, leftInStart);
        }

        if (rightCount > 0) {
            int rightInStart = pos + 1;
            int rightPreStart = preStart + 1 + leftCount;
            root.right = dfs(rightCount, preOrder, rightPreStart, inOrder, rightInStart);
        }

        return root;
    }

}
