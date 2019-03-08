import java.util.*;
/* Given a tree, clone it.
 */
public class CloneBinaryTree
{

	/* Goal  
	 * 1. Duplicate each node.
	 * 2. for each duplicated node, assign its left and right children. In order to assign 
	 * the childre the children has to exsit first. So we use post order travesal. 
	 *
	 * Wasy to traverse: post traversal
	 * 
	 * Ways to pass variables: function return
	 * In order to access the child nodes that are created in the last recursion call, 
	 * we use function return to pass the copied node so the current recussion call can access it.
	 * /
	public static BinaryTree.Node clone(BinaryTree.Node nd)
	{
		if(nd == null) return null;
		BinaryTree.Node ndCopy = new BinaryTree.Node(nd.getData());
		ndCopy.setLeft(clone(nd.left));
		ndCopy.setRight(clone(nd.right));
		return ndCopy;	
	}

	/* Ways to pass variables: Additional data structure as function paramter
	 * At each recursion call, the left child copy and right child copy are created in the previous
	 * recursion call, but we need to access from the original node. So we use a map to store
	 * the pair of <original node, copied node> 
	 *
	 * Difference between clone2() and clone() is clone2 uses additional data structure.
	 */
	public static void clone2(BinaryTree.Node node, Map<BinaryTree.Node, BinaryTree.Node> map)
	{
		if(node == null) return;
		clone2(node.left, map);
		clone2(node.right, map);
		BinaryTree.Node nodeCopy = new BinaryTree.Node(node.getData());
		map.put(node, nodeCopy);
		nodeCopy.setLeft(map.get(node.getLeft()));
		nodeCopy.setRight(map.get(node.getRight()));	
	}

	public static void clone2(BinaryTree.Node node)
	{
		Map<BinaryTree.Node, BinaryTree.Node> map 
			= new HashMap<BinaryTree.Node, BinaryTree.Node>();
		clone2(node, map);
		BinaryTree.inOrder(node);
		System.out.println();	
		BinaryTree.inOrder(map.get(node));
		System.out.println();	
	}

	/* Ways to traverse: combination
	 * use preorder traverse to duplicate node
	 * use postorder traverse to assign children 
	public static void clone3(BinaryTree.Node node, Map<BinaryTree.Node, BinaryTree.Node> map)
	{
		if(node == null) return;
		BinaryTree.Node nodeCopy = new BinaryTree.Node(node.getData());
		map.put(node, nodeCopy);
		clone3(node.left, map);
		clone3(node.right, map);
		nodeCopy.setLeft(map.get(node.getLeft()));
		nodeCopy.setRight(map.get(node.getRight()));	
	}

	public static void clone3(BinaryTree.Node node)
	{
		Map<BinaryTree.Node, BinaryTree.Node> map 
			= new HashMap<BinaryTree.Node, BinaryTree.Node>();
		clone3(node, map);
		BinaryTree.inOrder(node);
		System.out.println();	
		BinaryTree.inOrder(map.get(node));
		System.out.println();	
	}

	public static void test1()
	{
		BinaryTree.Node copy = clone(BinaryTree.tree1);	
		BinaryTree.inOrder(BinaryTree.tree1);
		System.out.println();	
		BinaryTree.inOrder(copy);
		System.out.println();	

	}

	public static void test2()
	{
		clone2(BinaryTree.tree1);	

	}

	public static void test3()
	{
		clone3(BinaryTree.tree1);	

	}
	
	public static void main(String[] args)
	{
		test1();	
		test2();	
		test3();	
	}
}
