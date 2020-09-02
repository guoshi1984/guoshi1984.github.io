public class InterleavingString
{
	/* LeetCode 97 Interleaving String
	 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
	* 
	* two D boolean result[i][j] stores whether s1.substring(0,j) and s2.substring(i, 0) form s3(0, i+j-1) 
	*/

		
	public static boolean check(String s1, String s2, String s3)
	{
		final int n1 = s1.length();
		final int n2 = s2.length();
		final int n3 = s3.length();
		
		if(n1 + n2 != n3) return false;
		
		boolean[][] result = new boolean[n1+1][n2+1];
		
		result[0][0] = true;
		
		for(int j=1; j <= n2; j++)
		{
			if((result[0][j-1] == true) && 
			(s2.charAt(j-1) == s3.charAt(j-1)))
			{	
				result[0][j] = true;
			}
			else
			{
				result[0][j] = false;
			}
		}

		for(int i=1; i <= n1; i++)
		{
			if(result[0][i-1] == true && 
			s1.charAt(i-1) == s3.charAt(i-1))
			{
				result[i][0] = true;
			}
			else
			{
				result[i][0] = false;
			}
		}

		
		for(int i = 1; i <= n1; i++)	
		{
			for(int j=1; j<= n2; j++)
			{
				if( (result[i][j-1] && 
				s3.charAt(i + j - 1) == s2.charAt(j-1))
				||(result[i-1][j] && 
				s3.charAt(i + j - 1) == s1.charAt(i-1)) )
				{
					result[i][j] = true;	
				}
				else
				{
					result[i][j] = false;	
				}
			}	
		}
		
		for(int i=0; i<= n1; i++)
		{
			for(int j=0; j<=n2; j++)
			{
				System.out.print(" "+ result[i][j] );
			}
			System.out.println();
		}	
		return result[n1][n2];
	}

	public static void main(String[] args)
	{
		String s2 = new String("aabcc");
		String s1 = new String("dbbca");
		String s3 = new String("aadbbcbcac");
		System.out.println(check(s1, s2, s3));

	}
}
