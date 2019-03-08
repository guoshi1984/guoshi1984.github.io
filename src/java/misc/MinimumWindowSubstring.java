import java.util.*;
public class MinimumWindowSubstring
{
	public static String find(String s, String t)
	{
		List<Character> charList = getCharList(t);	
		
		Map<Character, Integer> charPos = new HashMap<Character, Integer>();
		String result = new String();
		for(int i=0; i< s.length(); i++)
		{
			if(charList.contains(s.charAt(i)))
			{
				charPos.put(s.charAt(i), i);
				result=	updateMin(charPos, charList, s);
			}
		}
		return result;
	}

	private static List<Character> getCharList(String t)
	{
		List<Character> charList = new ArrayList<Character>();
		for(int i=0; i< t.length(); i++)
		{
			charList.add(t.charAt(i));
		}
		return charList;

	}
	
	private static String updateMin(Map<Character, Integer> charPos, List<Character> charList, String s)
	{
		
		for( char c : charList)
		{
			if(!charPos.containsKey(c))
			return new String("");
		}
		
		int max = Collections.max(charPos.values());
		int min = Collections.min(charPos.values());
		
		return s.substring(min, max+1);
	} 
	
	public static void main(String[] args)
	{
		String result = find("ADOBECODEBANC", "ABE");
		System.out.println(result);
	}
			
	
}
