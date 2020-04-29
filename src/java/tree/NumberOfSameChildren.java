public class NumberOfSameChildren
{
	public static boolean findSameChild(BinaryTree.Node node, BinaryTree.Node parent, int level, Count count)
	{
		if(node == null )	return false;
		System.out.println(BinaryTree.writeIndent(level) + node.getData());
		
		boolean left = findSameChild(node.getLeft(), node, level + 1, count);
		boolean right = findSameChild(node.getRight(), node, level + 1, count);
		
		if(left == true && right == true) {
			System.out.println(BinaryTree.writeIndent(level) + " Find all same child");
			count.count++;
		}

		if(parent!= null && node.getData() == parent.getData()) return true;
		else return false;
	}

	public static int findNumberOfNodesWithSameChild(BinaryTree.Node node,  int level, Count count)
	{
		if(node == null )	return -1;
		System.out.println(BinaryTree.writeIndent(level) + node.getData());
		
		int left = findNumberOfNodesWithSameChild(node.getLeft(), level + 1, count);
		int right = findNumberOfNodesWithSameChild(node.getRight(), level + 1, count);
		
		if(left != -1 && right != -1 && left == node.getData() && right == node.getData()) {
			System.out.println(BinaryTree.writeIndent(level) + " Find all same child");
			count.count++;
		}

		return node.getData();
	}


	public static class Count
	{
		Count(int count)
		{
			this.count = count;
		}
		int count;
	}
	public static void main(String[] args)
	{
		BinaryTree.Node tree0 = new BinaryTree.Node(101);
		tree0.setLeft(new BinaryTree.Node(101));
		tree0.setRight(new BinaryTree.Node(101));
		tree0.getLeft().setLeft(new BinaryTree.Node(101));
		tree0.getLeft().setRight(new BinaryTree.Node(101));
		tree0.getRight().setLeft(new BinaryTree.Node(100));
		tree0.getRight().setRight(new BinaryTree.Node(101));
		tree0.getLeft().getLeft().setLeft(new BinaryTree.Node(100));
		tree0.getLeft().getLeft().setRight(new BinaryTree.Node(101));
		Count count = new Count(0);
		findNumberOfNodesWithSameChild(tree0, 0, count);
		System.out.println(count.count);
	}


}
