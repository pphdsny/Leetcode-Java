package pp.arithmetic.leetcode;

import java.util.LinkedHashMap;

/**
 * Created by wangpeng on 2019-03-07.
 * 146. LRU缓存机制
 * <p>
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 示例:
 * <p>
 * LRUCache cache = new LRUCache( 2 );
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 *
 * @see <a href="https://leetcode-cn.com/problems/lru-cache/">lru-cache</a>
 */
public class _146_LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4
    }

    public static class LRUCache {

        private LinkedHashMap<Integer, Integer> map;
        private int max;
        private int cur;

        public LRUCache(int capacity) {
            max = capacity;
            this.map = new LinkedHashMap(0, 0.75f, true);
        }

        public int get(int key) {
            Integer integer = map.get(key);
            if (integer == null) {
                return -1;
            }
            return integer;
        }

        public void put(int key, int value) {
            Integer put = map.put(key, value);
            cur++;
            if (put != null) {
                cur--;
            }
            if (cur > max) {
                //删除最不常用的那个
                Integer elder = getElder();
                if (elder != null) {
                    map.remove(elder);
                    cur--;
                }
            }
        }

        private Integer getElder() {
            return map.entrySet().iterator().next().getKey();
        }
    }
}

