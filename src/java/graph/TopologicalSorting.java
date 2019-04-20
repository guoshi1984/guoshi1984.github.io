import java.util.*;
public class TopologicalSorting
{
	
	private static void find(Graph.Node node, Set<Graph.Node> visited,
		LinkedList<Graph.Node> result)
	{
		visited.add(node);
		System.out.println("current node"+ node.getData());
		for(Graph.Node thisNode : node.getAdjList())
		{
			if(!visited.contains(thisNode))
				 find(thisNode, visited, result);
		}
		System.out.println("add "+ node.getData());
		result.offerFirst(node);
		
		
	}
	
	public static List<Graph.Node> find(Graph graph)
	{
		Set<Graph.Node> visited = new HashSet<Graph.Node>();
		LinkedList<Graph.Node> result = new LinkedList<Graph.Node>();
		for(Graph.Node node : graph.getVertices())
		{
			if(!visited.contains(node))
				find(node, visited, result);
		}

		for(Graph.Node thisNode : result)
		{
			System.out.println(thisNode.getData());
		}
		return result;
	}
	

	public static void main(String[] args)
	{
		find(Graph.graph2);
	}		
}
