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

	public static BinaryTree.Node findLowestCommonAncestor2(BinaryTree.Node node, int n1, int n2) {
		if(node == null) return null;
		
		if(node.getData() == n1 || node.getData() == n2)
			return node;
		BinaryTree.Node left = findLowestCommonAncestor2(node.getLeft(), n1, n2);
		BinaryTree.Node right = findLowestCommonAncestor2(node.getRight(), n1, n2);
		
		if(left != null && right != null) {
			return node;
		}
		else if(left == null && right == null) {
			return null;
		}
		else {
			return left == null? right:left;
		}
	        	
	}

	public static void test1()
	{
		lowestCommonAncestor(BinaryTree.tree1, 4, 5 );
	}

	public static void test2() {
		System.out.println("Test 2: LCA");
		BinaryTree.Node ancestor = findLowestCommonAncestor2(BinaryTree.tree1, 4, 5 );
		if(ancestor.getData() == 2)
			System.out.println("Test 2 passed.");
		else
			System.out.println("Test 2 failed.");

	}

	public static void test3() {
		System.out.println("Test 3: LCA");
		BinaryTree.Node ancestor = findLowestCommonAncestor2(BinaryTree.tree1, 4, 2 );
		if(ancestor.getData() == 2)
			System.out.println("Test 3 passed.");
		else
			System.out.println("Test 3 failed.");
	}

	public static void test4() {
		System.out.println("Test 4: LCA");
		BinaryTree.Node ancestor = findLowestCommonAncestor2(BinaryTree.tree1, 4, 3 );
		if(ancestor.getData() == 1)
			System.out.println("Test 4 passed.");
		else
			System.out.println("Test 4 failed.");
	}

	public static void main(String[] args)
	{
		test1();	
		test2();	
		test3();	
		test4();	
	}
}
