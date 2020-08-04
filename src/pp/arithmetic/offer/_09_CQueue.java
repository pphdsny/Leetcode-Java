package pp.arithmetic.offer;

import java.util.Stack;

/**
 * Created by wangpeng on 2020-08-04.
 *
 * 剑指 Offer 09. 用两个栈实现队列
 *
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 *  
 *
 * 示例 1：
 *
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 *
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 *
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _09_CQueue {

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        System.out.println(cQueue.deleteHead());
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
    }

    /**
     * 解题思路：
     * 一个栈用于储存，另一个用于删除时候暂存数据
     *
     * 提交结果：
     * 执行用时：416 ms, 在所有 Java 提交中击败了5.06%的用户
     * 内存消耗：48.5 MB, 在所有 Java 提交中击败了32.71%的用户
     *
     * delete操作存在十分频繁的数据移动操作，待优化{@link CQueue2}
     */
    static class CQueue {

        Stack<Integer> add;
        Stack<Integer> stash;

        public CQueue() {
            add = new Stack();
            stash = new Stack();
        }

        public void appendTail(int value) {
            add.push(value);
        }

        public int deleteHead() {
            int retVal = -1;
            while (!add.isEmpty()){
                retVal = add.pop();
                stash.push(retVal);
            }
            //将删除的val剔除掉
            if (!stash.isEmpty()) {
                stash.pop();
            }
            while (!stash.isEmpty()){
                add.push(stash.pop());
            }

            return retVal;
        }
    }

    /**
     * 优化思路：
     * 1.stash用来delete操作，当stash为空的时候，才将add中的数据同步过去
     */
    static class CQueue2 {

        Stack<Integer> add;
        Stack<Integer> stash;

        public CQueue2() {
            add = new Stack();
            stash = new Stack();
        }

        public void appendTail(int value) {
            add.push(value);
        }

        public int deleteHead() {
            if (stash.isEmpty()){
                if (add.isEmpty()) return -1;
                while (!add.isEmpty()){
                    stash.push(add.pop());
                }
                return stash.pop();
            }else{
                return stash.pop();
            }
        }
    }
}
