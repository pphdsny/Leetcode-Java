package pp.arithmetic.leetcode;

import pp.arithmetic.model.RandomListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangpeng on 2018/8/20.
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 要求返回这个链表的深度拷贝。
 *
 * @see <a href="https://leetcode-cn.com/problems/copy-list-with-random-pointer/description/">copy-list-with-random-pointe</a>
 */
public class _138_CopyRandomList {

    public static void main(String[] args) {

    }

    /**
     * 空间复杂度：O(n)
     * 时间复杂度：O(n)
     * 通过两个map建立映射关系
     *
     * @param head
     * @return
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode dummyNode = new RandomListNode(0);
        RandomListNode lastCopyNode = dummyNode;
        Map<RandomListNode, Integer> srcMap = new HashMap<>();
        Map<Integer, RandomListNode> newMap = new HashMap<>();
        RandomListNode lastNode = head;
        int index = 0;
        //copy next节点
        while (lastNode != null) {
            //copy节点
            RandomListNode temp = new RandomListNode(lastNode.label);
            temp.random = lastNode.random;
            lastCopyNode.next = temp;
            lastCopyNode = temp;
            //设置对应关系
            srcMap.put(lastNode, index);
            newMap.put(index, lastCopyNode);
            //循环
            lastNode = lastNode.next;
            index++;
        }
        //copy random节点
        lastCopyNode = dummyNode.next;
        while (lastCopyNode != null) {
            RandomListNode tempRandom = lastCopyNode.random;
            lastCopyNode.random = newMap.get(srcMap.get(tempRandom));
            lastCopyNode = lastCopyNode.next;
        }

        return dummyNode.next;
    }

    /**
     * A->B->C
     * A->A`->B->B`->C->C`
     * 通过节点和节点的next建立映射关系
     *
     * @param head
     * @return
     */
    public RandomListNode copyRandomListOther(RandomListNode head) {
        RandomListNode iter = head, next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (iter != null) {
            next = iter.next;

            RandomListNode copy = new RandomListNode(iter.label);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        RandomListNode pseudoHead = new RandomListNode(0);
        RandomListNode copy, copyIter = pseudoHead;

        while (iter != null) {
            next = iter.next.next;

            // extract the copy
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            // restore the original list
            iter.next = next;

            iter = next;
        }

        return pseudoHead.next;
    }
}
