import java.util.*;
public class LongestPath
{
	public static class Node
	{
		int val;
		Node left;
		Node right;
		Node(int val)
		{
			this.val = val;
			left = null;
			right = null;
		}
	}

	private static int longestPath;
	
		
	
	public static int findDepth(Node node)
	{
		
		if(node == null) return 0;
		int leftDepth = findDepth(node.left)+1;
		int rightDepth = findDepth(node.right)+1;
		longestPath = Math.max(leftDepth + rightDepth - 2,
			longestPath);	
		return Math.max(leftDepth, rightDepth);
	}

	public static void main(String[] args)
	{
		Node nd1 = new Node(1);
		Node nd2 = new Node(2);
		Node nd3 = new Node(3);
		Node nd4 = new Node(4);
		Node nd5 = new Node(5);
		nd1.left = nd2;
		//nd1.right = nd3;
		nd2.left = nd4;
		nd2.right = nd5;
		System.out.println(findDepth(nd1));
		System.out.println(longestPath);
	}
	
		
}
