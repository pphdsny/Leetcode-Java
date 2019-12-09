package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

import java.util.Arrays;

/**
 * Created by wangpeng on 2019-12-09.
 * 106. 从中序与后序遍历序列构造二叉树
 *
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _106_buildTree {

    public static void main(String[] args) {
        _106_buildTree buildTree = new _106_buildTree();
        TreeNode treeNode = buildTree.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        Util.printTree(treeNode);
    }

    /**
     * 解题思路：
     * 中序：左->中->右，后序：左->右->中
     * 1、取后序的最后一位就是根节点
     * 2、遍历中序，找到根节点在中序中的位置I，I左边的就是左子树，右边就是右子树
     * 3、分别取中序和后续的0-I位置，得到的就是左子树的中序和后续遍历接通，重复步骤1、2将左子树构造出来
     * 4、同理步骤3，将右子树构造出来
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) return null;
        int rootVal = postorder[postorder.length - 1];
        TreeNode rootNode = new TreeNode(rootVal);
        int rootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootVal){
                rootIndex = i;
                break;
            }
        }
        rootNode.left = buildTree(Arrays.copyOfRange(inorder, 0, rootIndex), Arrays.copyOfRange(postorder, 0, rootIndex));
        rootNode.right = buildTree(Arrays.copyOfRange(inorder, rootIndex + 1, inorder.length), Arrays.copyOfRange(postorder, rootIndex, postorder.length - 1));
        return rootNode;
    }

}
