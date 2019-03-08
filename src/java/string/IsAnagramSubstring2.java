/* Given a target string and a pattern string, find if the target string has a substring which is 
 * the anagram of the pattern string
 */ 
public class IsAnagramSubstring2
{
	static final int MAX = 256;
	static boolean compare(int arr1[], int arr2[])
	{
		for(int i=0; i< MAX; i++)
			if(arr1[i] != arr2[i])
				return false;
		return true;
	}

	static void search(String pat, String txt)
	{
		int m = pat.length();
		int n = txt.length();
		
		int[] countP = new int[MAX];
		int[] countTW = new int[MAX];
		
		for(int i=0; i<m; i++)
		{
			(countP[pat.charAt(i)])++;
			(countTW[txt.charAt(i)])++;
		}

		for(int i=m; i<n; i++)
		{	
			if (compare(countP, countTW))
				System.out.println("Found at Index "
				+ (i-m));
			(countTW[txt.charAt(i)])++;
			(countTW[txt.charAt(i-m)])--;
			
		}

		if(compare(countP, countTW))
			System.out.println("Found at Index "
				+ (n-m));
	}
	public static void main(String args[])
	{	
		String txt = "BACDGABCDA";
		String pat = "ABCD";
		search(pat, txt);
	}
}
