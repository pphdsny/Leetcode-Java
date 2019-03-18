package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/7/10.
 */
public class _92_ReverseBetween {

    public static void main(String[] args) {
        ListNode node = Util.generateListNodeBySize(10);
        Util.printListNode(node);
//        ListNode newNode = reverseBetweenSelf(node, new Random().nextInt(10), new Random().nextInt(10));
        ListNode newNode = reverseBetweenSelf(node, 2, 7);
        Util.printListNode(newNode);
    }

    public static ListNode reverseBetweenSelf(ListNode head, int m, int n) {
        System.out.println("reverseBetweenSelf:m:" + m + ",n:" + n);
        if (n <= m) {
            return head;
        }
        ListNode preHead = null;
        ListNode mHead;
        ListNode nHead;
        ListNode lastHead = head;
        int between = n - m + 1;
        while (--m > 0) {
            preHead = lastHead;
            lastHead = lastHead.next;
        }
        mHead = lastHead;
        nHead = mHead;
        ListNode tempNode = new ListNode(0);
        while (between-- > 0 && lastHead != null) {
            lastHead = nHead.next;
            nHead.next = tempNode.next;
            tempNode.next = nHead;
            nHead = lastHead;
        }
        mHead.next = lastHead;
        if (preHead == null) {
            return tempNode.next;
        } else {
            preHead.next = tempNode.next;
        }
        return head;
    }

    public ListNode reverseBetweenOther(ListNode head, int m, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;
        ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
        for (int i = 0; i < m - 1; i++) pre = pre.next;

        ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
        ListNode then = start.next; // a pointer to a node that will be reversed

        // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
        // dummy-> 1 -> 2 -> 3 -> 4 -> 5

        for (int i = 0; i < n - m; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
        // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)

        return dummy.next;

    }
}
