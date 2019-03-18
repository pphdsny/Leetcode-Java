package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/8/16.
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 进阶：
 * 你能否不使用额外空间解决此题？
 */
public class _141_HasCycle {

    public static void main(String[] args) {
        ListNode srcNode = Util.generateListNodeBySort(5);
        ListNode lastNode = Util.getLastNode(srcNode);
        lastNode.next = srcNode.next.next;
        boolean b = hasCycle(srcNode);
        System.out.println("是否带环：" + b);
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode head1 = head;
        ListNode head2 = head;
        while (head2.next != null && head2.next.next != null) {
            head1 = head1.next;
            head2 = head2.next.next;
            if (head1 == head2) {
                return true;
            }
        }
        return false;
    }
}
