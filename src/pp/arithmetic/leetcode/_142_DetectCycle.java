package pp.arithmetic.leetcode;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

/**
 * Created by wangpeng on 2018/8/20.
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 说明：不允许修改给定的链表。
 * <p>
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 *
 * @see <a href="https://leetcode-cn.com/problems/linked-list-cycle-ii/description/">linked-list-cycle-ii</a>
 */
public class _142_DetectCycle {
    public static void main(String[] args) {
        ListNode srcNode = Util.generateListNodeBySort(1);
        Util.printListNode(srcNode);
        ListNode lastNode = Util.getLastNode(srcNode);
        lastNode.next = srcNode;
        ListNode node = detectCycle(srcNode);
        System.out.println("环节点：" + (node == null ? "null" : node.val));
    }

    /**
     * head1:慢指针，一次一步
     * head2:快指针，一次二步
     * 相遇时候，快指针走的距离一定是慢指针的2倍
     * 一些变量说明：
     * a:开始节点距离环节点的距离
     * x:慢指针环节点到相遇点的距离
     * s:慢指针走的总距离=a+x
     * r:环的大小
     * L:链表的长度
     * n:快指针比慢指针多跑的圈
     * 求解：
     * a+x=s
     * nr+a+x=2s
     * a+x=nr=(n-1)r+r=(n-1)r+(L-a)
     * a=(n-1)r+(L-a-x)
     * L-a-x是相遇节点距离环节点的距离
     * 可以作证的是快慢节点相遇时慢节点一定没有跑一圈，快节点一定多跑了一圈，也就是n=1（未证）
     * 所以a=(L-a-x)=相遇节点到环节点的距离
     * <p>
     * 20181225更新
     * s:慢指针走的距离
     * r:环的长度
     * s+nr:快指针走的距离
     * x:起点距离环起始节点长度
     * y:环起始节点位置到相遇点长度
     * 等式一：s+nr=2s
     * 等式二：s=nr,s=x+y
     * 等式三：x+y=nr=>x=nr-y=(n-1)r+r-y约等于r-y（n-1圈的循环可以不计较）
     * r-y是相遇点到环起点的位置，也就是说 "起点距离环起始节点长度"="相遇点到环起点的长度"
     *
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode head1 = head;
        ListNode head2 = head;
        boolean hasCycle = false;
        while (head2.next != null && head2.next.next != null) {
            head1 = head1.next;
            head2 = head2.next.next;
            if (head1 == head2) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) return null;
        head2 = head;
        while (head1 != null && head2 != null) {
            if (head1 == head2) {
                break;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1;
    }
}
