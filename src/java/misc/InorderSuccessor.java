public class InorderSuccessor
{
	public static class Node
	{
		int val;
		Node left;
		Node right;
		Node parent;
		
		Node(int val)
		{
			this.val = val;
			left = null;
			right = null;
		
		}
	}

	
	public static Node find2(Node nd, Node n)
	{
		if(n.right!=null)
		{
			return findLeft(n.right);
		}
		Node p = n.parent;
		while(p != null && n ==p.right)
		{
			n = p;
			p = p.parent;
		}
		return p;
	}
	public static Node findLeft(Node nd)
	{
		Node cur = nd;
		while(cur.left != null)
		{
			cur = cur.left;
		}
		return cur;
	}
	
	public static Node prev;

	public static void find(Node nd, int val)
	{
		if(nd == null) return;
		find(nd.left, val);
		
		System.out.println(nd.val);
		if(prev != null)
		{
			System.out.println("prev val" + prev.val);
		}	
		if(prev != null && prev.val == val)
		{	
			System.out.println("find " + nd.val);
		}
		prev = nd;
		
		find(nd.right,val);
		
	}
	
	//public static void find(Node)
	public static void main(String[] args)
	{
		Node nd1 = new Node(20);
		Node nd2 = new Node(8);
		Node nd3 = new Node(22);
		Node nd4 = new Node(4);
		Node nd5 = new Node(12);
		Node nd6 = new Node(10);
		Node nd7 = new Node(14);
		nd1.left = nd2;
		nd1.right = nd3;
		nd2.parent = nd1;
		nd3.parent = nd1;
		nd2.left = nd4;
		nd2.right = nd5;
		nd4.parent = nd2;
		nd5.parent = nd2;
		nd5.left = nd6;
		nd5.right = nd7;
		nd6.parent = nd5;
		nd7.parent = nd5;
		//find(nd1, 10);
		Node nd = find2(nd1, nd6);
		System.out.println(nd.val);
	}
}
