package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/7/9.
 * 递归实现
 */
public class _206_ReverseList_2 {

    public static void main(String[] args) {
        ListNode listNode1 = Util.generateListNodeBySize(10);
        Util.printListNode(listNode1);
        ListNode retList = reverseList(listNode1);
        Util.printListNode(retList);
    }

    /**
     * 递归实现
     *
     * @param listNode
     * @return
     */
    private static ListNode reverseList(ListNode listNode) {
        if (listNode.next == null) {
            return listNode;
        }
        ListNode dummy = new ListNode(0);
        generate(dummy, listNode);
        return dummy.next;
    }

    private static void generate(ListNode dummp, ListNode node) {
        if (node == null) {
            return;
        }
        ListNode temp = dummp.next;
        ListNode next = node.next;
        dummp.next = node;
        node.next = temp;
        generate(dummp, next);
    }

}
