package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/8/21.
 * <p>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * 说明:
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * @see <a href="https://leetcode-cn.com/problems/swap-nodes-in-pairs/description/">swap-nodes-in-pairs</a>
 */
public class _24_SwapPairs {
    public static void main(String[] args) {
        ListNode node = Util.generateListNodeBySize(10);
        Util.printListNode(node);
        ListNode swapPairs = swapPairs(node);
        Util.printListNode(swapPairs);
    }

    public static ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        while (head != null && head.next != null) {
            //获取第一第二个的节点
            ListNode first = head;
            ListNode second = head.next;
            //交换位置
            first.next = second.next;
            second.next = first;
            //循环
            head = first.next;
            pre.next = second;
            pre = first;
        }

        return dummy.next == null ? head : dummy.next;
    }
}
