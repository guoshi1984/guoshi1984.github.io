import java.util.*;
public class TopologicalSorting
{
	
	private static void find(Node node, Set<Node> visited,
		LinkedList<Node> result)
	{
		visited.add(node);
		//System.out.println(nd.val);
		for(Node thisNode : nd.getAdjList())
		{
			if(!visited.contains(thisNode))
				 find(thisNode, visited, result);
		}
		result.offerFirst(node);
		
		
	}
	public static List<Node> find(Node nd)
	{
		Set<Node> visited = new HashSet<Node>();
		LinkedList<Node> result = new LinkedList<Node>();
		find(nd, visited, result);
		for(Node thisNode : result)
		{
			System.out.println(curNd.getData());
		}
		return result;
	}
	

	public static void main(String[] args)
	{
                Node nd1 = new Node(1);
                Node nd2 = new Node(2);
                Node nd3 = new Node(3);
                Node nd4 = new Node(4);
		Node nd5 = new Node(5);
                nd1.addAdj(nd2);
                nd1.addAdj(nd3);
                nd2.addAdj(nd3);
                nd2.addAdj(nd4);
                nd3.addAdj(nd4);
                nd3.addAdj(nd5);
               // nd2.addAdj(nd4);
               // nd4.addAdj(nd2);
               // nd2.addAdj(nd4);
               // nd4.addAdj(nd3);
               // nd3.addAdj(nd4);
		find(nd1);
	}		
}
