import java.util.*;
import java.util.LinkedList;
/* LeetCode 138 Copy List with Random Pointer
 */
public class CopyListWithRandomPointer {
	public static 	class Node {
    		int val;
    		Node next;
    		Node random;

    		public Node(int val) {
        		this.val = val;
        		this.next = null;
        		this.random = null;
    		}

	}

	/* copy using BFS, use a map to access the copied node */		
	public static Node copy(Node node)
	{
		Queue<Node> q = new LinkedList<Node>();
		Set<Node> visited = new HashSet<Node>();
		Map<Node, Node> map = new HashMap<Node, Node>();
		q.offer(node);
		while(!q.isEmpty()) {
			Node currentNode = q.poll();
			if(!map.containsKey(currentNode))
				map.put(currentNode, new Node(currentNode.val));
			visited.add(currentNode);
			if(currentNode.next != null) {
				if(!visited.contains(currentNode.next)) 
					q.offer(currentNode.next);
				if(!map.containsKey(currentNode.next)){
					map.put(currentNode.next, new Node(currentNode.next.val));
				}
				map.get(currentNode).next = map.get(currentNode.next);

			}
			if(currentNode.random != null) { 
				if(!visited.contains(currentNode.random)) 
					q.offer(currentNode.random);
				if(!map.containsKey(currentNode.random)){
					map.put(currentNode.random, new Node(currentNode.random.val));
				}
				map.get(currentNode).random = map.get(currentNode.random);
			}
		}

		return map.get(node);


	}
	
	/* For each node, insert the copied node right after it. All copied nodes have null random pointer    
	 * node 1 -> copied node 1 -> node 2 - copied node2
	 * then create random pointer for the copied node, because each copied node comes right after the original one, 
	 * we can access the random reference of the copied node
	 * break up the reference to have two separate lists.
	 */
	public static Node copy2(Node head) {
		if (head == null) return null;
		Node current = head;
		while(current != null) {
			Node copy = new Node(current.val);
			copy.next = current.next;
			current.next = copy;
			current = copy.next;
		}
		
		current = head;
		while(current != null) {
			if(current.random != null) {
				current.next.random = current.random.next;
			}
			current = current.next.next;
		}

		current = head;
		Node result = head.next;
		while(current != null) {
			Node copy = current.next;
			current.next = copy.next;
			if(copy.next != null)
				copy.next = copy.next.next;
			current = current.next;
		}
		return result;
	}

	public static void main(String[] args) {
		Node node1 = new Node(7);
		Node node2 = new Node(13);
		Node node3 = new Node(11);
		Node node4 = new Node(10);
		Node node5 = new Node(1);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node1.random = null;
		node2.random = node1;
		node3.random = node5;
		node4.random = node1;
		node5.random = node3;
		Node nc = copy(node1);
		System.out.println(nc.next.random.val);
		Node nc2 = copy2(node1);	
		System.out.println(nc2.val);
	}
}
