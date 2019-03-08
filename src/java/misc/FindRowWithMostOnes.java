/** Given a boolean 2D array, where each row is sorted. Find
* the row with the maximum number of 1s.
*/
public class FindRowWithMostOnes
{
	public static int find(int[][] matrix)
	{
		int m = matrix.length;
		int n = matrix[0].length;
		
		int result = 0;
		for(int i = 0, j = n-1; i < m && j>=0;)
		{
			if(matrix[i][j]==1) 
			{  
				--j;
				 result = i;
			}
			else{ ++i;}
		}	
		return result;
	}
	public static void main(String[] args)
	{
		int[][] matrix = {{0,0,0},{0,1,0},{1,1,1}};
		System.out.println(find(matrix));
	}
}
