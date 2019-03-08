public class BinaryTree
{
	public static class Node
	{
		public int data;
		public Node left;
		public Node right;
		public Node()
		{
			this.data = 0;
			left = null;
			right = null;

		}

		public Node(int data)
		{
			this.data = data;
			left = null;
			right = null;
		}
		
		public void setData(int data)
		{
			this.data = data;
		}

		public int getData()
		{
			return this.data;
		}		

		public void setLeft(Node node)
		{
			this.left = node;
		}

		public void setRight(Node node)
		{
			this.right = node;
		}
		
		public Node getLeft()
		{
			return left;
		}

		public Node getRight()
		{	
			return right;
		}

	}

	
	public static void inOrder(Node node)
	{
		if(node == null)
			return;
		inOrder(node.getLeft());
		System.out.print(node.getData());
		inOrder(node.getRight());
	}


	public static Node tree1 = new Node();
	public static Node tree2 = new Node();
	static
	{
		//initialize tree1
		tree1.setData(1);
		tree1.setLeft(new Node(2));
		tree1.setRight(new Node(3));
		tree1.getLeft().setLeft(new Node(4));
		tree1.getLeft().setRight(new Node(5));
		
		//initialize tree2
		tree2.setData(1);
		tree2.setLeft(new Node(2));
		tree2.setRight(new Node(3));
		tree2.getLeft().setLeft(new Node(4));
		tree2.getLeft().setRight(new Node(5));
	}
}


