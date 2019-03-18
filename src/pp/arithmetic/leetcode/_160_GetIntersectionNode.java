package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

import java.util.Random;

/**
 * Created by wangpeng on 2018/8/16.
 * <p>
 * 求两个链表的向交元素
 * <p>
 * 注意：
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class _160_GetIntersectionNode {
    public static void main(String[] args) {
        ListNode nodeA = Util.generateListNodeBySize(2);
        Util.printListNode(nodeA);
        ListNode nodeB = Util.generateListNodeBySize(4);
        Util.printListNode(nodeB);
        //生成一些公共节点
        int size = new Random().nextInt(10);
        for (int i = 0; i < size; i++) {
            ListNode node = new ListNode(new Random().nextInt(10));
            ListNode lastNodeA = Util.getLastNode(nodeA);
            ListNode lastNodeB = Util.getLastNode(nodeB);
            lastNodeA.next = node;
            lastNodeB.next = node;
        }
        System.out.println("新生成的链表：");
        Util.printListNode(nodeA);
        Util.printListNode(nodeB);
//        ListNode intersectionNode = getIntersectionNode(nodeA, nodeB);
        ListNode intersectionNode = getIntersectionNodeOther(nodeA, nodeB);
        System.out.println("相交链表：");
        Util.printListNode(intersectionNode);
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lengthA = length(headA);
        int lengthB = length(headB);
        ListNode tempA = headA;
        ListNode tempB = headB;
        if (lengthA > lengthB) {
            int diffLength = lengthA - lengthB;
            while (diffLength-- > 0) {
                tempA = tempA.next;
            }
        }
        if (lengthB > lengthA) {
            int diffLength = lengthB - lengthA;
            while (diffLength-- > 0) {
                tempB = tempB.next;
            }
        }
        while (tempA != null && tempB != null) {
            if (tempA == tempB) {
                return tempA;
            }
            tempA = tempA.next;
            tempB = tempB.next;
        }

        return null;
    }

    public static int length(ListNode node){
        int length = 0;
        ListNode temp = node;
        while (temp != null){
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 他人的解法，A,B的交叉循环2次，如有交叉，一定是在交叉点
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNodeOther(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;
        }

        return a;
    }
}
