import java.util.*;
public class ValidParentheses {
	
	/* Leetcode 20
	 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
	 * An input string is valid if:
	 * Open brackets must be closed by the same type of brackets.
	 * Open brackets must be closed in the correct order.
	 *
	 * Solution: use a stack to keep track of the open brackets, when see a close bracket, peek the stack and check if they are in pair.
	 */

	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack();
  		for(int i=0; i< s.length(); i++) { 
			char c = s.charAt(i);
			if(c == '(' || c == '[' || c == '{' ) { 
				stack.push(c);
      				continue;
			}
			if(c ==')' && stack.peek() != '(')
      				return false;
    			if(c ==']' && stack.peek()!='[')
      				return false;
    			if(c =='}' && stack.peek()!='{')
      				return false;
    			stack.pop();
  		}	
  		
		if(stack.size()!=0) return false;
  		return true;
	}

	public static void main(String[] args) {
		String s1 = new String("()");
		System.out.println(isValid(s1));
		String s2 = new String("()[]{}");
		System.out.println(isValid(s2));
		String s3 = new String("(]");
		System.out.println(isValid(s3));
		String s4 = new String("([)]");
		System.out.println(isValid(s4));
		String s5 = new String("{[]}");
		System.out.println(isValid(s5));
	}
}

