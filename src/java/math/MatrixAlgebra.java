package math;
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

	public static double[] getColumn(double[][] a, int index)
	{
		double[] v = new double[a[0].length];
		for(int i=0; i< a.length; i++)
		{
			v[i] = a[i][index];
		}
		return v;
	}
	
	public static void getColumn(double[][] a, double[] v, int index)
	{
		if(index >= a[0].length)
			throw new IllegalArgumentException("Matrix column index out of bound");

		for(int i=0; i< a.length; i++)
		{
			a[i][index] = v[i];
		}
	}

	/* Multiply two matrix with compatible dimension
	 *
	 * @param a The first matrix to be multiplied
	 * @param b The second matrix to be multiplied
	 * @return the product of axb
	 * */
	public static double[][] matrixMultiply(double[][] a, double[][] b)
	{
		if(a[0].length != b.length)
			throw new IllegalArgumentException("Matrix dimensions do not match.");

		double[][] c = new double[a.length][b[0].length];
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
		return c;
	}	
	
	/* check if a matrix is symmetric
	 * @param a the matrix to be checked
	 */
	public static boolean isSymmetric(double[][] a)
	{
		if(a.length != a[0].length) return false;
		else
		{
			for(int i=0; i<a.length; i++)
			{
				for(int j=0; j<i; j++)
				{
					if(a[i][j] != a[j][i])
						return false;
				}
			}
		}
		return true;
	}

	/* return the transpose of a matrix 
	 * @param a the input matrix
	 * @return the transpose of matrix a
	 */
	public static double[][] getTransposeMatrix(double[][] a)
	{
		double[][] aTranspose = new double[a[0].length][a.length];
		for(int i=0; i<a.length; i++)
		{
			for(int j=0; j<a[0].length; j++)
			{
				aTranspose[j][i] = a[i][j];
			}
		}
		return aTranspose;
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
	 * @return  The inverted matrix
	 */
	public static double[][] invertMatrix(double[][] a)
	{
		if(a.length != a[0].length)
			throw new IllegalArgumentException("Matrix row and column dimensions do not match.");
		final int n = a.length;
		double[][] aTranspose = new double[n][n];

		int[] index = new int[n];
		Parity p = new Parity();
		//aInv = new double[n][n];
		
		double[][] aInv = new double[n][n];
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
		return aInv;
	}

	/* Jacobi diagonalization
	 * @param Ain input matrix
	 * @param evals returned eigenvalues
	 * @param evecs returned eigenvectors
	 */

	public static void calculateEigenUsingJacobi(double[][] Ain, double[] evals, double[][] evecs)
	{
		
		if(Ain.length != Ain[0].length)
			throw new IllegalArgumentException("Matrix row and column dimensions do not match.");

		//TODO is symmetric matrix;
		//numerical recepies Jacobi transfomation eigen value solver
		
		if(MatrixAlgebra.isSymmetric(Ain)==false)		
			throw new IllegalArgumentException("Matrix has to be symmetric "+ 
					"in order to use Jacobi eigenvalue calculation!	");
		final int n = Ain.length;
		int p = 0;
		int q = 0;
		double fabsApq,fabsApp,fabsAqq;
		double a,c,blen,sitheta;
		double b;
		double t,zeta;
		double alpha,stau;
		double beta,betastar;
	        double  zp,zq;
	        final int MAXSWEEP=100;
		  
		/* local copy to work on */
		double[][] A = new double[n][n];
		/* Copy Ain to A */
		for(int i=0; i < n; i++)
		{
			for (int j=0; j < n; j++) 
			{
		         	A[i][j] = Ain[i][j];
		  	}
		}
			
		 /* Set evecs to identity */
		for(int i=0; i < n; i++)
		{
			for(int j=0; j < n; j++) 
			{
		  		if (i==j) evecs[i][j] = 1.0;
		  		else evecs[i][j] = 0.0;
		  	}
		}

	    	int sweep;
		for (sweep=0; sweep < MAXSWEEP; sweep++) 
		{		                      
			/* S = sum of abs of real and imag of off diag terms
			 *  maxoffdiag is maximum size of off-diagonal element, 
			 *  and (p,q) will contain its coordinates in the matrix A 
			 */

			double maxoffdiag = 0.0;
		        double S = 0.0;
		                
			for(int i=0; i < n; i++)
			{
		  		for(int j=i+1; j < n; j++) 
				{
		                	double temp = Math.abs(A[i][j]);
		                        S += temp;
		                        if(temp >= maxoffdiag) 
					{
		                		maxoffdiag = temp;
		                        	p = i;
		                        	q = j;
		                 	}
		                }
			}  

			if(maxoffdiag == 0.0) break;
		        // If we're done to machine precision, go home!
		        fabsApp = Math.abs(A[p][p]);
		        fabsAqq = Math.abs(A[q][q]);
		        
			if((maxoffdiag+fabsApp==fabsApp) && (maxoffdiag+fabsAqq==fabsAqq) )
		  		break;
			        //set threshold
			double thresh = 0.0;
			if(sweep < 5) thresh = 0.4*S/(n*n);
		  	// Loop over off diagonal terms of A in upper triangle: p < q
		  	for(p=0; p < n; p++)
			{
				for(q=p+1; q < n; q++) 
				{
		  			// If A(p,q) is too small compared to A(p,p) and A(q,q), 
					// skip the rotation
		  			fabsApq = Math.abs(A[p][q]);
		                        fabsApp = Math.abs(A[p][p]);
		  			fabsAqq = Math.abs(A[q][q]);
		 			if ( (fabsApq+fabsApp==fabsApp) && (fabsApq+fabsAqq==fabsAqq) )
		 				continue;
					//If A(p,q) is smaller than the threshold, then also skip
					// the rotation
					if (fabsApq <= thresh)
						continue;
		  			// the 2x2 matrix we diagonalize is [ [a b] ; [conj(b) a] ]
					a = A[p][p];
		  			c = A[q][q];
		                        b = A[p][q];
		                        blen = Math.abs(b);
					// zeta = \frac{1}{tan(2\theta)}
					zeta = (c-a)/(2.0*blen);
		  			// t = sgn(zeta)/(|zeta|+sqrt(zeta^2+1)), but if zeta is too
		  			//  huge, then we set t = 1/(2*zeta)
		  			if ( Math.abs(zeta)>1.0e200 )
		  				t = 1/(2.0*zeta);
		  			else {
		  				t = 1.0/(Math.abs(zeta)+Math.sqrt(zeta*zeta+1.0));
		  				if (zeta<0.0) t = -t;
		  			}
		  
		  			/* The matrix we use to diagonalize the 2x2 block above is
					 * [ [alpha beta] ; [-conj(beta) alpha] ] 
		  		         * where alpha is real and positive and alpha = cos(theta)
		 		       	 * and beta = sin(theta)*b/|b|.
		 			* The angle theta is chosen to diagonalize the 2x2 block.
		 			 * The relevant formula are sin(theta)=cos(theta)*t and
		 			 * cos(theta)=1/sqrt(1+t^2).
		 			 * stau = (1-alpha) cleverly written. */
		 			 alpha = 1.0/Math.sqrt(t*t+1.0);
		 			 sitheta = t*alpha;
		 			 stau = sitheta*sitheta/(1.0+alpha);
		 			 beta = b*sitheta/blen;
		 			 
					 //betastar = conj(beta);
		 			 betastar = beta; // because it is real	 
		  			/* Now we update the elements of A: */
		  			/* This involves chaning the p'th and q'th column of A */
		  			for(int i=0; i < n; i++) 
					{
		  				if(i==p) 
						{
		  					A[i][p] -= blen*t;
		  					A[i][q] = 0.0;
		  				}
		  				else if(i==q) 
						{
		  					A[i][p] = 0.0;
		  					A[i][q] += blen*t;
		  				}
						else 
						{
							zp = A[i][p];
							zq = A[i][q];
							A[i][p] -= stau*zp + betastar*zq;
							A[i][q] += beta*zp - stau*zq;
							A[p][i] = A[i][p];
							A[q][i] = A[i][q];
						}
					}
						
		 
		 
		  			/* Now we must update the eigenvector matrix with this
		  			* rotation:  evecs <- evecs*P_pq.
		  			* Update p'th and q'th column of evecs 
					* */
		  			for(int i=0; i < n; i++) 
					{
		  				zp = evecs[i][p];
		  				zq = evecs[i][q];
		  				evecs[i][p] = alpha*zp - betastar*zq;
		  				evecs[i][q] = beta*zp  + alpha*zq;
		  			}
		 	 	} /* (p,q) rotation loop */
			}
		} /* end of sweep loop */
		  
		if(sweep == MAXSWEEP) 
		{
		  		System.out.println("Warning:  Jacobi() needs more than "
						+MAXSWEEP + " sweeps ");
		}
		
	        //System.out.println(evals.length);	
		for(int i=0; i < evals.length; i++) 
		{
			evals[i] = A[i][i];
		}
		  		
		// sort eigs and evecs by ascending eigenvalue
		int[] list = new int[n]; 
		for(int i=0; i < n; i++) 
			list[i] = i;
	
		for(int i=1; i < n; i++) {
			double temp = evals[i];
		  	int j;
		  	for(j=i-1; j>=0 && evals[j]<temp; j--) {
		  		evals[j+1] = evals[j];
		  		list[j+1] = list[j];
		  	}
		  	evals[j+1] = temp;
		  	list[j+1] = i;
		}

		// todo, sort eigen vectors in place
		double[][] evecsOrig = new double[n][n];	
		for(int i=0; i < n; i++)
		{
			for(int j=0; j < n; j++)
			{
				evecsOrig[i][j] = evecs[i][j];
		  	}
		}

		for(int j=0; j < n; j++)
		{
			for(int i=0; i < n; i++)
			{
				evecs[i][j] = evecsOrig[i][list[j]];
		  	}
		}
		
		// inplace sort eigen vectors
		//for(int j=0; j < n; j++)
		//{
		//	while(list[j]!=j)
		//	{
		//		int oldTargetIndex = list[list[j]];
		//		double[] oldTargetVector = MatrixAlgebra.getColumn(evecs, list[j]);
		//
		//		MatrixAlgebra.setColumn(a, MatrixAlgebra.getColumn(evcs, j), list[j]);
		//		list[list[j]]= list[j];
		//
		//
		//		MatrixAlgebra.setColumn(a, oldTargetVector, j);
		//		list[j]= oldTargetIndex;
		//	}
		//}
		//

	}

	
	public static double[] solveLinearEquation(double[][] x, double[] b)
	{
		MatrixAlgebra.Parity p = new MatrixAlgebra.Parity();
		int [] index = new int[x.length];
		MatrixAlgebra.ludcmp(x, x.length, index, p);
		MatrixAlgebra.lubksb(x, x.length, index, b);
		return b;
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
		//double[][] xTx = new double[nCols][nCols];
		//MatrixAlgebra.matrixMultiply(xT, x, xTx);
		
		double[][] xTx = MatrixAlgebra.matrixMultiply(xT, x);
		
		double[][] xTxInv = MatrixAlgebra.invertMatrix(xTx);

		double[][] xTxInvXT = MatrixAlgebra.matrixMultiply(xTxInv, xT);
		double[][] b1 = new double[b.length][1];
		for(int i=0; i< b.length; i++)
			b1[i][0] = b[i];
		double[][] beta1 = MatrixAlgebra.matrixMultiply(xTxInvXT, b1);
		for(int i=0; i< beta.length; i++)
			beta[i] = beta1[i][0];

				
	}

	//svd not tested
	public static void svd(double[][] a, double[][] u, double[][] sigma, double[][] v)
	{
		final int nRows = a.length;
		final int nCols = a[0].length;
		double[][] aT= new double[nCols][nRows];
		for(int i=0; i<nCols; i++)
		{
			for(int j=0; j<nRows; j++)
				aT[i][j] = a[j][i];
		}
		double[][] aTa = MatrixAlgebra.matrixMultiply(aT, a);
		double[][] aaT = MatrixAlgebra.matrixMultiply(a, aT);
		
		final int nsigma = Math.min(nRows, nCols);
		double[] sigma21 = new double[nCols];
		double[] sigma22 = new double[nRows];

		calculateEigenUsingJacobi(aTa, sigma21, v);
		//calculateEigenUsingJacobi(aaT, sigma22, u);

		System.out.println("sigma21");
		MatrixAlgebra.print(sigma21);
		double[][] usigma = MatrixAlgebra.matrixMultiply(a, v);
	
		for(int i=0; i<nsigma; i++)
		{
			sigma[i][i] = Math.sqrt(sigma21[i]);
		}

		for(int j=0; j< u[0].length; j++)
		{
			for(int i=0; i< u.length; i++)
			{
				if(sigma[j][j]!=0)
					u[i][j] = usigma[i][j]/sigma[j][j];
			}
		}
		

	}

	public static void test1()
	{
		System.out.println("----------Run test case 1----------");
		System.out.println("Linear equation calculation: Ax=b");
		double matrix[][] = {{1,3},{1,2}};
		double b[] = {8,6};
		System.out.println("Matrix A");
		MatrixAlgebra.print(matrix);
		System.out.println("Vector b");
		MatrixAlgebra.print(b);
		b = MatrixAlgebra.solveLinearEquation(matrix, b);
		System.out.println("Solution vector x");
		MatrixAlgebra.print(b);
		System.out.println("\n");
		return;
	}

	public static void test2()
	{
		System.out.println("----------Run test case 2----------");
		System.out.println("Matrix inversion");
		double matrix[][] = {{1,3},{1,2}};
		System.out.println("Matrix A");
		MatrixAlgebra.print(matrix);
		double[][] matrixInverse = invertMatrix(matrix);
		System.out.println("Matrix A's inverse");
		MatrixAlgebra.print(matrixInverse);
		double[][] product = MatrixAlgebra.matrixMultiply(matrix, matrixInverse);
		System.out.println("A multiplied by A's inverse");
		MatrixAlgebra.print(product);
		System.out.println("\n");
	}

	public static void test3()
	{
		System.out.println("----------Run test case 3----------");
		System.out.println("Least square calculation X beta = b");
		double x[][] = {{2, 1, 1 },
				{2, 2, 1},
				{5, 3, 1},
				{2, 4, 1}};
		double b[] = {2,
			      4,
			      3,
		              4};
		System.out.println("Matrix x:");
		MatrixAlgebra.print(x);
		System.out.println("Vector b:");
		MatrixAlgebra.print(b);
		double beta[] = new double[3];
		MatrixAlgebra.leastSquareCalculation(x, b, beta);
		System.out.println("Solution beta:");
		MatrixAlgebra.print(beta);	
		System.out.println("\n");
	}

	public static void test4()
	{

		System.out.println("----------Run test case 4----------");
		System.out.println("Jacobi eigenvalues calculation");
		double a[][] = {{1, 2},
				{2, 1}};

		System.out.println("Matrix A");
		MatrixAlgebra.print(a);
		double evals[] = new double[a.length];
		double evecs[][] = new double[a.length][a.length];
		MatrixAlgebra.calculateEigenUsingJacobi(a, evals, evecs);	
		System.out.println("A's calculated eigenvalues");
		MatrixAlgebra.print(evals);
		System.out.println("A's calcualted eigenvectors");
		MatrixAlgebra.print(evecs);	
		System.out.println("A's expected eigenvalues");
		System.out.println("3.0 " + "-1.0 ");
		System.out.println("A's expected eigenvectors");
		System.out.println("0.707 " + "0.707 ");
		System.out.println("0.707 " + "-0.707 ");
		System.out.println("\n");	
	}
	public static void test5()
	{

		System.out.println("----------Run test case 5----------");
		System.out.println("Jacobi eigenvalues calculation");
		double a[][] = {{25, 20},
				{20, 25}};

		System.out.println("Matrix A");
		MatrixAlgebra.print(a);
		double evals[] = new double[a.length];
		double evecs[][] = new double[a.length][a.length];
		MatrixAlgebra.calculateEigenUsingJacobi(a, evals, evecs);	
		System.out.println("A's eigenvalues");
		MatrixAlgebra.print(evals);
		System.out.println("A's eigenvectors");
		MatrixAlgebra.print(evecs);	
		System.out.println("\n");	
	}
	
	public static void test6()
	{

		System.out.println("----------Run test case 6----------");
		System.out.println("Jacobi eigenvalues calculation");
		double a[][] = {{9, 12},
				{12, 41}};

		System.out.println("Matrix A");
		MatrixAlgebra.print(a);
		double evals[] = new double[a.length];
		double evecs[][] = new double[a.length][a.length];
		MatrixAlgebra.calculateEigenUsingJacobi(a, evals, evecs);	
		System.out.println("A's eigenvalues");
		MatrixAlgebra.print(evals);
		System.out.println("A's eigenvectors");
		MatrixAlgebra.print(evecs);	
		System.out.println("\n");	
	}
	
	public static void test7()
	{

		System.out.println("----------Run test case 7----------");
		System.out.println("Jacobi eigenvalues calculation");
		double a[][] = {{4, -30, 60, -35},
				{-30, 300, -675, 420},
				{60, -675, 1620, -1050},
				{-35, 420, -1050, 700},
				};
		System.out.println("Matrix A");
		MatrixAlgebra.print(a);
		double evals[] = new double[a.length];
		double evecs[][] = new double[a.length][a.length];
		MatrixAlgebra.calculateEigenUsingJacobi(a, evals, evecs);	
		System.out.println("A's eigenvalues");
		MatrixAlgebra.print(evals);
		System.out.println("A's eigenvectors");
		MatrixAlgebra.print(evecs);	
		System.out.println("\n");	
	}
	
	public static void test8()
	{

		System.out.println("----------Run test case 8----------");
		System.out.println("SVD calculation");
		double a[][] = {{3, 0},
				{4, 5}};

		System.out.println("Matrix A");
		MatrixAlgebra.print(a);
		double u[][] = new double[a.length][a.length];
		double v[][] = new double[a[0].length][a[0].length];
		double[][] sigma = new double[a.length][a[0].length];
		MatrixAlgebra.svd(a, u, sigma, v);	
		System.out.println("u");
		MatrixAlgebra.print(u);
		System.out.println("sigma");
		MatrixAlgebra.print(sigma);	
		System.out.println("v");
		MatrixAlgebra.print(v);
		System.out.println("usigmavt");
		double[][] usigma = MatrixAlgebra.matrixMultiply(u, sigma);
		double[][] a2= MatrixAlgebra.matrixMultiply(usigma, MatrixAlgebra.getTransposeMatrix(v));
		MatrixAlgebra.print(a2);
		System.out.println("\n");	
	}

	public static void test9()
	{

		System.out.println("----------Run test case 9----------");
		System.out.println("SVD calculation");
		double a[][] = {{1, 0, 0, 0, 2},
				{0, 0, 3, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 2, 0, 0, 0}};

		System.out.println("Matrix A");
		MatrixAlgebra.print(a);
		double[][] u = new double[a.length][a.length];
		double[][] v = new double[a[0].length][a[0].length];
		double[][] sigma = new double[a.length][a[0].length];
		MatrixAlgebra.svd(a, u, sigma, v);	
		System.out.println("u");
		MatrixAlgebra.print(u);
		System.out.println("sigma");
		MatrixAlgebra.print(sigma);	
		System.out.println("v");
		MatrixAlgebra.print(v);
		System.out.println("usigmav");
		double[][] usigma = MatrixAlgebra.matrixMultiply(u, sigma);
		double[][] a2= MatrixAlgebra.matrixMultiply(usigma, MatrixAlgebra.getTransposeMatrix(v));
		MatrixAlgebra.print(a2);
		System.out.println("\n");	
	}
	public static void main(String[] args)
	{
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
		test7();
		test8();
		test9();
	}
}
	

