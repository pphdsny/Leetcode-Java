package pp.arithmetic.offer;

import pp.arithmetic.Util;
import pp.arithmetic.model.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2020-07-30.
 * 剑指 Offer 06. 从尾到头打印链表
 *
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *  
 *
 * 限制：
 *
 * 0 <= 链表长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _06_reversePrint {

    public static void main(String[] args) {
        ListNode listNode = Util.generateListNodeBySize(10);
        Util.printListNode(listNode);
        _06_reversePrint reversePrint = new _06_reversePrint();
        int[] arr = reversePrint.reversePrint(listNode);
        Util.printArray(arr);
    }

    /**
     * 解题思路：
     * 链表的问题，最直接也只能是遍历+递归，可以借助多指针一起，此题只需要遍历就可以了
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        dfs(head,list);
        int[] retArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            retArr[i] = list.get(i);
        }
        return retArr;
    }

    private void dfs(ListNode node,List<Integer> list){
        if (node == null){
            return;
        }
        dfs(node.next,list);
        list.add(node.val);
    }
}
