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

	public void remove(int key)
	{
		if(this.head == null) return;
		if(this.head.data == key) 
		{
			this.head = head.next;
			return;
		}

		Node current = this.head;
		Node prev = null;
		while(current !=null && current.data != key)
		{
			prev = current;
			current = current.next;
		}

		if(current == null) return;
		prev.next = current.next;

	}

	public boolean containsKey(int x)
	{
		Node current = this.head;
		while(current != null)
		{
			if(current.data == x)
				return true;
			current = current.next;

		}
		return false;
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

	public boolean hasLoop()
	{
		Node slow = this.head;
		Node fast = this.head;

		while(slow != null && fast != null && fast.next != null)
		{
			slow = slow.next;
			fast = fast.next.next;
			if(slow == fast)
			{
				return true;
			}
		}
		return false;	

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

	public int getLength()
	{
		return getLength(this.head);
	}

	private int getLength(Node node)
	{
		if(node == null)
			return 0;
		else
			return getLength(node.next) + 1;
	}
		
//	public static LinkedList list1 = new LinkedList(); 
	static
	{
//		list1.add(1).add(2).add(3).add(4).add(5);
	}

	// test function reverse() 
	public static void test1()
	{
		System.out.println("Test case 1");
		LinkedList list1 = new LinkedList();
		list1.add(1).add(2).add(3).add(4).add(5);
		System.out.println("Linked list before reverse");
		list1.print();
		list1.reverse();
		System.out.println("Linked list after reverse");
		list1.print();

	}

	// test function reverseUsingRecursion
	public static void test2()
	{
		System.out.println("Test case 2");
		LinkedList list1 = new LinkedList();
		list1.add(1).add(2).add(3).add(4).add(5);
		System.out.println("Linked list before reverse");
		list1.print();
		list1.reverseUsingRecursion();
		System.out.println("Linked list after reverse");
		list1.print();

	}

	public static void test3()
	{
		System.out.println("Test case 3");
		LinkedList list1 = new LinkedList();
		list1.add(1).add(2).add(3).add(4).add(5);
		System.out.println("Print linked list");
		list1.print();
		System.out.println("Print linked list reversely");
		list1.printReversely();
		System.out.println();

	}

	public static void test4()
	{
		System.out.println("Test case 4");
		LinkedList list1 = new LinkedList();
		list1.add(1).add(2).add(3).add(4).add(5);
		System.out.println("Print linked list");
		list1.print();
		list1.remove(4);
		list1.print();
		System.out.println("Print linked list after removing key 4");
		System.out.println();

	
	}

	public static void test5()
	{
		System.out.println("Test case 5");
		LinkedList list1 = new LinkedList();
		list1.add(1).add(2).add(3).add(4).add(5);
		System.out.println("Print linked list");
		list1.print();
		System.out.println("Length is " + list1.getLength());
		System.out.println();
	
	}

	public static void test6()
	{
		System.out.println("Test case 6");
		LinkedList list1 = new LinkedList();
		list1.add(1).add(2).add(3).add(4).add(5);
		System.out.println("Print linked list");
		list1.print();
		System.out.println("list contains 2: " + list1.containsKey(2));
		System.out.println();
	}

	public static void test7()
	{
		System.out.println("Test case 7");
		LinkedList list1 = new LinkedList();
		list1.add(1).add(2).add(3).add(4).add(5);
		System.out.println("Print linked list");
		list1.print();
		System.out.println("list has loop: " 
				+ list1.hasLoop());
		System.out.println("Create a loop by connecting tail to head");
		list1.head.next.next.next.next.next = list1.head.next;
		System.out.println("list has loop: " 
				+ list1.hasLoop());
		System.out.println();
	}

	public static void main(String[] args)
	{
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
	}
}
