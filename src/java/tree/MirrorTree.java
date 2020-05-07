
public class MirrorTree
{
	
	/* LeetCode 226 Invert Binary Tree
	 * Given a binary tree, change the tree structure to its mirror. The mirror tree of binary
	 * tree is defined as a tree with all its left and right children of all no-leaf nodes
	 * interchanged
 	 */
	public static BinaryTree.Node doMirrorTree(BinaryTree.Node node)
	{
		if(node == null)
		{
			return node;
		}
  		BinaryTree.Node left = doMirrorTree(node.getLeft());
		BinaryTree.Node right = doMirrorTree(node.getRight());
		node.left = right;
		node.right = left;
		//System.out.println(nd.getVal());  
		return node;
	}

	/* Using post order travesal, from bottom to top
	 * 
	 * Traverse the tree recursively to its bottom, when finding an empty node, stop and 
	 * return to the last recursive call.
	 *
	 * For each node, exchange the left and right node.
	 */ 
	public static void doMirrorTree2(BinaryTree.Node node)
	{
		if(node == null) return;
		doMirrorTree(node.left);
		doMirrorTree(node.right);
		BinaryTree.Node tmp = node.left;
		node.left = node.right;
		node.right = tmp;
	}	


	public static void main(String[] args)
	{
		BinaryTree.inOrder(BinaryTree.tree1);
		System.out.println();
		doMirrorTree(BinaryTree.tree1);
		BinaryTree.inOrder(BinaryTree.tree1);
		System.out.println();
	}
}
