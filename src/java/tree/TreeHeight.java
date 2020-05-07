import java.util.*;
import java.math.*;
/* LeetCode 104
 */
class TreeHeight
{

	public static int getDepth(BinaryTree.Node node)
	{
		if(node == null) return 0;
		int leftHeight = getDepth(node.left);
		int rightHeight = getDepth(node.right);
		return Math.max(leftHeight, rightHeight)+1;
	}

	public static void test1()
	{
		System.out.println(getDepth(BinaryTree.tree1));
	}

	public static void main(String[] args)
	{
		test1();
	}
}
