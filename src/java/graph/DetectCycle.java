import java.util.*;
public class DetectCycle
{
	public static class Node
	{
		public int val;
		ArrayList<Node> adjList;
		
		Node(int val)
		{
			this.val = val;
			adjList = new ArrayList<Node>();
		}
		
		void addAdj(Node nd)
		{
			this.adjList.add(nd);
		}
		ArrayList<Node> getAdjList()
		{
			return this.adjList;
		}
	}
	
	public static void find(Node nd, Set<Node> visited,
		Stack<Node> visitedStack)
	{
		visited.add(nd);
		visitedStack.push(nd);
		System.out.println(nd.val);
	 	System.out.println("stack push "+ nd.val);	
		for(Node curNd : nd.getAdjList())
		{
			if(visitedStack.search(curNd)!=-1)
			System.out.println("find cycle");
			if(!visited.contains(curNd))
				 find(curNd, visited, visitedStack);
		}
		System.out.println("stack pop "+ visitedStack.peek().val);
		visitedStack.pop();
		
		
	}
	public static void find(Node nd)
	{
		Set<Node> visited = new HashSet<Node>();
		Stack<Node> visitedStatck = new Stack<Node>();
		find(nd, visited, visitedStatck);
	}
	

	public static void main(String[] args)
	{
		Node nd0 = new Node(0);
                Node nd1 = new Node(1);
                Node nd2 = new Node(2);
                Node nd3 = new Node(3);
                nd0.addAdj(nd1);
                nd0.addAdj(nd2);
                nd1.addAdj(nd2);
                nd2.addAdj(nd0);
                nd2.addAdj(nd3);
                nd3.addAdj(nd3);
		find(nd2);
	}		
}
