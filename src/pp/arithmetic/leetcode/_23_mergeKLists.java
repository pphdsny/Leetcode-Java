package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/9/13.
 * 23.合并K个排序链表
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * @see <a href="https://leetcode-cn.com/problems/merge-k-sorted-lists/description/">merge-k-sorted-lists</a>
 */
public class _23_mergeKLists {
    public static void main(String[] args) {
        int maxSize = 1000;
        int nodeLength = 100;
        ListNode[] listNodes = new ListNode[nodeLength];
        ListNode[] listNodes2 = new ListNode[nodeLength];
        for (int i = 0; i < nodeLength; i++) {
            listNodes[i] = Util.generateOrderListNode(maxSize);
            listNodes2[i] = Util.generateOrderListNode(maxSize);
        }
        long start = System.currentTimeMillis();
        ListNode node = mergeKLists(listNodes);
        long end = System.currentTimeMillis();
        System.out.println("mergeKLists(end-start):" + (end - start));
//        Util.printListNode(node);
        Util.printDivideLine();
        start = System.currentTimeMillis();
        ListNode node2 = mergeKLists2(listNodes2);
        end = System.currentTimeMillis();
        System.out.println("mergeKLists2(end-start):" + (end - start));
//        Util.printListNode(node2);
    }

    /**
     * 2,2组合更优
     * 时间复杂度:
     * 2n*m/2+2^2n*m/2^2+....2^k*n*m/2^k
     * m/2^k会无限趋近于1，所以k=logm
     * Nm+Nm+...+Nm
     * 时间复杂度O(n*mlogm)
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length < 2) {
            return lists[0];
        }
        int n = lists.length;
        while (n > 1) {
            //0,1,2,3
            //n=4,k=2
            //n=2,k=1
            int k = (n + 1) / 2;
            for (int i = 0; i < n / 2; i++) {
                lists[i] = mergetTwo(lists[i], lists[i + k]);
            }
            n = k;
        }
        return lists[0];
    }

    /**
     * 时间复杂度：
     * n+2*n+3*n+...+m*n=n*(1+2+...+m)=n*(m*(m-1)/2)=O(n*m^2)
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length < 2) {
            return lists[0];
        }
        ListNode dummy = new ListNode(0);
        //效率低下168ms，可以优化成2个一起组合
        for (int i = 0; i < lists.length; i++) {
            dummy.next = mergetTwo(dummy.next, lists[i]);
        }
        return dummy.next;
    }

    /**
     * 时间复杂度O(n)
     *
     * @param node1
     * @param node2
     * @return
     */
    private static ListNode mergetTwo(ListNode node1, ListNode node2) {
        ListNode dummy = new ListNode(0);
        ListNode next = dummy;
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                next.next = node1;
                next = node1;
                node1 = node1.next;
            } else {
                next.next = node2;
                next = node2;
                node2 = node2.next;
            }
        }
        if (node1 != null) {
            next.next = node1;
        }
        if (node2 != null) {
            next.next = node2;
        }
        return dummy.next;
    }
}
