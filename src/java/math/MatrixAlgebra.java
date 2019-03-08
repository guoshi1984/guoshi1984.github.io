import java.math.*;

public class MatrixAlgebra{


	/**
	 * Parity of the matrix, return 1 if interchanging rows even number of times, 
	 * -1 if interchanging rows odd number of times
	 * 
	 */

	public static class Parity
	{
		public int parity;
		public Parity()
		{
			parity = 1;
		}
		
	}

	/**
	 * Print 2D matrix
	 */

	public static void print(double[][] matrix)
	{
		for(int i=0; i<matrix.length; i++)
		{
			for(int j=0; j<matrix[0].length; j++)
			{
				System.out.print(matrix[i][j]+ " ");
			}
			System.out.print("\n");
		}
	}

	/**
	 * Print 1D double vector
	 */

	public static void print(double[] vector)
	{
		for(int i=0; i<vector.length; i++)
		{
			System.out.print(vector[i]+ " ");
		}
		System.out.print("\n");
	}	

	/**
	 * Print 1D int vector
	 */

	public static void print(int[] vector)
	{
		for(int i=0; i<vector.length; i++)
		{
			System.out.print(vector[i]+ " ");
			System.out.print("\n");
		}
	}

	/* Multiply two matrix with compatible dimension
	 *
	 * @param a The first matrix to be multiplied
	 * @param b The second matrix to be multiplied
	 * @param c The product matrix of multiplication 
	 */

	public static void matrixMultiply(double[][] a, double[][] b, double[][] c)
	{
		if(a[0].length != b.length)
			throw new IllegalArgumentException("Matrix dimensions do not match.");

		for(int i=0; i<a.length; i++)
		{
			for(int j=0; j<b[0].length; j++)
			{
				c[i][j] = 0;
				for(int k=0; k<a[0].length; k++)
				{
					c[i][j] += a[i][k]*b[k][j];	
				}
			}
		}
	}	
	

	/* LU decomposition
	 * @param a The matrix to be decomposed, it is updated in place and store the L and U matrix
	 * @param n The dimension of the matrix
	 * @param indx The array that records the row index after permutation. The row index in this array
	 * 		is based on the original matrix.
	 * @param d The parity of the matrix, return 1 if rows are interchanged even number of times, return
	 *		-1 if odd number of times.
	 * @return Return 1 if matrix is non-singular, return 0 if matrix is singular.
	 */

	public static int ludcmp (double[][]  	a,
			final int  	n,
			int[]  	indx,
			Parity   	d	 
			) 			
	{
		//array to store the scale value of each row, which is 1/(biggest element of the row)
     		double[] vv = new double[n];
   
		//initialize the parity to be 1
     		d.parity=1;
     		for (int i=0;i<n;++i)
     		{
       			double big=0.0;
       			for (int j=0;j<n;++j)
       			{
         			double temp=Math.abs(a[i][j]);
         			if(temp>big)
           			big=temp;
       			}
       			if (big == 0.0)
         		return 0;
       			vv[i]=1.0/big;
     		}
   
     		for (int j=0;j<n;++j)
     		{
       			int imax = j;
			// for i<j, calculate the U matrix
       			for (int i=0;i<j;++i)
       			{
         			double sum=a[i][j];
         			for (int k=0;k<i;++k)
         			{
           				sum -= a[i][k]*a[k][j]; 
         			}
         			a[i][j]=sum;   
       			}

			// for i>j, calculate the L matrix
       			double big = 0.0;
       			for (int i=j;i<n;++i)
       			{
         			double sum=a[i][j];
         			for (int k=0;k<j;++k)
           				// leave the division later 
           				sum -= a[i][k]*a[k][j];  
         			a[i][j]=sum;
         			// find which row of this j col should be used as pivot
         			double dum=vv[i]*Math.abs(sum);  
         			if(dum >=big)
         			{
           				big=dum;
           				// save the imax as the pivot
           				imax=i;             
         			}
       			}

       			// if interchanging rows, do it.
       			if (j != imax)                     
       			{
         			for (int k=0;k<n;++k)
         			{
           				double dum =a[imax][k];
           				a[imax][k]=a[j][k];
           				a[j][k]=dum;
         			}
         			//interchaning once, permutation of det needs a minus sign
         			d.parity = -d.parity;		
         			vv[imax]=vv[j];          
       			}

       			//save the order of the row index in an array, the index is the orignal row number
       			indx[j]=imax;
       			if (a[j][j] == 0.0)
       			{
				return 0;
       			}
       			if (j != n-1)
       			{
         			double dum=1.0/(a[j][j]);     
         			for (int i=j+1;i<n;++i)
           				// divide by the pivot element for the l parts
           				a[i][j] *= dum;       
       			}
     		}
     		return 1;
   	}

	/* LU backward substitution for solving Ax=b
	 * @param matrix The matrix A.
	 * @param n The dimension of A
	 * @param indx The array that saves the order of row index after partial pivoting
	 * @param b The vector b
	 */
	 public static double[] lubksb ( 	
			double[][] matrix,
			final int  n,
			int[] indx,
			double[] b	 
										) 			


	{
		int ii =-1;
		for (int i=0;i<n;++i)
		{
			int ip=indx[i];
			double sum = b[ip];
			b[ip]=b[i];
			if (ii>=0)
			{
				for (int j=ii;j<i;++j)
					//solve for Ly = b
					sum -= matrix[i][j]*b[j]; //solve for Ly = b
			}
			else
			{
			 	if (sum!=0.0)
					ii=i;
			}     	
			b[i]=sum;
		}	 
		
		for (int i=n-1;i>=0;--i)      // solve for Ux = y
		{
		     double sum = b[i];
		     for (int j=i+1;j<n;++j)
		       sum -= matrix[i][j]*b[j];
		     b[i]=sum/matrix[i][i];
		   }
		return b;
	}


	/* Invert a Matrix
	 * @param a The matrix to be inverted
	 * @param aInv The inverted matrix
	 */
	public static void invertMatrix(double[][] a, double[][] aInv)
	{
		if(a.length != a[0].length)
			throw new IllegalArgumentException("Matrix row and column dimensions do not match.");
		final int n = a.length;
		double[][] aTranspose = new double[n][n];

		int[] index = new int[n];
		Parity p = new Parity();
		//aInv = new double[n][n];

		for(int i=0; i<n; ++i)
		{
			for(int j=0; j<n; ++j )
			{
				// A trick here is to calculate A's transose instead of A
				// Because we would like to pass the col of aInv as parameter b
				// in the lubksb. However for 2d array, aInv[i] refers to
				// the row vector of A's inverse, which is the column vector of
				// A's inverse and tranpose. So if we pass aInv[i] as b and use 
				// the function lubksb(), what we get is the A's inverse transpose  
				// Now if we change the goal to pass A's transpose into lubksb(),
				// what we get is exactly A's inverse.
				aTranspose[i][j] = a[j][i];  
				aInv[i][j] = 0;
			}
			aInv[i][i] = 1;
		}

		if(MatrixAlgebra.ludcmp(aTranspose, n, index, p)==0)
		{
			throw new RuntimeException("Matrix in inversion is singular.");
		}

		for(int j=0; j<n; ++j)
		{
			lubksb(aTranspose, n , index, aInv[j]);
		}
	}

	public static void linearEquationCalculation(double[][] x, double[] b)
	{
		MatrixAlgebra.Parity p = new MatrixAlgebra.Parity();
		int [] index = new int[x.length];
		MatrixAlgebra.ludcmp(x, x.length, index, p);
		MatrixAlgebra.lubksb(x, x.length, index, b);
	}
	public static void leastSquareCalculation(double[][] x, double[] b, double[] beta)
	{
		final int nRows = x.length;
		final int nCols = x[0].length;
		final int nParas = beta.length;
		if(beta.length != x[0].length)
			throw new IllegalArgumentException("Matrix dimensions do not match.");
		double[][] xT= new double[nCols][nRows];
		for(int i=0; i<nCols; i++)
		{
			for(int j=0; j<nRows; j++)
				xT[i][j] = x[j][i];
		}
		double[][] xTx = new double[nCols][nCols];
		MatrixAlgebra.matrixMultiply(xT, x, xTx);
		MatrixAlgebra.print(xTx);
		double[][] xTxInv = new double[nCols][nCols];
		MatrixAlgebra.invertMatrix(xTx, xTxInv);
		double[][] xTxInvXT = new double[nCols][nRows];
		MatrixAlgebra.matrixMultiply(xTxInv, xT, xTxInvXT);
		double[][] b1 = new double[b.length][1];
		for(int i=0; i< b.length; i++)
			b1[i][0] = b[i];
		double[][] beta1 = new double[beta.length][1];
		MatrixAlgebra.matrixMultiply(xTxInvXT, b1, beta1);
		for(int i=0; i< beta.length; i++)
			beta[i] = beta1[i][0];

				
	}
	public static void main(String[] args)
	{
		double matrix[][] = {{1,3},{1,2}};
		double b[] = {8,6};
		MatrixAlgebra.linearEquationCalculation(matrix, b);
		System.out.println("linear equation calculation");
		MatrixAlgebra.print(b);
		//MatrixAlgebra.lubksb(matrix, matrix.length, index, b);
		double [][] matrixInverse = new double[matrix.length][matrix[0].length];
		invertMatrix(matrix, matrixInverse);
		MatrixAlgebra.print(matrixInverse);
		double product[][] = new double[matrix.length][matrix.length];
		MatrixAlgebra.matrixMultiply(matrix, matrixInverse, product);
		MatrixAlgebra.print(product);
		double x[][] = {{2, 1, 1 },
				{2, 2, 1},
				{5, 3, 1},
				{2, 4, 1}};
		double b1[] = {2,
			      4,
			      3,
		              4};
		double beta[] = new double[3];
		MatrixAlgebra.leastSquareCalculation(x, b1, beta);
		MatrixAlgebra.print(beta);	
	}
}
	

