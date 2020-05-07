import java.util.*;
/* Given an N-ary tree, find the subtree with the maximum average. Return the root of the subtree.
 * A subtree of a tree is the node which have at least 1 child plus all its descendants. 
 * The average value of a subtree is the sum of its values, divided by the number of nodes.
 */
public class MaximumAverageSubtree
{
	public static class Node
	{
		public int value;
		public ArrayList<Node> children;
		public Node(int value)
		{
			this.value = value;
			this.children = new ArrayList<Node>();
		}
	}

	public static class Result
	{
		Node node;
		int sum;
		int size;
		public Result(Node node, int sum, int size)
		{
			this.node = node;
			this.sum = sum;
			this.size = size;
		}
	}

	private static Result result = null;
	public static Result find(Node node)
	{
		int currentSum = 0;
		int currentSize = 0;
		for(Node child : node.children)
		{
			Result childResult = find(child);
			currentSum += childResult.sum;
			currentSize += childResult.size;
		}

		currentSum += node.value;
		currentSize += 1;
		Result currentResult = new Result(node, currentSum, currentSize);

		if(result == null 
				|| currentResult.sum * result.size > result.sum * currentResult.size)
		{
			result = currentResult;
		}
		System.out.println(currentResult.sum);
		System.out.println(result.sum);
	 	return currentResult;		
	}

	public static Node getResult(Node node)
	{
		if(node == null)
			return null;
		find(node);
		return result.node;
	}
	
	public static void main(String[] args)
	{
		Node n1 = new Node(1);
		Node n2 = new Node(-5);
		Node n3 = new Node(11);
		Node n4 = new Node(1);
		Node n5 = new Node(2);
		Node n6 = new Node(4);
		Node n7 = new Node(-2);
		Node n8 = new Node(3);
		n1.children.add(n2);
		n1.children.add(n3);
		n2.children.add(n4);
		n2.children.add(n5);
		n3.children.add(n6);
		n3.children.add(n7);
		n2.children.add(n8);
		
		Node node = getResult(n1);
		System.out.println(node.value);
	}

}

