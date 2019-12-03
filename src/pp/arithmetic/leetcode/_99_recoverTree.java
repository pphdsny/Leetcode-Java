package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2019-12-01.
 * 99. 恢复二叉搜索树
 *
 * 二叉搜索树中的两个节点被错误地交换。
 *
 * 请在不改变其结构的情况下，恢复这棵树。
 *
 * 示例 1:
 *
 * 输入: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * 输出: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * 示例 2:
 *
 * 输入: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * 输出: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * 进阶:
 *
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _99_recoverTree {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(3);
        treeNode.left.right = new TreeNode(2);
        _99_recoverTree recoverTree = new _99_recoverTree();
        recoverTree.recoverTree(treeNode);
        Util.printTree(treeNode);
    }

    /**
     * 解题思路：
     * 分：几种情况
     * 1、根节点和左子树的某个数字交换 -> 由于根节点大于左子树中的所有数，所以交换后我们只要找左子树中最大的那个数，就是所交换的那个数
     * 2、根节点和右子树的某个数字交换 -> 由于根节点小于右子树中的所有数，所以交换后我们只要在右子树中最小的那个数，就是所交换的那个数
     * 3、左子树和右子树的两个数字交换 -> 找左子树中最大的数，右子树中最小的数，即对应两个交换的数
     * 4、左子树中的两个数字交换
     * 5、右子树中的两个数字交换
     * @param root
     */
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        //寻找左子树中最大的节点
        TreeNode maxLeft = getMaxOfBST(root.left);
        //寻找右子树中最小的节点
        TreeNode minRight = getMinOfBST(root.right);

        if (minRight != null && maxLeft != null) {
            //左边的大于根节点，右边的小于根节点，对应情况 3，左右子树中的两个数字交换
            if ( maxLeft.val > root.val && minRight.val < root.val) {
                int temp = minRight.val;
                minRight.val = maxLeft.val;
                maxLeft.val = temp;
            }
        }

        if (maxLeft != null) {
            //左边最大的大于根节点，对应情况 1，根节点和左子树的某个数做了交换
            if (maxLeft.val > root.val) {
                int temp = maxLeft.val;
                maxLeft.val = root.val;
                root.val = temp;
            }
        }

        if (minRight != null) {
            //右边最小的小于根节点，对应情况 2，根节点和右子树的某个数做了交换
            if (minRight.val < root.val) {
                int temp = minRight.val;
                minRight.val = root.val;
                root.val = temp;
            }
        }
        //对应情况 4，左子树中的两个数进行了交换
        recoverTree(root.left);
        //对应情况 5，右子树中的两个数进行了交换
        recoverTree(root.right);

    }
    //寻找树中最小的节点
    private TreeNode getMinOfBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode minLeft = getMinOfBST(root.left);
        TreeNode minRight = getMinOfBST(root.right);
        TreeNode min = root;
        if (minLeft != null && min.val > minLeft.val) {
            min = minLeft;
        }
        if (minRight != null && min.val > minRight.val) {
            min = minRight;
        }
        return min;
    }

    //寻找树中最大的节点
    private TreeNode getMaxOfBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode maxLeft = getMaxOfBST(root.left);
        TreeNode maxRight = getMaxOfBST(root.right);
        TreeNode max = root;
        if (maxLeft != null && max.val < maxLeft.val) {
            max = maxLeft;
        }
        if (maxRight != null && max.val < maxRight.val) {
            max = maxRight;
        }
        return max;
    }

}
