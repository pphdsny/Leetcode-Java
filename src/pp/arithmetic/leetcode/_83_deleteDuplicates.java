package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2019-11-04.
 * 83. 删除排序链表中的重复元素
 *
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 *
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _83_deleteDuplicates {

    public static void main(String[] args) {
        _83_deleteDuplicates deleteDuplicates = new _83_deleteDuplicates();
        ListNode node = new ListNode(1);
        node.next = new ListNode(1);
        node.next.next = new ListNode(2);
        Util.printListNode(deleteDuplicates.deleteDuplicates(node));
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(1);
        node1.next.next = new ListNode(2);
        node1.next.next.next = new ListNode(3);
        node1.next.next.next.next = new ListNode(3);
        Util.printListNode(deleteDuplicates.deleteDuplicates(node1));
    }

    /**
     * 解题思路：典型的列表遍历
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode next = head;
        ListNode preEqual;
        while (next != null) {
            preEqual = next.next;
            while (preEqual!=null&&preEqual.val == next.val){
                preEqual = preEqual.next;
            }
            next.next = preEqual;
            next = preEqual;
        }

        return dummy.next;
    }
}
