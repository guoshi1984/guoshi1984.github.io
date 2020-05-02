/* Leetcode 270
 * Given a binary search tree and a target node K, find the node
** with minimum absolute difference with give target value K
*/
public class ClosedValue
{
	
	static int min = Integer.MAX_VALUE;
	static int result = 0;
	public static void find(BinaryTree.Node node, int target)
	{
		if(node == null) return;
		if(Math.abs(node.getData() - target) < min)
		{
			min = Math.abs(node.getData() - target);
			result = node.getData();
		}
		if(target < node.getData())
			find(node.getLeft(), target);
		else 
			find(node.getRight(), target);
		 
	}	
	
	public static void test1()
	{
		find(BinaryTree.tree2, 18);
		
		System.out.println(result);
	}
	
	public static void main(String[] args)
	{
		test1();
	}
}
