import java.util.*;
public class TrimBinarySearchTree
{
	public static BinaryTree.Node trim(BinaryTree.Node node, int L, int R) {
		
		if(node == null) return null;
		node.setLeft(trim(node.getLeft(), L, R));
		node.setRight(trim(node.getRight(), L, R));
		if(node.getData() < L) 
			return node.getRight();
		else if(node. getData() > R)
			return node.getLeft();
		else return node;
	}

	public static void test1() {
		BinaryTree.Node node = new BinaryTree.Node(1);
		node.setLeft(new BinaryTree.Node(0));
		node.setRight(new BinaryTree.Node(2));
		BinaryTree.Node node1 = trim(node, 1, 2);
		List<Integer> nodes = BinaryTree.inOrder(node1);	
		System.out.println(nodes);
	}
	
	public static void test2() {
		BinaryTree.Node node = new BinaryTree.Node(3);
		node.setLeft(new BinaryTree.Node(0));
		node.setRight(new BinaryTree.Node(4));
		node.getLeft().setRight(new BinaryTree.Node(2));
		node.getLeft().getRight().setLeft(new BinaryTree.Node(1));
		node = trim(node, 1, 3);
		List<Integer> nodes = BinaryTree.inOrder(node);	
		System.out.println(nodes);
	}

	public static void main(String[] args) {
		test1();
		test2();
	}

}
