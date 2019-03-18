package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/8/23.
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * @see <a href="https://leetcode-cn.com/problems/palindrome-linked-list/description/">palindrome-linked-list</a>
 */
public class _234_isPalindrome {

    public static void main(String[] args) {
        ListNode node = Util.generateListNodeBySize(3);
        Util.printListNode(node);
        boolean palindrome = isPalindrome(node);
        System.out.println("是否是回文链表：" + palindrome);
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode node = _206_ReverseList.reverseList(slow);
        fast = head;
        slow = node;
        while (fast != null && slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }
}
