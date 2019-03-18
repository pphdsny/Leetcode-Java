package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/8/22.
 * 删除链表的倒数第N个节点
 * <p>
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 *
 * @see <a href="https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/description/">remove-nth-node-from-end-of-list</a>
 */
public class _19_RemoveNthFromEnd {
    public static void main(String[] args) {
        ListNode node = Util.generateListNodeBySize(5);
        Util.printListNode(node);
        ListNode node1 = removeNthFromEndOther(node, 5);
        Util.printListNode(node1);
    }

    /**
     * 自己的常规解法，求出length，再减去n
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        dummy.next = head;
        int length = length(head);
        int x = length - n;
        while (x >= 0 && pre != null && pre.next != null) {
            if (x == 0) {
                ListNode next = pre.next;
                pre.next = next.next;
                next.next = null;
                break;
            }
            pre = pre.next;
            x--;
        }
        return dummy.next;
    }

    public static int length(ListNode node) {
        int length = 0;
        ListNode temp = node;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 他人的解法，快慢指针
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEndOther(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        ListNode fast = dummy;
        ListNode slow = dummy;
        dummy.next = head;
        while (fast.next != null) {
            if (n <= 0) {
                slow = slow.next;
            }
            fast = fast.next;
            n--;
        }
        if (slow.next != null)
            slow.next = slow.next.next;
        return dummy.next;
    }
}
