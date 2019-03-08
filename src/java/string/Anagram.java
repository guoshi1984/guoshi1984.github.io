import java.util.Arrays;
/* Given two strings, return true if two strings are anagram, return false if not.
 */

public class Anagram
{
	private static int NO_OF_CHARS = 256;
	public static boolean areAnagram(String s1, String s2)
	{
		char[] chars1 = s1.toCharArray();
		char[] chars2 = s2.toCharArray();
		if(chars1.length != chars2.length) return false;
		int count1[] = new int[NO_OF_CHARS];
		int count2[] = new int[NO_OF_CHARS];
		Arrays.fill(count1, 0);
		Arrays.fill(count2, 0);
		
		for(int i=0; i < chars1.length; i++)
		{
			count1[chars1[i]]++;
		}
		
		for(int i=0; i < chars2.length; i++)
		{
			count2[chars2[i]]++;
		}
		for(int i=0; i< NO_OF_CHARS; i++)
		{
			if(count1[i] != count2[i])
				return false;
		}
		return true;	
	}
	public static void test1()
	{
		String s1 = new String("abcd");
		String s2 = new String("bcda");
		System.out.println(areAnagram(s1,s2));	
		
	}
	public static void test2()
	{
		String s1 = new String("abcd");
		String s2 = new String("bcdd");
		System.out.println(areAnagram(s1,s2));	
		
	}
	public static void main(String[] args)
	{	
		test1();
		test2();
	}

}
