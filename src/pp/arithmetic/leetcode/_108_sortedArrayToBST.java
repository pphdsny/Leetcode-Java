package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2018/9/26.
 * 108.将有序数组转换为二叉搜索树
 * <p>
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * <p>
 * 示例:
 * <p>
 * 给定有序数组: [-10,-3,0,5,9],
 * <p>
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * <p>
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 *
 * @see <a href="https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/description/">convert-sorted-array-to-binary-search-tree</a>
 */
public class _108_sortedArrayToBST {

    public static void main(String[] args) {
        TreeNode treeNode = sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        Util.printTree(treeNode);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        TreeNode treeNode = generate(nums, 0, nums.length);
        return treeNode;
    }

    private static TreeNode generate(int[] nums,
                                     int start,
                                     int end) {
        //[-10,-3,0,5,9]
        if (start >= end) {
            return null;
        }
        int middle = (start + end) / 2;
        TreeNode treeNode = new TreeNode(nums[middle]);
        TreeNode leftNode = generate(nums, start, middle);
        TreeNode rightNode = generate(nums, middle + 1, end);
        treeNode.left = leftNode;
        treeNode.right = rightNode;
        return treeNode;
    }
}
