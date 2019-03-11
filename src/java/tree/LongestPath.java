import java.util.*;
public class LongestPath
{

	private static int longestPath;
	public static int findDepth(BinaryTree.Node node)
	{
		
		if(node == null) return 0;
		int leftDepth = findDepth(node.left)+1;
		int rightDepth = findDepth(node.right)+1;
		longestPath = Math.max(leftDepth + rightDepth - 2,
			longestPath);	
		return Math.max(leftDepth, rightDepth);
	}

	public static void test1()
	{
		findDepth(BinaryTree.tree1);
		System.out.println(longestPath);
	}

	public static void main(String[] args)
	{
		test1();
	}
	
		
}
