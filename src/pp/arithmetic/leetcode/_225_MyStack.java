package pp.arithmetic.leetcode;

import java.util.ArrayDeque;

/**
 * Created by wangpeng on 2018/8/24.
 * 225. 用队列实现栈
 * 使用队列实现栈的下列操作：
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 * <p>
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 */
public class _225_MyStack {

    public static void main(String[] args) {
        MyStack obj = new MyStack();
        obj.push(1);
        obj.push(2);
        int param_2 = obj.pop();
        System.out.println("param_2:" + param_2);
        int param_3 = obj.top();
        System.out.println("param_3:" + param_3);
        boolean param_4 = obj.empty();
        System.out.println("param_4:" + param_4);
    }

    public static class MyStack {
        ArrayDeque<Integer> arrayDeque;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            arrayDeque = new ArrayDeque();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {
            ArrayDeque<Integer> tempQuene = new ArrayDeque<>();
            tempQuene.push(x);
            while (!arrayDeque.isEmpty()) {
                tempQuene.push(arrayDeque.pop());
            }
            while (!tempQuene.isEmpty()) {
                arrayDeque.push(tempQuene.pop());
            }
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {
            return arrayDeque.pop();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return arrayDeque.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return arrayDeque.isEmpty();
        }
    }
}
