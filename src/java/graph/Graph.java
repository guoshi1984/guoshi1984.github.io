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

	protected LinkedList<Node> vertices;
	public void addVertex(Node node)
	{
		vertices.add(node);
	}

	public LinkedList<Node> getVertices()
	{
		return vertices;
	}

	Graph()
	{
		vertices = new LinkedList<Node>();
	}
	

	public static Graph graph1 = new Graph();
	public static Graph graph2 = new Graph();
	static
	{
                Node node11 = new Node(1);
                Node node12 = new Node(2);
                Node node13 = new Node(3);
                Node node14 = new Node(4);
		Node node15 = new Node(5);
		//            1        1->2  1->3 
		//           /  \       
		//  	    2    3     2->2  2->4 3->4  3->5
		//         /_|\ / \  
		//            4 - 5   5->4     
                node11.addAdj(node12).addAdj(node13);
                node12.addAdj(node12).addAdj(node14);
                node13.addAdj(node14).addAdj(node15);
                node15.addAdj(node14);
		graph1.addVertex(node11);
		graph1.addVertex(node12);
		graph1.addVertex(node13);
		graph1.addVertex(node14);
		graph1.addVertex(node15);
                Node node20 = new Node(0);
                Node node21 = new Node(1);
                Node node22 = new Node(2);
                Node node23 = new Node(3);
		Node node24 = new Node(4);
		Node node25 = new Node(5);
		//    5   4      5->2  5->0 4->0  4->1
		//   / \ / \
		//  2   0   1    2->3 
		//   \     /
		//    \   /     
		//      3        3->1
		node25.addAdj(node22).addAdj(node20);
                node24.addAdj(node20).addAdj(node21);
                node22.addAdj(node23);
		node23.addAdj(node21);
		graph2.addVertex(node20);
		graph2.addVertex(node21);
		graph2.addVertex(node22);
		graph2.addVertex(node23);
		graph2.addVertex(node24);
		graph2.addVertex(node25);
	}

}
