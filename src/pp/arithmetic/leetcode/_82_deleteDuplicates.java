package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2019-10-31.
 * 82. 删除排序链表中的重复元素 II
 *
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _82_deleteDuplicates {

    public static void main(String[] args) {
        _82_deleteDuplicates deleteDuplicates = new _82_deleteDuplicates();
        //1->2->3->3->4->4->5
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(3);
        head1.next.next.next.next = new ListNode(4);
        head1.next.next.next.next.next = new ListNode(4);
        head1.next.next.next.next.next.next = new ListNode(5);
        Util.printListNode(deleteDuplicates.deleteDuplicates(head1));
        //1->1->1->2->3
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(1);
        head2.next.next = new ListNode(1);
        head2.next.next.next = new ListNode(2);
        head2.next.next.next.next = new ListNode(3);
        Util.printListNode(deleteDuplicates.deleteDuplicates(head2));
    }

    /**
     * 解题思路：
     * 对于链表类型的题目，就是按照next的指针进行遍历，找到题目要求
     * 1、定义四个指针:
     *  一个头结点的前置虚拟指针==>方便返回结果的头结点,
     *  一个前置指针==>方便切断遍历相同节点，
     *  一个遍历指针，
     *  一个搜寻相同指针的尾结点==>定位结尾
     * 2、找到满足条件的，将前置指针的next指向尾结点的next
     *
     * 执行用时 :1 ms, 在所有 java 提交中击败了99.26%的用户
     * 内存消耗 :37 MB, 在所有 java 提交中击败了57.65%的用户
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        //头结点的前置虚拟指针
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //前置指针
        ListNode preNode = head;
        //遍历指针
        ListNode node = head;
        //尾结点指针
        ListNode endNode ;
        while (node != null ) {
            endNode = node.next;
            while (endNode != null && endNode.val == node.val) {
                endNode = endNode.next;
            }
            if (node.next == endNode){
                //不是重复的
                preNode = node;
            }else{
                //存在重复的
                preNode.next = endNode;
                if (dummy.next == node){
                    dummy.next = endNode;
                }
            }
            node = endNode;
        }

        return dummy.next;
    }
}
