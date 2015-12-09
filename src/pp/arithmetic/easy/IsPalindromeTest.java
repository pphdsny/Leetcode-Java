package pp.arithmetic.easy;
/**
 * 
 * @author pphdsny
 * 
 *         Given a string, determine if it is a palindrome, considering only
 *         alphanumeric characters and ignoring cases.
 * 
 *         For example, "A man, a plan, a canal: Panama" is a palindrome.
 *         "race a car" is not a palindrome.
 * 
 *         Note: Have you consider that the string might be empty? This is a
 *         good question to ask during an interview. For the purpose of this
 *         problem, we define empty string as valid palindrome.
 * 
 * @see https://leetcode.com/problems/valid-palindrome/
 */
public class IsPalindromeTest {

	public static void main(String[] args) {
		boolean palindrome = isPalindrome("race a car");
		System.out.println("是否是回文字符串：" + palindrome);
		
	}

	// run 40ms
	public static boolean isPalindrome1(String s) {
		String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
		String rev = new StringBuffer(actual).reverse().toString();
		return actual.equals(rev);
	}

	// run 8ms
	public static boolean isPalindrome(String s) {
		if (s.length() == 1) {
			return true;
		}
		char[] array = s.toCharArray();
		int i = 0, j = array.length - 1;
		while (i < j) {
			char startChar;
			char endChar;
			while (!Character.isLetterOrDigit(array[i]) && i < j) {
				i++;
			}
			if (Character.isUpperCase(array[i])) {
				startChar = Character.toLowerCase(array[i]);
			} else {
				startChar = array[i];
			}
			while (!Character.isLetterOrDigit(array[j]) && i < j) {
				j--;
			}
			if (Character.isUpperCase(array[j])) {
				endChar = Character.toLowerCase(array[j]);
			} else {
				endChar = array[j];
			}
			if (startChar != endChar) {
				return false;
			}
			i++;
			j--;
		}
		return i >= j;
	}

	public static boolean isAlphanumeric(char c) {
		if (c >= 48 && c <= 57) {
			// 数字
			return true;
		}
		if (c >= 65 && c <= 90) {
			// 大写字母
			return true;
		}
		if (c >= 97 && c <= 122) {
			// 小写字母
			return true;
		}
		return false;
	}
}
