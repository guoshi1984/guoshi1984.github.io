/* LeetCode 100
 * Given two binary tree, check if they are the same
 */
public class SameTree
{

	public static boolean checkSame(BinaryTree.Node t1, BinaryTree.Node t2)
	{
		if(t1 == null && t2 ==null)
		{
			return true;
		}
		if(t1 != null && t2 != null && t1.data == t2.data)
		{	
			return checkSame(t1.left, t2.left) && checkSame(t1.right, t2.right);
		}
		else
		{
			return false;
		}
	}

	public static void main(String[]  args)
	{
		System.out.println(checkSame(BinaryTree.tree1, BinaryTree.tree2));
	}
}
