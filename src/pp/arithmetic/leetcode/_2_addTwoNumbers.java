package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2019-03-20.
 * 2. 两数相加
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @see <a href="https://leetcode-cn.com/problems/add-two-numbers/">add-two-numbers</a>
 */
public class _2_addTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        _2_addTwoNumbers addTwoNumbers = new _2_addTwoNumbers();
        ListNode listNode = addTwoNumbers.addTwoNumbers(l1, l2);
        Util.printListNode(listNode);
    }

    /**
     * 执行用时 : 66 ms, 在Add Two Numbers的Java提交中击败了26.17% 的用户
     * 内存消耗 : 54.2 MB, 在Add Two Numbers的Java提交中击败了0.95% 的用户
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode dummyNext = dummyNode;
        ListNode l1Next = l1;
        ListNode l2Next = l2;
        int addNext = 0;
        while (l1Next != null || l2Next != null) {
            int v1 = l1Next != null ? l1Next.val : 0;
            int v2 = l2Next != null ? l2Next.val : 0;

            int add = v1 + v2 + addNext;
            ListNode addNode = new ListNode(add % 10);
            addNext = add / 10;
            dummyNext.next = addNode;
            dummyNext = addNode;
            //下一位
            l1Next = l1Next != null ? l1Next.next : null;
            l2Next = l2Next != null ? l2Next.next : null;
        }
        if (addNext != 0) {
            ListNode addNode = new ListNode(addNext);
            dummyNext.next = addNode;
        }

        return dummyNode.next;
    }
}
