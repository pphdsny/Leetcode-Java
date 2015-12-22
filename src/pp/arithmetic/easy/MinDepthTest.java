package pp.arithmetic.easy;

import pp.arithmetic.model.TreeNode;

/**
 * 
 * @author pphdsny
 * 
 *         Given a binary tree, find its minimum depth.
 * 
 *         The minimum depth is the number of nodes along the shortest path from
 *         the root node down to the nearest leaf node.
 * 
 *         {@link https://leetcode.com/problems/minimum-depth-of-binary-tree/}
 */
public class MinDepthTest {

	public static void main(String[] args) {
	}

	public static int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = minDepth(root.left);
		int right = minDepth(root.right);
		return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
	}

}
