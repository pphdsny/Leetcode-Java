package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;
import pp.arithmetic.model.TreeNode;

/**
 * Created by wangpeng on 2019-12-11.
 * 109. 有序链表转换二叉搜索树
 *
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _109_sortedListToBST {

    public static void main(String[] args) {
        _109_sortedListToBST sortedListToBST = new _109_sortedListToBST();
        ListNode listNode = new ListNode(-10);
        listNode.next = new ListNode(-3);
        listNode.next.next = new ListNode(0);
        listNode.next.next.next = new ListNode(5);
        listNode.next.next.next.next = new ListNode(9);
        TreeNode treeNode = sortedListToBST.sortedListToBST(listNode);
        Util.printTree(treeNode);
    }

    /**
     * 解题思路：
     * 去有序链表的中间位置作为根节点，将左部生成左子树，右部生成右子树
     * 难点：
     * 1、如何找到链表的中间位置 ==> 两次遍历，第一次使用length记录总长度，第二次取length/2的位置为更觉得
     * 2、如何将一个链表分割成左右两部分独立链表 ==> 变量rightPreNode保存遍历过程中的前置节点，为断链做准备
     *
     * 对于难点一：也可以用快慢指针定位中间位置
     *
     * 链表的解法多数是遍历，利用额外的节点保存中间状态
     * 树的解法多数是递归，同样的入参生成左右子树
     *
     * 执行用时 :2 ms, 在所有 java 提交中击败了48.48%的用户
     * 内存消耗 :38.7 MB , 在所有 java 提交中击败了97.64%的用户
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        ListNode next = head;
        int length = 0;
        while (next!=null){
            length++;
            next = next.next;
        }
        if (length==1){
            return new TreeNode(head.val);
        }
        ListNode leftNode = head;
        ListNode rightNode = head;
        ListNode rightPreNode = head;
        for (int i = 0; i < length / 2; i++) {
            rightPreNode = rightNode;
            rightNode = rightPreNode.next;
        }
        //生成根节点
        TreeNode root = new TreeNode(rightNode.val);
        //断开左侧
        rightPreNode.next = null;
        //断开右侧
        rightNode = rightNode.next;
        //生成左右子树
        root.left = sortedListToBST(leftNode);
        root.right = sortedListToBST(rightNode);
        return root;
    }
}
