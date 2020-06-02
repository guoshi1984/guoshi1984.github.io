/* Leetcode 125
 * Given a string, check if it is a palindrom
 */
public class IsPalindrome
{
	public static boolean check(String s)
	{
		final int n = s.length();
		int i = 0;
		int j = n - 1;
		while (i < j)
		{
			if(!(s.charAt(i) == s.charAt(j)))
			{
				return false;	
			}
			i++;
			j--;
		}
		return true;
	}
	
	public static void main(String[] args)
	{
		System.out.println(check("abcdcba"));
	}
}
