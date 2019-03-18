package pp.arithmetic.leetcode;

import java.util.HashMap;

/**
 * Created by wangpeng on 2019-03-11.
 * 432. 全 O(1) 的数据结构
 * <p>
 * 实现一个数据结构支持以下操作：
 * <p>
 * Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
 * Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否者使一个存在的 key 值减一。如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
 * GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串""。
 * GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
 * 挑战：以 O(1) 的时间复杂度实现所有操作。
 *
 * @see <a href="https://leetcode-cn.com/problems/all-oone-data-structure/">leetcode-oone-data-structure</a>
 */
public class _432_AllOne {

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("a");
        allOne.inc("a");
        allOne.inc("a");
        allOne.inc("a");
        allOne.inc("a");
        allOne.inc("a");
        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("c");
        allOne.dec("c");
        System.out.println(allOne.getMinKey());
        //["AllOne","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","dec","getMinKey"]
        //[[],["a"],["a"],["a"],["a"],["a"],["a"],["a"],["a"],["a"],["a"],["a"],["a"],["a"],["a"],["a"],["a"],["b"],["b"],["c"],["c"],[]]
        //[null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,""]
        //[null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,"b"]
    }

    /**
     * 整体实现Map+双指针，由于只需要知道最大和最小值，所以只关心head和tail即可，不需要太在意中间的是否是有序的
     * 最开始的解法，定义了两个双指针，一个是key另一个是count的，并且要保证count有序，做的是否复杂还出错了
     * 中间有大量的非空判断，总结了一下：对于指针的问题都可以添加一个头指针或者尾指针来避免频繁的空判断
     */
    private static class AllOne {
        class Node {
            int val;
            String key;
            AllOne.Node next;
            AllOne.Node prev;

            public Node(String key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        /**
         * Initialize your data structure here.
         */
        HashMap<String, AllOne.Node> map;
        AllOne.Node head;
        AllOne.Node tail;

        public AllOne() {
            map = new HashMap<>();
            head = new AllOne.Node("", 0);
            tail = new AllOne.Node("", 0);
            head.next = tail;
            head.prev = tail;
            tail.prev = head;
            tail.next = head;
        }

        private void removeNode(AllOne.Node n) {
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }

        private void addNodeFirst(AllOne.Node n) {
            n.next = head.next;
            n.prev = head;
            head.next.prev = n;
            head.next = n;
        }

        private void addNodeLast(AllOne.Node n) {
            n.next = tail;
            n.prev = tail.prev;
            tail.prev.next = n;
            tail.prev = n;
        }

        /**
         * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
         */
        public void inc(String key) {
            if (map.containsKey(key)) {
                AllOne.Node tmp = map.get(key);
                tmp.val++;
                if (tmp.val >= head.next.val) {
                    removeNode(tmp);
                    addNodeFirst(tmp);
                } else if (tmp.val > tmp.prev.val) {
                    AllOne.Node preTmp = tmp.prev;

                    preTmp.prev.next = tmp;
                    tmp.prev = preTmp.prev;
                    preTmp.prev = tmp;
                    preTmp.next = tmp.next;
                    tmp.next.prev = preTmp;
                    tmp.next = preTmp;
                }
            } else {
                AllOne.Node tmp = new AllOne.Node(key, 1);
                map.put(key, tmp);
                addNodeLast(tmp);
            }
        }

        /**
         * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
         */
        public void dec(String key) {
            if (!map.containsKey(key)) return;
            AllOne.Node tmp = map.get(key);
            if (tmp.val == 1) {
                removeNode(tmp);
                map.remove(key);
            } else {
                tmp.val--;
                if (tmp.val <= tail.prev.val) {
                    removeNode(tmp);
                    addNodeLast(tmp);
                } else if (tmp.val < tmp.next.val) {
                    AllOne.Node nextTmp = tmp.next;
                    tmp.prev.next = nextTmp;
                    nextTmp.prev = tmp.prev;
                    tmp.next = nextTmp.next;
                    tmp.prev = nextTmp;
                }
            }
        }

        /**
         * Returns one of the keys with maximal value.
         */
        public String getMaxKey() {
            if (map.size() == 0) return "";
            return head.next.key;
        }

        /**
         * Returns one of the keys with Minimal value.
         */
        public String getMinKey() {
            if (map.size() == 0) return "";
            return tail.prev.key;
        }
    }

}
