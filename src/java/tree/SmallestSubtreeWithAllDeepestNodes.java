import java.util.*;
/* Leetcode 865. Smallest Subtree with all the Deepest Nodes
 * Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
 * A node is deepest if it has the largest depth possible among any node in the entire tree.
 * The subtree of a node is that node, plus the set of all descendants of that node.
 * Return the node with the largest depth such that it contains all the deepest nodes in its subtree.
 */
public class SmallestSubtreeWithAllDeepestNodes
{
	protected static BinaryTree.Node lowest = null;
	protected static int maxDepth = 0;	
	public static int find(BinaryTree.Node node, int level)
	{
		if(node == null) return -1;
		int left = find(node.getLeft(), level + 1);
		int right = find(node.getRight(), level + 1);
		maxDepth = Math.max(maxDepth, Math.max(left, right));
		if(left == right && left == maxDepth) lowest = node;
		if( left == -1 && right == -1) return level;
		else
			return Math.max(left, right);
	}

	public static class Pair
	{
		public Pair(int depth, BinaryTree.Node node)
		{
			this.depth = depth;
			this.node = node;
		}
		protected int depth;
		protected BinaryTree.Node node;
	}	

	// (1) For a given node define depth as the number of levels to the top root node. Given a node 
	//     Pair.depth is the maximum depth among all the leaves of the given node. Pair.node is the result.
	// (2) call find2 function recursively to traverse the tree to the deepest leaf 
	// (3) when we reach to the null node at the bottom, Pair.depth = -1(meaning invalid) Pair.node = null,
	//       3       level = 0
	//      / \
	//    null null   return (-1 null)
	// (4) after each recursion find2 is done. 
	//     if both left and right node have Pair.depth = -1, then it means the current node is the leaf, save the Pair.depth = level, and Pair.node = the node itself
	//         3     level = 0
	//        / \
	//       5   1   level = 1 Both call return (1, null)
	//     
	//     if either left and right node has Pair.depth = -1, then it means the current node is one leaf, Pair.depth should be the Pair.depth of the leaf node
	//         3  <-node  level = 0      call return (0, node)  
	//          \
	//           1  <- recursion call is done.  call return (-1, null)
	//     if left and right has same depth, depth should be either depth of its child, node should change to the current node, as it contains both 5 and 1 as child.
	//         3    <-node
	//        / \
	//       5   1   <- recursion call is done.  return (1, 5)  (1,1)
	//     if left and right has different depth, keep Pair to be the pair from child node with the larger depth
	//
	public static Pair find2(BinaryTree.Node node, int level)
	{
		if(node == null) return new Pair(-1, null);
		Pair left = find2(node.getLeft(), level + 1);
		Pair right = find2(node.getRight(), level + 1);
		if(left.depth ==-1 ||  right.depth == -1) return new Pair(Math.max(level, Math.max(left.depth, right.depth)), node);
		else if(left.depth == right.depth) return new Pair(left.depth, node);
		else if(left.depth > right.depth) return new Pair(left.depth, left.node);
		else return new Pair(right.depth, right.node);
	}

	public static Pair find3(BinaryTree.Node node)
	{
		if(node == null) return new Pair(-1, null);
		Pair left = find3(node.getLeft());
		Pair right = find3(node.getRight());
		int newDepth = Math.max(left.depth, right.depth) + 1;
		if(left.depth ==-1 ||  right.depth == -1) return new Pair(newDepth, node);
		else if(left.depth == right.depth) return new Pair(newDepth, node);
		else if(left.depth > right.depth) return new Pair(newDepth, left.node);
		else return new Pair(newDepth, right.node);
	}
	public static void main(String[] args)
	{
		BinaryTree.Node root = new BinaryTree.Node(3);
		root.setLeft(new BinaryTree.Node(5));
		root.getLeft().setLeft(new BinaryTree.Node(6));
		root.getLeft().setRight(new BinaryTree.Node(2));
		root.getLeft().getRight().setLeft(new BinaryTree.Node(7));
		root.getLeft().getRight().setRight(new BinaryTree.Node(4));
		root.setRight(new BinaryTree.Node(1));
		root.getRight().setLeft(new BinaryTree.Node(0));
		root.getRight().setRight(new BinaryTree.Node(8));
		root.getRight().getRight().setRight(new BinaryTree.Node(9));
		find(root, 0);
		System.out.println(lowest.getData());
		System.out.println(find2(root, 0).node.getData());
		System.out.println(find3(root).node.getData());
		BinaryTree.Node root1 = new BinaryTree.Node(3);
		root1.setLeft(new BinaryTree.Node(5));
		System.out.println(find2(root1, 0).node.getData());
	
	}
}
