package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/10/30.
 * 25. k个一组翻转链表
 * <p>
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 * <p>
 * 示例 :
 * <p>
 * 给定这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * @see <a href="https://leetcode-cn.com/problems/reverse-nodes-in-k-group/description/">reverse-nodes-in-k-group</a>
 */
public class _25_reverseKGroup {

    public static void main(String[] args) {
        ListNode node = Util.generateListNodeBySize(1);
        Util.printListNode(node);
        ListNode node1 = reverseKGroup(node, 2);
        Util.printListNode(node1);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        ListNode node = head;
        ListNode preNode = dummy;
        ListNode nextPreNode;
        int count;
        while (true) {
            if (checkFinish(node, k)) {
                if (dummy.next == null){
                    dummy.next = head;
                }
                break;
            }
            //倒置
            count = k;
            nextPreNode = null;
            while (count > 0) {
                if (nextPreNode == null) {
                    nextPreNode = node;
                }
                ListNode next = node.next;
                ListNode temp = preNode.next;
                preNode.next = node;
                node.next = temp;
                node = next;
                count--;
            }
            preNode = nextPreNode;
            nextPreNode.next = node;
        }
        return dummy.next;
    }

    private static boolean checkFinish(ListNode node, int k) {
        boolean isFinish = false;
        ListNode next = node;
        //check是否到底
        while (k > 0) {
            if (next == null) {
                isFinish = true;
                break;
            }
            next = next.next;
            k--;
        }
        return isFinish;
    }

}
