public class RangeSumOfBinarySearchTree
{
	/* Traverse the whole tree, for each node the return value is 
	 * the sum of nodes including itself and all the children whose values are within the give range. 
	 * Traverse the whole tree all the way down to the null node, 
	 * if node value is within the range, add it to the total sum and return to the parent level.
	 * if node value is not within the range, just add return value of its left and right subtree and return to the parent level.
	 * keep going up to the root node and return the result
	 * This works for any tree not only binary search tree
	 */ 
	public static int findSum(BinaryTree.Node node, int L, int R) {
		if(node == null) return 0;
		int 	left = findSum(node.getLeft(), L, R);
		int	right = findSum(node.getRight(), L, R);
		if(node.getData() >=L && node.getData() <= R)
			return node.getData() + left + right;
		else
			return left + right;
	}


	/* Use binary search tree structure, if node < L then skip the left subtree if node > R then skip the right subtree.	
	 * otherwise traverse down both left and right subtree, when done traversing, add the node to the total sum then return to the parent level.
	 */ 
	public static int findSum2(BinaryTree.Node node, int L, int R) {
		if(node == null) return 0;
		if(node.getData() < L) return findSum(node.getRight(), L, R);
		if(node.getData() > R) return findSum(node.getLeft(), L, R);
		return node.getData() + findSum(node.getLeft(), L, R) + findSum(node.getRight(), L, R);
			
	}

	public static void main(String[] argc) {
		int sum = findSum(BinarySearchTree.tree1, 5, 7);
		int sum2 = findSum2(BinarySearchTree.tree1, 5, 7);
		System.out.println(sum);
		System.out.println(sum2);
	}

}
