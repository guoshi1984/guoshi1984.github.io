import java.util.*;
class Graph
{
	public static class Node
	{
		public int data;
		ArrayList<Node> adjList;
		
		Node(int data)
		{
			this.data = data;
			adjList = new ArrayList<Node>();
		}

		public int getData()
		{
			return this.data;
		}		

		public void setData(int data)
		{
			this.data = data;
		}

		public Node addAdj(Node node)
		{
			this.adjList.add(node);
			return this;
		}

		public ArrayList<Node> getAdjList()
		{
			return this.adjList;
		}
	}

	public static Node graph1;
	static
	{
                Node node1 = new Node(1);
                Node node2 = new Node(2);
                Node node3 = new Node(3);
                Node node4 = new Node(4);
		Node node5 = new Node(5);
                node1.addAdj(node2).addAdj(node3);
                node2.addAdj(node2).addAdj(node4);
                node3.addAdj(node4).addAdj(node5);
		graph1 = node1;
	}

}
