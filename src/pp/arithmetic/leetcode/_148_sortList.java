package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/9/1.
 * 148. 排序链表
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * @see <a href="https://leetcode-cn.com/problems/sort-list/description/">sort-list</a>
 */
public class _148_sortList {
    public static void main(String[] args) {
        Util.printListNode(sortList(Util.generateListNodeBySize(2)));
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = slow;
        while (slow != null && fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(slow);
        return merge(left, right);
    }

    private static ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode next = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                next.next = left;
                left = left.next;
            } else {
                next.next = right;
                right = right.next;
            }
            next = next.next;
        }
        if (left != null) {
            next.next = left;
        }
        if (right != null) {
            next.next = right;
        }
        return dummy.next;
    }
}
