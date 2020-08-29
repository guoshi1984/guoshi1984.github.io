import java.util.*;
public class ValidParentheses {

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

