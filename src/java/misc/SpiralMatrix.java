import java.util.*;
public class SpiralMatrix
{
	public static void printSpiral(int[][] matrix)
	{
		final int m = matrix.length;
		final int n = matrix[0].length;
			
		final int numOfSpirals = Math.min((int)Math.ceil(m/2.0),
			(int)Math.ceil(n/2.0));
		for(int spiralIndex = 0; spiralIndex < numOfSpirals;
			spiralIndex++)
		{
			int lowRowIndex = spiralIndex;
			int highRowIndex = m - spiralIndex - 1;
			int lowColIndex = spiralIndex;
			int highColIndex = n - spiralIndex - 1;
			
			for(int j = lowColIndex; j<= highColIndex; j++)
				System.out.print(" " + matrix[lowRowIndex][j]);
			for(int i = lowRowIndex+1; i<= highRowIndex; i++)
				System.out.print(" " + matrix[i][highColIndex]);
			if(highRowIndex > lowRowIndex)
			{
				for(int j = highColIndex-1; j >= lowColIndex; j--)
				System.out.print(" " + matrix[highRowIndex][j]);
			}
			if(highColIndex > lowColIndex)
			{
				for(int i = highRowIndex-1; i >= lowRowIndex+1; i--)
				System.out.print(" " + matrix[i][lowColIndex]);
			}
		}
		
	}
	
	public static void main(String[] args)
	{
		int[][] matrix =
		{ {1, 2, 3, 4, 5, 6},
		  {7, 8, 9, 10, 11, 12},
		  {13, 14, 15, 16, 17, 18},
		};
		printSpiral(matrix);
		System.out.println();

	}
}
