package pp.arithmetic.easy;

import pp.arithmetic.model.TreeNode;

/**
 * 
 * @author pphdsny
 * 
 *         Write a function that takes an unsigned integer and returns the
 *         number of ’1' bits it has (also known as the Hamming weight).
 * 
 *         For example, the 32-bit integer ’11' has binary representation
 *         00000000000000000000000000001011, so the function should return 3.
 * 
 *         {@link https://leetcode.com/problems/number-of-1-bits/}
 */
public class HammingWeightTest {

	public static void main(String[] args) {
		int result = hammingWeight(111);
		System.err.println("包含1的个数：" + result);
	}

	/**
	 * 3ms
	 * 
	 * @param n
	 * @return
	 */
	public static int hammingWeight(int n) {
		String hexString = Integer.toBinaryString(n);
		char[] array = hexString.toCharArray();
		int result = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == '1') {
				result++;
			}
		}
		return result;
	}

	/**
	 * 2ms
	 * 
	 * @param n
	 * @return
	 */
	public static int hammingWeight1(int n) {
		int ones = 0;
		while (n != 0) {
			ones = ones + (n & 1);
			//无符号除2
			n = n >>> 1;
		}
		return ones;
	}
}
