package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/10/27.
 * 203. 移除链表元素
 * <p>
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 * @see <a href="https://leetcode-cn.com/problems/remove-linked-list-elements/description/">remove-linked-list-elements</a>
 */
public class _203_removeElements {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(1);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        Util.printListNode(listNode1);
        ListNode node1 = removeElements(listNode1, 1);
        Util.printListNode(node1);
    }

    public static ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode(0);
        ListNode node = dummyNode;
        dummyNode.next = head;
        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return dummyNode.next;
    }
}
