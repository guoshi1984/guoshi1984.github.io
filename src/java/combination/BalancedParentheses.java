/* LeetCode 22
 * write a function to generate all possible n pairs of balanced parentheses
 *
 * Analysis: Each possible solution is a string with characters "(" or ")". We use a recursion call to generate
 * each character at each time until the size of characters reaches n. We build the string of characters based
 * on the principle of stack, which means each recursion call generates a character on top of the character stack,
 * after recursion the top character on the stack is popped.
 *
 */


public class BalancedParentheses
{
	/* Function that uses a recursive call to generate the string, based on DFS
	 * @param str The string we generate
	 * @param n The total size
	 * @param numberOfOpen The number of open parentheses that have been generated
	 * @param numberOfClose The number of close paratheses that have been generated
	 */
 	 private static void generateBalancedParentheses(String str, int n, int numberOfOpen, int numberOfClose)
	{
		if(numberOfClose == n)
		{
			System.out.println(str);
			return;
		}

		if(numberOfOpen < n)
		{
			str = str + '(';
			generateBalancedParentheses(str, n, numberOfOpen+1, numberOfClose);
			str = str.substring(0, str.length()-1);
		}
		if(numberOfOpen > numberOfClose)
		{
			str = str + ')';
			generateBalancedParentheses(str, n, numberOfOpen, numberOfClose+1);
			str = str.substring(0, str.length()-1);
		}

	}	

	// Top level function that wrapps the recursion call 
	public static void generateBalancedParentheses(int n)
	{
		String str = new String();
		generateBalancedParentheses(str, n, 0, 0);
	}

	public static void main(String[] args)
	{
		generateBalancedParentheses(3);
	}
}
