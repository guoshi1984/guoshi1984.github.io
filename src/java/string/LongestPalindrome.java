import java.util.*;
public class LongestPalindrome
{
	public static int find(String s)
	{
		boolean[][] check = new boolean[s.length()][s.length()];
		for(boolean[] row : check)
			Arrays.fill(row, false);

		for(int i=0 ; i< s.length(); i++)
		{
			for(int j=0; j<=i ; j++)
			{
				if(i==j) check[j][i] = true;
				else if(i-j ==1 && 
				s.charAt(i) == s.charAt(j))
				{
					check[j][i] = true;
					System.out.println(j + " "+i); 
				}
				else 
				{
					if((check[i-1][j+1] == true)
 					&& (s.charAt(i) == s.charAt(j)))
					{	check[j][i] = true;
						System.out.println(j + " "+i); 
					}
				}
			}
		}
		int maxLength = 0;
		for(int i = 0; i < s.length(); i++ )
		{
			for(int j = 0; j < s.length(); j++)
			{
				if(check[i][j] == true)
				{
					maxLength =
					Math.max(maxLength, j - i + 1);	
				}
			}

		}
		return maxLength;
		
	}
		
	public static void main(String[] args)
	{
		String s = new String("babad");
		System.out.println(find(s));
		
	}
}

