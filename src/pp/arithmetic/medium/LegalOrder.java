package pp.arithmetic.medium;

import java.util.Objects;
import java.util.Stack;

/**
 * Created by wangpeng on 2018/8/27.
 * 合法出入栈
 * 已知从1至n的数字序列，按顺序入栈，每个数字入栈后即可出栈，
 * 也可在栈中 停留，等待后面的数字入栈出栈后，该数字再出栈，求该数字序列的某出栈 序列是否合法?
 *
 * @see <a href="http://poj.org/problem?id=1363"></a>
 */
public class LegalOrder {

    public static void main(String[] args) {
        Stack<Integer> order = new Stack<>();
        order.push(4);
        order.push(5);
        order.push(2);
        order.push(1);
        order.push(3);
        boolean legalOrder = isLegalOrder(order);
        System.out.println("legalOrder:" + legalOrder);
    }

    public static boolean isLegalOrder(Stack<Integer> order) {
        int n = 5;
        Stack<Integer> stack = new Stack<>();
        for (int i = 1; i <= n; i++) {
            stack.push(i);
            while (!stack.isEmpty()
                    && Objects.equals(stack.peek(), order.peek())) {
                stack.pop();
                order.pop();
            }
        }
        if (order.isEmpty()) {
            return true;
        }
        return false;
    }
}
