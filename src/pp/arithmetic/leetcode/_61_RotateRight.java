package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/8/22.
 * 61. 旋转链表
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * <p>
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 *
 * @see <a href="https://leetcode-cn.com/problems/rotate-list/description/">rotate-list</a>
 */
public class _61_RotateRight {

    public static void main(String[] args) {
        ListNode node = Util.generateListNodeBySize(1);
        Util.printListNode(node);
        ListNode rotateRight = rotateRight(node, 4);
        Util.printListNode(rotateRight);
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        //先形成一个环
        ListNode next = head;
        int length = 1;
        while (next != null && next.next != null) {
            next = next.next;
            length++;
        }
        next.next = head;
        next = head;
        int step = length - k % length - 1;
        while (step > 0) {
            next = next.next;
            step--;
        }
        ListNode kNode = next.next;
        next.next = null;
        return kNode;
    }
}
