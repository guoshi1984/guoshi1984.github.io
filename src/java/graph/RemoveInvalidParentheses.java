import java.util.*;
/*LeetCode 301
 * Remove the minimum number of invalid parentheses in order to make the input string valid. 
 * Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 * Example 1:
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2: 
 * Input: "(a)())()" 
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 * Input: ")("
 * Output: [""]
 *
 *
 *
 * Let n be the number of parentheses in the string. 
 * While doing BFS we remove one one parantheses character from the given string 
 * and keep on doing it till we get a result (even an empty string is also an answer). 
 * So, in the worst case we traverse till the end. Therefore,
 * T(n) = n * C(n, n) + (n - 1) * C(n, n -1) + .... + C(n, 1)
 * Lets break up the terms for better understanding one first level we have n characters to remove from C(n,n) 
 * total number of string, at second level length of strings is n-1 and there are C(n,n-1) such strings, and so on.
 * Now, summation of T(n) = n*2^n. Here, is how it is achieved:
 * We know binomial theorm,
 * (1+x)^n = C(n,0) + C(n,1)*x + C(n,2)*x^2 + ... + C(n,n)*x^n
 * Taking derivative w.r.t x
 * n*(1 + x)^(n-1) = C(n,1) + 2*C(n,2)*x + ... + n*C(n,n)*x^(n-1)
 * Substituting x = 1,
 * n*2^n = C(n,1) + 2*C(n,2) + ... + (n-1)*C(n, n-1) + n*C(n,n)
 *
 */

public class RemoveInvalidParentheses {
	public static List<String> removeInvalidParentheses(String s) {
		List<String> res = new ArrayList<>();
			if(s == null) {
            			return res;
        	}
        	
		Queue<String> queue = new LinkedList<>();
        	Set<String> visited = new HashSet<>();
        	queue.offer(s);
        	boolean foundResult = false;
        
        	while(!queue.isEmpty()) {
            		s = queue.poll();
            		if(isValid(s)) {
                		res.add(s);
                		foundResult = true;
            		}
            		
			if(foundResult) {
                		continue;
            		}
            
			for(int i = 0; i < s.length(); i++) {
                		char c = s.charAt(i);
                		if(c == '(' || c == ')') {
                    			String t = s.substring(0, i) + s.substring(i + 1);
                    			if(!visited.contains(t)) {
                        			queue.offer(t);
                        			visited.add(t);
                    			}
                		}
            		}
        	}
        
        	return res;
	}
    
    	private static boolean isValid(String s) {
        	int leftCount = 0;
        	for(int i = 0; i < s.length(); i++) {
            		char c = s.charAt(i);
            		if(c == '(') {
                		leftCount++;
            		} else if (c == ')') {
                		leftCount--;
            		}
            		
			if(leftCount < 0) {
                		return false;
            		}
        	}
        
        	return leftCount == 0;
    	}

	public static void test1() {
		System.out.println("Test 1");
		String s = "()())()";
		List<String> result = removeInvalidParentheses(s);
		Collections.sort(result);
		if(result.equals(Arrays.asList(new String[] {"(())()","()()()"})))
			System.out.println("Test 1 passed.");
		else
			System.out.println("Test 1 failed.");
	}	

	public static void test2() {
		System.out.println("Test 2");
		String s = "(a)())()";
		List<String> result = removeInvalidParentheses(s);
		Collections.sort(result);
		if(result.equals(Arrays.asList(new String[] {"(a())()","(a)()()"})))
			System.out.println("Test 2 passed.");
		else
			System.out.println("Test 2 failed.");
	}

	public static void test3() {
		System.out.println("Test 3");
		String s = ")((";
		List<String> result = removeInvalidParentheses(s);
		Collections.sort(result);
		if(result.equals(Arrays.asList(new String[] {""})))
			System.out.println("Test 3 passed.");
		else
			System.out.println("Test 3 failed.");
	}

	public static void main(String[] args) {
		test1();
		test2();
		test3();
	}	

}
