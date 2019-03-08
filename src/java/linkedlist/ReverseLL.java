public class ReverseLL
{
	public static class Node
	{
		int data;
		Node next;
		Node(int data)
		{
			this.data = data;
			next = null;
		}
	}
	
	public static Node reverse(Node nd)
	{
		Node prev = null;
		Node cur = nd;
		Node next = null;
		while(cur != null)
		{
			next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		nd = prev;	
		return nd;
		
	}
	
	static void printLL(Node nd)
	{
		while(nd!=null)
		{
			System.out.print(" "+ nd.data);
			nd = nd.next;
		}
	}

	public static void main(String[] args)
	{
		Node nd1 = new Node(1);
		Node nd2 = new Node(2);
		Node nd3 = new Node(3);
		Node nd4 = new Node(4);
		nd1.next = nd2;
		nd2.next = nd3;
		nd3.next = nd4;
		
		Node result = reverse(nd1);
		printLL(result);
	}
}
