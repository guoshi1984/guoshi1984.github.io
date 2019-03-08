
public class TreeDistance
{
	static class Node
	{
	 	Node left, right;
		int data;
		
		Node(int data)
		{
			this.data = data;
			left = null;
			right = null;
		}
	        	
		public void insert(int data )
		{
			if(this.data > data)
			{	
				if(this.left != null)
				{
					this.left.insert(data);
				}
				else
				{
					this.left = new Node(data);	
				}
			}
			else
			{	
				if(this.right != null)
				{	
					this.right.insert(data);
				}
				else
				{	
					this.right = new Node(data);
				}
			}
		}
	}
	
	static int d1 = -1;
	static int d2 = -1;
	static int dist = 0;
	
	static int findLevel(Node root, int k, int level)
	{
		if(root == null) return -1;
		if(root.data == k) return level;
		int l = findLevel(root.left, k, level+1);
		return (l!=-1)? l : findLevel(root.right, k, level+1);
	}

	static Node findDist(Node root, int n1, int n2, int lvl)
	{
		if(root == null) return null;
		System.out.println("find: "+ root.data);
		if(root.data == n1)
		{
			d1 = lvl;
			return root;
		}
		if(root.data == n2)
		{
			d2 = lvl;
			return root;
		}
		Node leftLca = findDist(root.left, n1, n2, lvl+1);
		Node rightLca = findDist(root.right, n1, n2, lvl+1);
		if(leftLca != null &&rightLca != null)
		{	
		//	System.out.println("l "+ leftLca.data +" r "
	//		+ rightLca.data);
			dist = d1 + d2 - 2*lvl;
			//System.out.println("d2 "+ d2);
			return root;
		}
		return (leftLca != null)? leftLca: rightLca;
	}
	static int findDist(Node root, int n1, int n2)
	{
		d1 = -1;
		d2 = -1;
		dist = 0;
		Node lca = findDist(root, n1, n2, 1);
		System.out.println("lca "+ lca.data);	
		if(d1 != -1 && d2 != -1) return dist;
		if(d1 != -1 )
		{
			dist = findLevel(lca, n2, 0);
			return dist;
		}
		if(d2 != -1 )
		{
			dist = findLevel(lca, n1, 0);
			return dist;
		}
		return -1;
	}
	public static void main(String[] args)
	{
		int[] values = {5, 3,6,1,4,2};
		Node root = new Node(values[0]);
		for(int i =1; i< values.length;  i++)
		{
			root.insert(values[i]);
		}
		//System.out.println(root.right.data);
		System.out.println(findDist(root, 1, 6));
	}
} 
