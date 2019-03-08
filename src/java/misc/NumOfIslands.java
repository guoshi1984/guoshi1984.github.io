public class NumOfIslands
{
	static boolean isSafe(int[][] mat, int i, int j, boolean visited[][])
	{
		
		final int m = mat.length;
		final int n = mat[0].length;
		return (i >= 0) && (i < m)
			&& (j >= 0) && (j < n)&&
			(mat[i][j] == 1 && !visited[i][j]);	
	}
	//dfs for all neighbors that is 1s

	static void dfs(int[][] mat, int i, int j, boolean visited[][])
	{
		
		final int m = mat.length;
		final int n = mat[0].length;
	
		int[] rowNeighbor 
		= {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] colNeighbor
		= {-1, 0, 1, -1, 1, -1, 0, 1};

		visited[i][j] = true;
		
		for(int k =0; k < 8; ++k)
		{
			if(isSafe(mat, i + rowNeighbor[k],
				j + colNeighbor[k], visited))
				dfs(mat, i + rowNeighbor[k],
				j + colNeighbor[k], visited);	
		}
			
	}
	
	static int find(int mat[][])
	{
		final int m = mat.length;
		final int n = mat[0].length;
		
		boolean[][] visited = new boolean[m][n];	
		int count = 0;
		for(int i = 0; i < m; i++ )
		{
			for(int j = 0; j < n; j++)
			{
				
				//mat[i][j] is not visited
				if(mat[i][j] ==1 && !visited[i][j])
				{
					dfs(mat, i, j, visited);
					count++;
				}
				
			}
		}
		return count;
	}
	
	public static void main(String[] args)
	{
		int[][] mat = {{1, 1, 0, 0, 0},
			      {0, 1, 0, 0, 1},
			      {1, 0, 0, 1, 1},
			      {0, 0, 0, 0, 0},
			      {1, 0, 1, 0, 1}};
		System.out.println(find(mat));			
	}
}
