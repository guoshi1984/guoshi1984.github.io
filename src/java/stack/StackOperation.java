import java.util.*;
public class StackOperation
{
	
	public static void insertAtBottom(Stack<Integer> stack, int i)
	{
		if(stack.isEmpty())
			stack.push(i);
		else
		{
			int current = stack.peek();
			stack.pop();
			insertAtBottom(stack, i);
			stack.push(current);

		}
	}

	// reverse a stack using an additional stack
	public static Stack<Integer> reverse1(Stack<Integer> stack)
	{
		Stack<Integer> stack2 = new Stack<Integer>();
		while(!stack.isEmpty())
		{
			stack2.push(stack.peek());
			stack.pop();
		}
		
		return stack2;
	}

	// reverse a stack using recursive call statck 
	public static void reverse2(Stack<Integer> stack)
	{
		if(!stack.isEmpty())
		{
			int current = stack.peek();
			stack.pop();
			reverse2(stack);
			insertAtBottom(stack, current);
		}
	}

	public static void print(Stack<Integer> stack)
	{
		while(!stack.isEmpty())
		{
			int current = stack.peek();
			System.out.print(current);
			stack.pop();
		}
		System.out.println();
	}

	public static void test1()
	{
		Stack<Integer> stack = new Stack<Integer>();
		insertAtBottom(stack, 1);
		insertAtBottom(stack, 2);
		insertAtBottom(stack, 3);
		insertAtBottom(stack, 4);
		print(stack);
	}
	
	public static void test2()
	{
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0; i<10; i++)
		{
			stack.push(i);
		}
		long time1 = System.nanoTime();	
		stack = reverse1(stack);
		long time2 = System.nanoTime();
		long delta1 = time2 - time1;	
		System.out.println(delta1);	
		print(stack);
	}
	
	public static void test3()
	{
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0; i<10; i++)
		{
			stack.push(i);
		}
		long time3 = System.nanoTime();	
		reverse2(stack);
		long time4 = System.nanoTime();	
		long delta2 = time4-time3;	
		System.out.println(delta2);	
		print(stack);
	}

	//time complexity
	//size          10     100       1000
	//reverse1    0.071   0.481      2.93
	//reverse2    0.366   7.949      202
	//
	//so the first one is simple and faster
	//
	//space complexity
	//reverse1 uses additional stack, reverse2 uses function recursion call
	//stack to store the element of the original stack, so both methods use
	//addtion space.
	//
	public static void main(String[] args)
	{
		//test1();
		test2();
		test3();
	}


}
