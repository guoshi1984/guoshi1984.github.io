import java.util.*;
public class SnakeLadder
{
	public static class Node
	{
		int val;
		int dist;
		int jump;
		int fall;
		Node(int val)
		{
			this.val = val;
			this.dist = 0;
			this.jump = 0;
			this.fall = 0;
		}
		
		void setJump(int jump)
		{
			this.jump = jump;
		}
		void setFall(int fall)
		{
			this.fall = fall;
		}
		void setDist(int dist)
		{
			this.dist = dist;
		}
	}

	public static int find(List<Node> nodes)
	{

		Node cur = nodes.get(0);
		cur.dist = 0;
		
		Queue<Node> q = new LinkedList<Node>();
		Set<Node> visited = new HashSet<Node>();
		q.add(cur);		
		while(q.size()!=0)
		{
			cur = q.poll();
			int curVal = cur.val;
			System.out.println("curVal " + curVal);
			int curDist = cur.dist;
			for(int i = 1; i<=6; i++)
			{
				int newIndex = curVal+i-1;
				if(newIndex < 30)
				{
					Node newNode = nodes.get(newIndex);
				  
					if(!visited.contains(newNode))
					{
						visited.add(newNode);
						newNode.setDist(curDist+1);
						if(newNode.jump != 0)
						{
							Node jumpNode
							= nodes.get(newNode.jump);
							jumpNode.setDist(curDist+1);
							visited.add(jumpNode);
							q.add(jumpNode);
						}
						else
						{ 
							q.add(newNode);
						}
					}
				}
			}
		}
		
		return nodes.get(29).dist;
	}
	
	public static void main(String[] args)
	{
		List<Node> nodes = new ArrayList<Node>();
		
		for(int i = 0; i < 30; i++ )
		{
			Node nd = new Node(i+1);
			nodes.add(nd);
		}
		
		nodes.get(2).setJump(21);
		nodes.get(4).setJump(7);
		nodes.get(10).setJump(25);
		nodes.get(19).setJump(28);
		nodes.get(16).setFall(3);
		nodes.get(18).setFall(6);
		nodes.get(20).setFall(8);
		nodes.get(26).setFall(0);

		System.out.println(find(nodes));	
	}
	
	
}

