package pp.arithmetic.leetcode;

import pp.arithmetic.model.ListNode;
import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2018/7/9.
 */
public class _206_ReverseList {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
//        ListNode retList = reverseList(listNode1);
//        Util.printListNode(retList);
        ListNode retList2 = reverseList2(listNode1);
        Util.printListNode(retList2);
    }

    /**
     * 就地逆置法
     * @param listNode
     * @return
     */
    public static ListNode reverseList(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return listNode;
        }
        ListNode head = listNode;
        ListNode newListNode = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newListNode;
            newListNode = head;
            head = next;
        }
        return newListNode;
    }

    /**
     * 头插法
     * @param listNode
     * @return
     */
    public static ListNode reverseList2(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return listNode;
        }
        ListNode head = listNode;
        ListNode tempNode = new ListNode(0);
        while (head != null) {
            ListNode next = head.next;
            head.next = tempNode.next;
            tempNode.next = head;
            head = next;
        }
        return tempNode.next;
    }
}
