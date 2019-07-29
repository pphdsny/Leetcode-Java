package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

import java.util.Arrays;

/**
 * Created by wangpeng on 2019-07-27.
 * 105. 从前序与中序遍历序列构造二叉树
 * <p>
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _105_buildTree {

    public static void main(String[] args) {
        _105_buildTree buildTree = new _105_buildTree();
        Util.printTree(buildTree.buildTree1(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
        //优化方案
        Util.printTree(buildTree.buildTree(new int[]{1, 2, 3}, new int[]{3, 2, 1}));
        Util.printTree(buildTree.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
    }

    /**
     * 解题思路：
     * 1.确定根节点，前序的首位就是根节点
     * 2.确定根节点的左子树和右子树，找到中序根节点之前的就是左子树，之后的就是右子树
     * 3.将左子树和右子树的前序和中序传递到buildTree中，递归1-2步骤
     * <p>
     * 执行耗时
     * 执行用时 :48 ms, 在所有 Java 提交中击败了9.05%的用户
     * 内存消耗 :77.6 MB, 在所有 Java 提交中击败了5.07%的用户
     * <p>
     * 优化：提交时间耗时有点长，感觉问题出在数组拷贝下，将数组拷贝改为传递下标试试
     * {@link _105_buildTree#buildTree(int[], int[])}
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder.length == 0) return null;
        //1
        TreeNode root = new TreeNode(preorder[0]);
        if (preorder.length == 1) return root;
        //2
        int leftIndex;
        for (leftIndex = 0; leftIndex < inorder.length; leftIndex++) {
            if (inorder[leftIndex] == preorder[0]) {
                break;
            }
        }
        //3
        TreeNode leftNode = buildTree1(Arrays.copyOfRange(preorder, 1, leftIndex + 1), Arrays.copyOfRange(inorder, 0, leftIndex));
        TreeNode rightNode = buildTree1(Arrays.copyOfRange(preorder, leftIndex + 1, preorder.length), Arrays.copyOfRange(inorder, leftIndex + 1, inorder.length));
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }

    /**
     * 优化方案：避免数组的拷贝
     *
     * 执行用时 :3 ms, 在所有 Java 提交中击败了99.12%的用户
     * 内存消耗 :37.5 MB, 在所有 Java 提交中击败了70.21%的用户
     *
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder.length, preorder, 0, inorder, 0);
    }

    // 从前序和中序构造二叉树，前序和中序是大数组中的一段[start, start + count)
    private TreeNode buildTree(int count, int[] preOrder, int preStart, int[] inOrder, int inStart) {
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
            root.left = buildTree(leftCount, preOrder, leftPreStart, inOrder, leftInStart);
        }

        if (rightCount > 0) {
            int rightInStart = pos + 1;
            int rightPreStart = preStart + 1 + leftCount;
            root.right = buildTree(rightCount, preOrder, rightPreStart, inOrder, rightInStart);
        }

        return root;
    }
}
