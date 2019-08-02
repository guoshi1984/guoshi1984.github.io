import java.util.*;
/* Implement a queue using two stacks. Implement two functions push pop
 */
public class TwoStackQueue
{
	private static Stack<Integer> stack1;
	private static Stack<Integer> stack2;

	public TwoStackQueue()
	{
		stack1 = new Stack<Integer>();
		stack2 = new Stack<Integer>();
	}
	public TwoStackQueue push(Integer i)
	{
		stack1.push(i);
		return this;
	}
	public Integer pop()
	{
		if(stack1.isEmpty() && stack2.isEmpty())
			throw new EmptyStackException();
		int x;
		if (stack2.isEmpty()) { 
			while (!stack1.isEmpty()) { 
				x = stack1.pop(); 
				stack2.push(x); 
			} 
		} 
		x = stack2.pop(); 
		return x; 
	}

	public static void test1()
	{
		TwoStackQueue tsq = new TwoStackQueue();
		tsq.push(1).push(2).push(3);
		if(tsq.pop()==1 && tsq.pop()==2 && tsq.pop()==3);
			System.out.println("Test1 passed!")	
		else
			System.out.println("Test1 failed")
		
	}
	public static void main(String[] args)
	{
		test1();
	}
}

