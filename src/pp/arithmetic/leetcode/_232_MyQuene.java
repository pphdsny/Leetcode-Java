package pp.arithmetic.leetcode;

import java.util.Stack;

/**
 * Created by wangpeng on 2018/8/24.
 * <p>
 * 232. 用栈实现队列
 * <p>
 * 使用栈实现队列的下列操作：
 * <p>
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 * 示例:
 * MyQueue queue = new MyQueue();
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 * 说明:
 * <p>
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 *
 * @see <a href="https://leetcode-cn.com/problems/implement-queue-using-stacks/description/">implement-queue-using-stacks</a>
 */
public class _232_MyQuene {
    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        int param_2 = obj.pop();
        System.out.println("param_2:"+param_2);
        int param_3 = obj.peek();
        System.out.println("param_3:"+param_3);
        boolean param_4 = obj.empty();
        System.out.println("param_4:"+param_4);
    }

    static class MyQueue {

        Stack<Integer> stack;

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            stack = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            Stack<Integer> tempStack = new Stack<>();
            while (!stack.isEmpty()) {
                tempStack.push(stack.pop());
            }
            tempStack.push(x);
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }

        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            return stack.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            return stack.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return stack.isEmpty();
        }
    }
}
