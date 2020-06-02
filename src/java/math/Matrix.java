public class Matrix
{
	//
	public static boolean isToeplitzMatrix2(int[][] matrix)
	{
		final int m = matrix.length;
		final int n = matrix[0].length;

		for(int i = 0; i < m-1; i++){
			for(int j = 0; j < n-1; j++){
				if(matrix[i][j] != matrix[i+1][j+1])
					return false;
			}	
		}
		return true;
	}


	public static void main(String[] args)
	{	
		int[][]  matrix = { {1,2,3,4},
			            {5,1,2,3},
				    {9,5,1,2}};
		System.out.println(isToeplitzMatrix2(matrix));
		int[][]  matrix2 = { {1,2,3},
			             {5,1,3},
				     {3,5,1},
				    };
		System.out.println(isToeplitzMatrix2(matrix2));
		return;
		
	}	
}
