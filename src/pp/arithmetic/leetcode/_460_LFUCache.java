package pp.arithmetic.leetcode;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by wangpeng on 2019-03-07.
 * 460. LFU缓存
 * <p>
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
 * <p>
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。
 * 在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
 * <p>
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 * <p>
 * 示例：
 * <p>
 * LFUCache cache = new LFUCache( 2 );
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回 1
 * cache.put(3,3);    // 去除 key 2
 * cache.get(2);       // 返回 -1 (未找到key 2)
 * cache.get(3);       // 返回 3
 * cache.put(4,4);    // 去除 key 1
 * cache.get(1);       // 返回 -1 (未找到 key 1)
 * cache.get(3);       // 返回 3
 * cache.get(4);       // 返回 4
 *
 * @see <a href="https://leetcode-cn.com/problems/lfu-cache/">lfu-cache</a>
 */
public class _460_LFUCache {
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回 1
        cache.put(3, 3);    // 去除 key 2
        System.out.println(cache.get(2));       // 返回 -1 (未找到key 2)
        System.out.println(cache.get(3));       // 返回 3
        cache.put(4, 4);    // 去除 key 1
        System.out.println(cache.get(1));       // 返回 -1 (未找到 key 1)
        System.out.println(cache.get(3));       // 返回 3
        System.out.println(cache.get(4));       // 返回 4
    }

    /**
     * 执行用时: 238 ms, 在LFU Cache的Java提交中击败了23.53% 的用户
     * 内存消耗: 71.7 MB, 在LFU Cache的Java提交中击败了0.00% 的用户
     * 使用HashMap + LinkedList的方式实现，时间耗时花在了LinkedList上
     */
    private static class LFUCache {

        private HashMap<Integer, Node> map = new HashMap<>();
        private HashMap<Integer, LinkedList<Integer>> freqMap = new HashMap<>();
        int max;
        int cur;
        int minFreq;

        public LFUCache(int capacity) {
            max = capacity;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node != null) {
                updateFreq(node);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (max <= 0) {
                return;
            }
            Node node = map.get(key);
            if (node != null) {
                //更新频率
                node.value = value;
                updateFreq(node);
            } else {
                cur++;
                if (cur > max) {
                    deleteNode();
                    cur--;
                }
                node = new Node(key, value, 1);
                map.put(key, node);
                addFreq(1, key);
                minFreq = 1;
            }
        }

        private void deleteNode() {
            LinkedList<Integer> keys = freqMap.get(minFreq);
            if (keys != null && keys.size() > 0) {
                Integer deleteKey = keys.get(0);
                keys.remove(deleteKey);
                map.remove(deleteKey);
            }
        }

        private void updateFreq(Node node) {
            removeFreq(node.freq, node.key);
            LinkedList<Integer> preKeyList = freqMap.get(node.freq);
            if (preKeyList != null && preKeyList.size() == 0 && minFreq == node.freq) {
                minFreq = node.freq + 1;
            }
            addFreq(++node.freq, node.key);
        }

        private void removeFreq(int freq, int key) {
            LinkedList<Integer> keyList = freqMap.get(freq);
            if (keyList != null) {
                keyList.remove((Integer) key);
            }
        }

        private void addFreq(int freq, int key) {
            LinkedList<Integer> keyList = freqMap.get(freq);
            if (keyList == null) {
                keyList = new LinkedList<>();
                freqMap.put(freq, keyList);
            }
            keyList.add(key);

        }
    }

    private static class Node {
        int key;
        int value;
        int freq;

        public Node(int key, int value, int freq) {
            this.key = key;
            this.value = value;
            this.freq = freq;
        }
    }
}
