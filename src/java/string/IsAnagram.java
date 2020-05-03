/* Leetcode 242
 * Given two string s and t, write a function to determine
 * if t is an anagram of s.
 */
public class IsAnagram{
	public static boolean isAnagram(String s, String t)
	{
		int[] alphabet = new int[26];
		for(int i = 0; i < s.length(); i++) alphabet[s.charAt(i)-'a']++;
		for(int i = 0; i < t.length(); i++) alphabet[t.charAt(i)-'a']--;
		for (int i : alphabet) if(i != 0) return false; 
		return true;
	}

	public static void main(String[] args)
	{
		String s = new String("abcd");
		String t = new String("bcda");
		System.out.println(isAnagram(s,t));
	}
}
