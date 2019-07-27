package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by wangpeng on 2019-07-27.
 * 102. 二叉树的层次遍历
 * <p>
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 *  3
 * / \
 * 9  20
 *  /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _102_levelOrder {

    public static void main(String[] args) {
        _102_levelOrder levelOrder = new _102_levelOrder();
        List<List<Integer>> lists = levelOrder.levelOrder(Util.generateTreeNode());
        for (int i = 0; i < lists.size(); i++) {
            Util.printList(lists.get(i));
        }
        List<List<Integer>> lists2 = levelOrder.levelOrder2(Util.generateTreeNode());
        for (int i = 0; i < lists2.size(); i++) {
            Util.printList(lists2.get(i));
        }
    }

    /**
     * 解题思路：
     * 按层次遍历，类似于BFS，用一个队列保存遍历结果
     * 1.将（根）节点存入队列
     * 2.将队列中数据取空
     * 3.将取出的treeNode的左右子树存入队列并将结果存入结果集
     * 4.重复1-3直到队列无数据
     *
     * 优化：可以把2-3合并成一步，你会咋弄？
     * 优化方案{@link _102_levelOrder#levelOrder2(TreeNode)}
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new ArrayDeque<>();
        //1
        queue.add(root);
        //4
        while (!queue.isEmpty()) {
            List<TreeNode> list = new ArrayList<>();
            //2
            while (!queue.isEmpty()) {
                list.add(queue.poll());
            }
            //3
            if (list.size() > 0) {
                List<Integer> addList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    TreeNode treeNode = list.get(i);
                    addList.add(treeNode.val);
                    if (treeNode.left != null) queue.add(treeNode.left);
                    if (treeNode.right != null) queue.add(treeNode.right);
                }
                result.add(addList);
            }
        }
        return result;
    }

    /**
     * 优化：将2-3合并
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new ArrayDeque<>();
        //1
        queue.add(root);
        //4
        while (!queue.isEmpty()) {
            List<Integer> addList = new ArrayList<>();
            //2-3
            int depth = queue.size();
            for (int i = 0; i < depth; i++) {
                TreeNode treeNode = queue.poll();
                addList.add(treeNode.val);
                if (treeNode.left != null) queue.add(treeNode.left);
                if (treeNode.right != null) queue.add(treeNode.right);
            }
            if (addList.size() > 0) result.add(addList);
        }
        return result;
    }
}
