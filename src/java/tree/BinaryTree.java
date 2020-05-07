import java.util.*;
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

	
	public static List<Integer> inOrder(Node node)
	{
		List<Integer> list = new ArrayList<Integer>();
		inOrder(node, list);
		return list;
	}
	

	public static void inOrder(Node node, List<Integer> list)
	{
		if(node == null)
			return;
		inOrder(node.getLeft(), list);
		list.add(node.getData());
		//System.out.print(node.getData());
		inOrder(node.getRight(), list);
	}

	public static void preOrder(Node node )
	{
		if(node == null)
			return;
		System.out.print(node.getData());
		preOrder(node.getLeft());
		preOrder(node.getRight());
	}

	public static void postOrder(Node node)
	{
		if(node == null)
			return;
		postOrder(node.getLeft());
		postOrder(node.getRight());
		System.out.print(node.getData());
	}

	public static List<Integer> levelOrder(Node node)
	{
		List<Integer> result = new ArrayList<Integer>();
		Queue<Node> queue = new LinkedList<Node>();
        	queue.add(node);
       		while (!queue.isEmpty()){
			// poll() removes the present head.
            		Node curNode = queue.poll();
			result.add(curNode.getData());
            		/*Enqueue left child */
            		if (curNode.left != null) {
                		queue.add(curNode.getLeft());
            		}

            		/*Enqueue right child */
            		if (curNode.right != null) {
                		queue.add(curNode.getRight());
            		}
        	}
		return result;
	}


	/* Leetcode 297
	 */

	public static String serializeBinaryTree(Node node)
	{
		if(node == null)
			return "null,";
		StringBuilder sb = new StringBuilder();
		sb.append(node.getData());
		sb.append(",");

		sb.append(serializeBinaryTree(node.getLeft()));
		sb.append(serializeBinaryTree(node.getRight()));
		return sb.toString();
	}

	public static Node deserializeBinaryTree(String data, String delimiter)
	{
		LinkedList<String> nodes = new LinkedList<String>();
		nodes.addAll(Arrays.asList(data.split(delimiter)));
		return deserializeBinaryTree(nodes);
	}
	private static Node deserializeBinaryTree(LinkedList<String> nodes)
	{
		String currentNode = nodes.removeFirst();
		if(currentNode.equals("null"))
			return null;
		else
		{
			Node node = new Node(Integer.parseInt(currentNode));
			node.setLeft(deserializeBinaryTree(nodes));
			node.setRight(deserializeBinaryTree(nodes));
			return node;
		}
	}

	/* Leetcode 199
	 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
	 */ 
	public static List<Integer> rightSideView(BinaryTree.Node root)
	{
		List<Integer> result = new LinkedList<Integer>();
		rightSideView(root, 0, result);
		return result;
	}

	public static void rightSideView(BinaryTree.Node root, int level, List<Integer> result)
	{
		if(root == null) return;
		if(result.size() == level) result.add(root.getData());
		rightSideView(root.getRight(), level+1, result);
		rightSideView(root.getLeft(), level+1, result);
	}

	public static Node tree1 = new Node();
	public static Node tree2 = new Node();
	public static Node tree3 = new Node();
	
	
	static
	{

		//initialize tree1
		//   	 1
		//  	/ \
		//     2   3
		//    / \ 
		//   4   5
		tree1.setData(1);
		tree1.setLeft(new Node(2));
		tree1.setRight(new Node(3));
		tree1.getLeft().setLeft(new Node(4));
		tree1.getLeft().setRight(new Node(5));
		
		//initialize tree2 binary search tree
		//          5
		//         / \
		//        4   7
		//           / \
		//          6   8 
		tree2.setData(5);
		tree2.setLeft(new Node(4));
		tree2.setRight(new Node(7));
		tree2.getRight().setLeft(new Node(6));
		tree2.getRight().setRight(new Node(8));
		
		//initialize tree3
		//   	 1
		//  	/ \
		//     2   3
		//    / \ 
		//   2   2
		tree3.setData(1);
		tree3.setLeft(new Node(2));
		tree3.setRight(new Node(3));
		tree3.getLeft().setLeft(new Node(2));
		tree3.getLeft().setRight(new Node(2));
		
	}

	public static void test1()
	{
		System.out.println("Test1: ");
		System.out.println("Tree1 preorder traversal result: ");
		preOrder(tree1);
		System.out.println("");

	}

	// test serialize and deserialize
	public static void test5()
	{
		System.out.println("Test 5: test serialize and deserialize binary tree");
		String treeString = serializeBinaryTree(tree1);
		boolean result1 = treeString.equals("1,2,4,null,null,5,null,null,3,null,null,");
		Node node = deserializeBinaryTree(treeString, ",");
		List<Integer> inOrderList = inOrder(node);
		boolean result2 = inOrderList.equals(Arrays.asList(new Integer[]{4,2,5,1,3}));
		//inOrder(node);

		if(result1 == true && result2 == true)
			System.out.println("Test5 passed");
		else
			System.out.println("Test5 failed");
	}


	// test right side view
	public static void test9()
	{
		//right side view of tree1
		System.out.println("Test 9: right side view of tree 1");
		System.out.println(rightSideView(tree1));
	}

	public static void test10()
	{
		System.out.println("Test 10: right side view of tree 2");
		System.out.println(rightSideView(tree2));
	}

	public static String writeIndent(int level)
	{
		String indent = new String("");
		for(int i = 0; i< level; i++)
			indent +="\t";
		return indent;

	}	
	public static void main(String[] args)
	{
		//String str = new String(serializeBinaryTree(tree1));
		//System.out.println(str);
		//Node node = deserializeBinaryTree(str, ",");
		levelOrder(tree1 );
		test1();
		test5();
		test9();
		test10();
	}
}


