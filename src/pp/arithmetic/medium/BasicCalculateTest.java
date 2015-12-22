package pp.arithmetic.medium;

import java.util.Deque;
import java.util.Stack;

/**
 * 
 * @author pphdsny
 * 
 *         Implement a basic calculator to evaluate a simple expression string.
 *         The expression string may contain open ( and closing parentheses ),
 *         the plus + or minus sign -, non-negative integers and empty spaces .
 *         You may assume that the given expression is always valid.
 * 
 *         Some examples: "1 + 1" = 2 " 2-1 + 2 " = 3 "(1+(4+5+2)-3)+(6+8)" = 23
 * 
 *         Note: Do not use the eval built-in library function.
 * 
 * @see https://leetcode.com/problems/basic-calculator/
 */
public class BasicCalculateTest {

	public static void main(String[] args) {
		int calculate = calculate("(1+(4+5+2)-32)+(6+8)");
		System.out.println("输出结果：" + calculate);
	}
	
	public static int calculate1(String s){
		//将中缀表达式转换成后缀表达式再进行计算
		return 0;
	}

	public static int calculate(String s) {
		Stack<Integer> stack = new Stack<Integer>();
		int result = 0;
		int number = 0;
		int sign = 1;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c)) {
				number = 10 * number + (int) (c - '0');
			} else if (c == '+') {
				result += sign * number;
				number = 0;
				sign = 1;
			} else if (c == '-') {
				result += sign * number;
				number = 0;
				sign = -1;
			} else if (c == '(') {
				// we push the result first, then sign;
				stack.push(result);
				stack.push(sign);
				// reset the sign and result for the value in the parenthesis
				sign = 1;
				result = 0;
			} else if (c == ')') {
				result += sign * number;
				number = 0;
				result *= stack.pop(); // stack.pop() is the sign before the
										// parenthesis
				result += stack.pop(); // stack.pop() now is the result
										// calculated before the parenthesis

			}
		}
		if (number != 0)
			result += sign * number;
		return result;
	}
}
