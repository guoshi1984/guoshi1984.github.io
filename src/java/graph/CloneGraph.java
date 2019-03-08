import java.util.*;
public class CloneGraph
{
	public static class Node
	{
		public int val;
		public ArrayList<Node> adjList;
		Node(int val)
		{
			this.val = val;
			this.adjList = new ArrayList<Node>();
		}
		
		ArrayList<Node> getAdjList()
		{
			return this.adjList;	
		}
		void addAdj(Node nd)
		{
			this.adjList.add(nd);
		}
	}

	public static Node clone(Node nd)
	{
		LinkedList<Node> q = new LinkedList<Node>();
		Set<Node> needVisit = new HashSet<Node>();
		
		q.add(nd);
		//needVisit.add(nd);
		HashMap<Node, Node> map = new HashMap<Node, Node>();
		map.put(nd, new Node(nd.val));
		while(!q.isEmpty())
		{
			Node cur = q.poll();
			System.out.println("visiting "+cur.val);	
			ArrayList<Node> curAdjList = cur.adjList;
			for(Node curAdj : curAdjList)
			{	
				//System.out.println("checking "+ curAdj.val);
				//if(!needVisit.contains(curAdj))
				if(!map.containsKey(curAdj))
				{
					Node copy = new Node(curAdj.val);
					map.get(cur).addAdj(copy);
					q.add(curAdj);
					//needVisit.add(curAdj);
					map.put(curAdj, copy);
					System.out.println("adding "+ copy.val);
				}
				else
				{
					map.get(cur).addAdj(map.get(curAdj));
				}
			}
		}
		return map.get(nd);
	}
	public static void main(String[] args)
	{
		Node nd1 = new Node(1);
		Node nd2 = new Node(2);
		Node nd3 = new Node(3);
		Node nd4 = new Node(4);
		nd1.addAdj(nd2);
		nd2.addAdj(nd1);
		nd1.addAdj(nd3);
		nd3.addAdj(nd1);
		nd2.addAdj(nd3);
		nd3.addAdj(nd2);
		nd2.addAdj(nd4);
		nd4.addAdj(nd2);
		nd2.addAdj(nd4);
		nd4.addAdj(nd3);
		nd3.addAdj(nd4);
	
		System.out.println(clone(nd1).getAdjList().get(1).val);		
			
	}
	
	
}
