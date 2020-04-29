public class RangeSumOfBinarySearchTree
{
	public static int findSum(BinaryTree.Node node, int L, int R) {
		if(node == null) return 0;
		int 	left = findSum(node.getLeft(), L, R);
		int	right = findSum(node.getRight(), L, R);
		if(node.getData() >=L && node.getData() <= R)
			return node.getData() + left + right;
		else
			return left + right;
	}

	public static void main(String[] argc) {
		int sum = findSum(BinarySearchTree.tree1, 5, 7);
		System.out.println(sum);
	}

}
