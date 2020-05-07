import java.util.*;
public class BinarySearchTree
{

	 /* Leetcode 98
         * returns true if given search tree is binary
         * search tree (efficient version)
         */
        public static boolean isValidBST(BinaryTree.Node node)
        {
                return isValidBSTUtil(node, Integer.MIN_VALUE,
                               Integer.MAX_VALUE);
        }

        /* Returns true if the given tree is a BST and its
                values are >= min and <= max. */
        public static boolean isValidBSTUtil(BinaryTree.Node node, int min, int max)
        {
                /* an empty tree is BST */
                if (node == null)
                        return true;

                /* false if this node violates the min/max constraints */
                if (node.getData() < min || node.getData() > max) {
                        return false;
                }

                /* otherwise check the subtrees recursively
                        tightening the min/max constraints */
                // Allow only distinct values
                return (isValidBSTUtil(node.getLeft(), min, node.getData() - 1) &&
                        isValidBSTUtil(node.getRight(), node.getData() + 1, max));
        }

	/*
	 * 
	public static BinaryTree.Node buildBinarySearchTreeFromPreorder(LinkedList<Integer> lists)
        {
                ListIterator<Integer> iterator = lists.listIterator();
                BinaryTree.Node root = null;
                if(iterator.hasNext()) {
                        root = new BinaryTree.Node(iterator.next());
                }
                else return null;
                while (iterator.hasNext()) {
                        insert(root, iterator.next());
                }
                return root;

        }
	*/


	/* Leetcode 701
	 * A recursive function to insert a new node in BST */
        private static BinaryTree.Node insert(BinaryTree.Node node, int data)
        {
                if(node == null) return new BinaryTree.Node(data);
                else if(data < node.getData()) node.setLeft(insert(node.getLeft(), data));
                else node.setRight(insert(node.getRight(), data));
		return node;
        }

  
	/* A recursive function to delete a node in BST 
	 * Leetcode 450
	 * */
    	public static BinaryTree.Node remove(BinaryTree.Node node, int data) 
    	{ 
        	if (node == null)  return node; 
  
        	if (data < node.getData()) 
            		node.setLeft(remove(node.getLeft(), data)); 
        	else if (data > node.getData()) 
            		node.setRight(remove(node.getRight(), data)); 
  		else { 
            		// node with only one child or no child 
            		if (node.getLeft() == null) 
                		return node.getRight(); 
            		else if (node.right == null) 
                		return node.getLeft(); 
  
            		// node with two children: Get the inorder successor (smallest 
            		// in the right subtree) 
            		int target = findMinValue(node.getRight()); 
  
 	           	// Delete the inorder successor 
            		node.setRight(remove(node.getRight(), target)); 
        	} 
  
        	return node; 
    	}

	public static int findMinValue(BinaryTree.Node node)
	{
		int minValue = node.getData();
		while(node.getLeft()!= null) {
			minValue = node.getLeft().getData();
			node = node.getLeft();
		}
		return minValue;
	}
		
	public static BinaryTree.Node buildBinarySearchTreeFromInorder(List<Integer> nums)
	{
		BinaryTree.Node node = buildBinarySearchTreeFromInorder(nums, 0, nums.size()-1);
		return node;
	}

	/* LeetCode 109
	 * Build a Binary Search Tree from inorder
	 * The middle element should be the root
	 * Then build the left subtree using the nodes to left of the middle and right subtree to the right of the middle element recursively 
	 * when no elements availble, it means we have reached the bottom of the tree, return null, go to the parent level
	 * After a node's left subtree and right subtree have been built, return the node itself, go to the parent level
	 * keep return the node and reach the root node. Done
	 */ 
	public static BinaryTree.Node buildBinarySearchTreeFromInorder(List<Integer> nums, int start, int end)
	{
		if(start <= end) {
			int mid = (start + end)/2;
			BinaryTree.Node node = new BinaryTree.Node(nums.get(mid)); 
			node.setLeft(buildBinarySearchTreeFromInorder(nums, start, mid - 1));
			node.setRight(buildBinarySearchTreeFromInorder(nums, mid + 1 , end));
			return node;
		}
		else
			return null;
	}

	public static BinaryTree.Node tree1 = new BinaryTree.Node();
        public static BinaryTree.Node tree2 = new BinaryTree.Node();


        static
        {

		//initialize tree1 binary search tree
		//          5
		//         / \
		//        4   7
		//           / \
		//          6   8
		          
		tree1.setData(5);
		tree1.setLeft(new BinaryTree.Node(4));
		tree1.setRight(new BinaryTree.Node(7));
		tree1.getRight().setLeft(new BinaryTree.Node(6));
		tree1.getRight().setRight(new BinaryTree.Node(8));

                //initialize tree2 binary search tree
                //          5
                //         / \
                //        4   7
                //           / \
                //          6   8
                tree2.setData(5);
                tree2.setLeft(new BinaryTree.Node(4));
                tree2.setRight(new BinaryTree.Node(7));
                tree2.getRight().setLeft(new BinaryTree.Node(6));
                tree2.getRight().setRight(new BinaryTree.Node(8));

	}


	// test insert node
	public static void test1()
	{
		System.out.println("Test 1: insert and remove element in BST");
		insert(tree1, 3);
		List<Integer> target = BinaryTree.levelOrder(tree1);
		List<Integer> ref = new ArrayList<Integer>(Arrays.asList(5, 4, 7, 3, 6, 8));
		boolean result = target.equals(ref);
		remove(tree1, 3);	
		List<Integer> target1 = BinaryTree.levelOrder(tree1);
		List<Integer> ref1 = new ArrayList<Integer>(Arrays.asList(5, 4, 7, 6, 8));
		boolean result1 = target1.equals(ref1);
		if(result == true && result1 == true) 
			System.out.println("Test 1 passed.");
                else System.out.println("Test 1 failed");
	}
	
	public static void test2()
	{
		System.out.println("Test 2: insert and remove element in BST");
		insert(tree1, 9);
		List<Integer> target = BinaryTree.levelOrder(tree1);
		List<Integer> ref = new ArrayList<Integer>(Arrays.asList(5, 4, 7, 6, 8, 9));
		boolean result = target.equals(ref);
		remove(tree1, 9);	
		List<Integer> target1 = BinaryTree.levelOrder(tree1);
		List<Integer> ref1 = new ArrayList<Integer>(Arrays.asList(5, 4, 7, 6, 8));
		boolean result1 = target1.equals(ref1);
		if(result == true && result1 == true) 
			System.out.println("Test 2 passed.");
                else System.out.println("Test 2 failed");
	}

	public static void test3()
	{
		System.out.println("Test 3: build BST from inorder traversal");
		List<Integer> nums = new ArrayList<Integer>(Arrays.asList(4, 5, 6, 7, 8));
		BinaryTree.Node node = buildBinarySearchTreeFromInorder(nums);
		List<Integer> target = BinaryTree.inOrder(node);
		System.out.println(target.toString());
		boolean result = target.equals(nums);
                if(result == true)
                        System.out.println("Test 3 passed.");
                else System.out.println("Test 3 failed");


	}


	public static void test4()
	{
		System.out.println("Test 4: build BST from inorder traversal");
		List<Integer> nums = new ArrayList<Integer>(Arrays.asList(-10, -3, 0, 5, 9));
		BinaryTree.Node node = buildBinarySearchTreeFromInorder(nums);
		List<Integer> target = BinaryTree.inOrder(node);
		System.out.println(target.toString());
		boolean result = target.equals(nums);
                if(result == true)
                        System.out.println("Test 4 passed.");
                else System.out.println("Test 4 failed");


	}

	// test valid binary search tree
        public static void test7()
        {
                boolean result = isValidBST(tree2);
                if(result == true) System.out.println("Test 7 passed.");
                else System.out.println("Test 7 failed");
        }

        // test valid binary search tree
        public static void test8()
        {
                boolean result = isValidBST(tree1);
                if(result == false) System.out.println("Test 8 passed.");
                else System.out.println("Test 8 failed");
        }
	
	public static void main(String[] args)
	{
		test1();
		test2();
		test3();
		test4();
		test7();
                test8();
	}
}
