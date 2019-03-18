package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/8/21.
 * 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 *
 * @see <a href="https://leetcode-cn.com/problems/odd-even-linked-list/description/">odd-even-linked-list</a>
 */
public class _328_OddEvenList {

    public static void main(String[] args) {
        ListNode node = Util.generateListNodeBySize(5);
        Util.printListNode(node);
        ListNode oddEvenList = oddEvenList(node);
        Util.printListNode(oddEvenList);
    }

    public static ListNode oddEvenList(ListNode head) {
        ListNode oddDummy = new ListNode(0);
        ListNode evenDummy = new ListNode(0);
        ListNode oddNext = oddDummy;
        ListNode evenNext = evenDummy;
        int index = 1;
        while (head != null) {
            if (index % 2 == 0) {
                evenNext.next = head;
                evenNext = head;
            } else {
                oddNext.next = head;
                oddNext = head;
            }
            index++;
            head = head.next;
        }
        evenNext.next = null;
        oddNext.next = evenDummy.next;

        return oddDummy.next;
    }

    public static ListNode oddEvenListOther(ListNode head) {
        if (head != null) {

            ListNode odd = head, even = head.next, evenHead = even;

            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
        }
        return head;
    }
}
