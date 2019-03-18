package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

import java.util.Random;

/**
 * Created by wangpeng on 2018/8/17.
 * <p>
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 */
public class _86_Partition {
    public static void main(String[] args) {
        ListNode node = Util.generateListNodeBySize(10);
        Util.printListNode(node);
        int x = new Random().nextInt(5);
        System.out.println("需要分割的x:" + x);
        ListNode partition = partition(node, x);
        Util.printListNode(partition);
    }

    public static ListNode partition(ListNode head, int x) {
        //头段
        ListNode preDummyNode = new ListNode(0);
        ListNode preNode = preDummyNode;
        //尾端
        ListNode lastDummyNode = new ListNode(0);
        ListNode lastNode = lastDummyNode;
        while (head != null) {
            if (head.val >= x) {
                lastNode.next = head;
                lastNode = head;
            } else {
                preNode.next = head;
                preNode = head;
            }
            head = head.next;
        }
        lastNode.next = null;
        preNode.next = lastDummyNode.next;

        return preDummyNode.next;
    }
}
