class LowestCommonAncestor
{
	public static boolean lowestCommonAncestor(BinaryTree.Node node, int n1, int n2, BinaryTree.Node ancestor)
	{

		if(node == null) return false;
		boolean left = lowestCommonAncestor(node.getLeft(), n1, n2, ancestor);
		boolean right = lowestCommonAncestor(node.getRight(), n1, n2, ancestor);
		if(left == true && right == true && ancestor.getData() == 0)  
			ancestor.setData(node.getData()); 
		boolean current = false;
		if(node.getData() ==  n1 || node.getData() == n2) current = true;
		System.out.println(node.getData());
		System.out.println(left);
		System.out.println(right);
		if(ancestor != null) System.out.println(ancestor.getData());
		return left || right || current;
	}

	public static void lowestCommonAncestor(BinaryTree.Node node, int n1, int n2)
	{
		BinaryTree.Node ancestor = new BinaryTree.Node();
		lowestCommonAncestor(node, n1, n2, ancestor);
		System.out.println("ancestor "+ancestor.getData());
	}

	public static void test1()
	{
		lowestCommonAncestor(BinaryTree.tree1, 4, 5 );
	}
	public static void main(String[] args)
	{
		test1();	
	}
}
