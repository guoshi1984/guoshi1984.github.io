import java.util.*;
public class CountCharacters
{
	public static Map<Character, Integer> count(String s)
	{
		final int len = s.length();
		Map<Character, Integer> numChars = 
		new HashMap<Character, Integer>(Math.min(len,26));
		for(int i=0; i<len; i++)
		{
			char c = s.charAt(i);
			if(!numChars.containsKey(c))
			{
				numChars.put(c, 1);
			}
			else
			{
				numChars.put(c, numChars.get(c) +1);
			}
		}
		return numChars;	
		
	}
	
	public static void main(String[] args)
	{
		String s = new String("abcd");
		Map<Character, Integer> numChars = count(s);
	}
}
