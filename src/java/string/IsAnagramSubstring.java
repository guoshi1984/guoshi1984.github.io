/*Given two strings, check if one string is anagram substring of the other.
 */

public class IsAnagramSubstring{
	public static boolean isAnagram(String s, String t)
	{
		int[] alphabet = new int[26];
		for(int i = 0; i < s.length(); i++) alphabet[s.charAt(i)-'a']++;
		for(int i = 0; i < t.length(); i++) alphabet[t.charAt(i)-'a']--;
		for (int i : alphabet) if(i != 0) return false; 
		return true;
	}

	public static boolean isAnagramSubstring(String ss, String ls)
	{
		if(ss.length() > ls.length()) return isAnagramSubstring(ls, ss);
		for(int i=0; i < ls.length() - ss.length()+1; i++ )
		{
			String tmpS = ls.substring(i, i+ss.length());
			System.out.println(tmpS);
			if(isAnagram(ss, tmpS)) return true;
		}
		return false;
	}
	public static void main(String[] args)
	{
		String s = new String("ad");
		String t = new String("bcda");
		System.out.println(isAnagramSubstring(s,t));
	}
}
