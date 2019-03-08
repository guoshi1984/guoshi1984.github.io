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

		public Node setNext(Node node)
		{
			this.next = node;
		}

		public Node getNext()
		{
			return next;
		}

		public Node getData()
		{
			return this.data;
		}

		public Node setData(int data)
		{
			this.data = data;
		}
		
		public Node add(int data)
		{
			this.next = new Node(data);
			return this.next;
		}

	}

	public static Node node1 = new Node(1); 
	static
	{
		node1.add(2).add(3).add(4).add(5);
	}
}
