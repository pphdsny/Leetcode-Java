package pp.arithmetic.leetcode;

import java.util.Stack;

/**
 * Created by wangpeng on 2018/8/26.
 * <p>
 * 155. 最小栈
 * <p>
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
 * @see <a href="https://leetcode-cn.com/problems/min-stack/description/">min-stack</a>
 */
public class _155_MinStack {
    public static void main(String[] args) {
        MinStackOther obj = new MinStackOther();
        obj.push(Integer.MAX_VALUE);
        obj.push(Integer.MAX_VALUE);
        obj.push(Integer.MAX_VALUE);
        obj.top();
        obj.pop();
        obj.getMin();
        obj.pop();
        obj.getMin();
        obj.pop();
        obj.push(Integer.MAX_VALUE);
        obj.top();
        obj.getMin();
        obj.push(Integer.MIN_VALUE);
        obj.top();
        obj.getMin();
        obj.pop();
        System.out.println(obj.getMin());

    }

    public static class MinStack {

        Stack<Integer> dataStack;
        Stack<Integer> minStack;    //存每一位push进去的时候，最小的值

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            dataStack.push(x);
            if (minStack.isEmpty()) {
                minStack.push(x);
            } else if (x >= minStack.peek()) {
                minStack.push(minStack.peek());
            } else {
                minStack.push(x);
            }
        }

        public void pop() {
            dataStack.pop();
            minStack.pop();
        }

        public int top() {
            return dataStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /**
     * 解法更优，时间和空间复杂度都O(1)
     * 当存入的都是Integet.MAX_VALUE和MIN_VALUE的时候，会存在结果不正确的问题
     * //["MinStack","push","push","push","top","pop","getMin","pop","getMin","pop","push","top","getMin","push","top","getMin","pop","getMin"]
     * //[[],[2147483646],[2147483646],[2147483647],[],[],[],[],[],[],[2147483647],[],[],[-2147483648],[],[],[],[]]
     * //[null,null,null,null,2147483647,null,2147483646,null,2147483646,null,null,2147483647,2147483647,null,-2147483647,-2147483648,null,-2147483648]
     * //[null,null,null,null,2147483647,null,2147483646,null,2147483646,null,null,2147483647,2147483647,null,-2147483648,-2147483648,null,2147483647]
     */
    public static class MinStackOther {
        int min;
        Stack<Integer> stack;

        public MinStackOther() {
            stack = new Stack<>();
        }

        public void push(int x) {
            if (stack.isEmpty()) {
                stack.push(0);
                min = x;
            } else {
                stack.push(x - min);//Could be negative if min value needs to change
                if (x < min) min = x;
            }
        }

        public void pop() {
            if (stack.isEmpty()) return;

            int pop = stack.pop();

            if (pop < 0) min = min - pop;//If negative, increase the min value

        }

        public int top() {
            int top = stack.peek();
            if (top > 0) {
                return top + min;
            } else {
                return (min);
            }
        }

        public int getMin() {
            return min;
        }
    }
}
