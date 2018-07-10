package pp.arithmetic;

import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/7/9.
 */
public class Util {
    public static void printListNode(ListNode listNode){
        if (listNode == null){
            System.out.println("null");
            return ;
        }
        ListNode head = listNode;
        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}
