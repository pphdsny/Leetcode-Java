package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/8/15.
 */
public class _21_MergeTwoLists {
    public static void main(String[] args) {
        ListNode node1 = Util.generateListNodeBySort(5);
        Util.printListNode(node1);
        ListNode node2 = Util.generateListNodeBySort(5);
        Util.printListNode(node2);
//        ListNode newNode = mergeTwoListsSelf(node1, node2);
        ListNode newNode = mergeTwoListsOther(node1, node2);
        Util.printListNode(newNode);
    }

    public static ListNode mergeTwoListsSelf(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode preNode = dummyNode;
        //1、比较大小
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                preNode.next = l1;
                l1 = l1.next;
            } else {
                preNode.next = l2;
                l2 = l2.next;
            }
            preNode = preNode.next;
        }
        //2、将剩余拼接到后面
        if (l1 != null) {
            preNode.next = l1;
        }
        if (l2 != null) {
            preNode.next = l2;
        }
        return dummyNode.next;
    }

    public static ListNode mergeTwoListsOther(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoListsOther(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsOther(l2.next, l1);
            return l2;
        }
    }

}
