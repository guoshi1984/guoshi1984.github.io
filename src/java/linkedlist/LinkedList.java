class LinkedList
{
	
	public static class Node
	{
		private int data;
		private Node next;
		Node(int data)
		{
			this.data = data;
			this.next = null;
		}

		public void setNext(Node node)
		{
			this.next = node;
		}

		public Node getNext()
		{
			return next;
		}

		public int getData()
		{
			return this.data;
		}

		public void setData(int data)
		{
			this.data = data;
		}
		
		public Node add(int data)
		{
			this.next = new Node(data);
			return this.next;
		}


	}

	private Node head;

	public LinkedList()
	{
		this.head = null;
	}

	public LinkedList add(int data)
	{
		Node node = new Node(data);

		if(this.head == null)
		{	
			this.head = node;
		}
		else
		{
			Node last = this.head;
			while(last.next != null )
			{
				last = last.next;
			}
			last.next = node;
		}
		return this;

	}

	public void reverse()
        {
 		Node prev = null;
  		Node cur = this.head;
 		Node next = null;
 		while(cur != null)
         	{
         		next = cur.next;
 	        	cur.next = prev;
 			prev = cur;
 			cur = next;
 		}
 		this.head = prev;
 	}


	public void reverseUsingRecursion()
	{
		this.head = reverseUsingRecursion(this.head);
	}

	private Node reverseUsingRecursion(Node node)
	{
		if(node == null)
		{
			return node;								}
		
		if(node.next == null)
		{
			return node;
		}

		Node newHeadNode = reverseUsingRecursion(node.next);
		node.next.next = node;
		node.next = null;
		return newHeadNode;
	}

	public void print()
	{
		Node node = this.head;
		while(node != null)
		{
			System.out.print(" " + node.data);
			node = node.next;
		}
		System.out.println();
	}

	public void printReversely()
	{
		printReversely(this.head);
	}
	private  void printReversely(Node node)
	{
		if(node == null) return;
		printReversely(node.next);
		System.out.print(" "+node.data);
	}

		
//	public static LinkedList list1 = new LinkedList(); 
	static
	{
//		list1.add(1).add(2).add(3).add(4).add(5);
	}

	// test function reverse() 
	public static void test1()
	{
		LinkedList list1 = new LinkedList();
		list1.add(1).add(2).add(3).add(4).add(5);
		//list1.add(2);
		list1.reverse();
		list1.print();

	}

	// test function reverseUsingRecursion
	public static void test2()
	{
		LinkedList list1 = new LinkedList();
		list1.add(1).add(2).add(3).add(4).add(5);
		list1.reverseUsingRecursion();
		list1.print();

	}

	public static void test3()
	{
		LinkedList list1 = new LinkedList();
		list1.add(1).add(2).add(3).add(4).add(5);
		list1.printReversely();
		System.out.println();

	}

	public static void main(String[] args)
	{
		test1();
		test2();
		test3();
	}
}
