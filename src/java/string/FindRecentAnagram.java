import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class FindRecentAnagram
{
	public static void find(String[] strings, int index)
	{
		Map<String, String> anagramMap = 
		new HashMap<String, String>();
		for(String s:strings)
		{	
			char[] chars = s.toCharArray();
			Arrays.sort(chars);
			String keyString = new String(chars);
			if(anagramMap.containsKey(keyString))
			{ 
				System.out.println(
				"most recent anagram of "+ s 
				+ " is " +anagramMap.get(keyString));
			}
			anagramMap.put(keyString, s);
		}
		//return true;	
	}
	public static void main(String[] args)
	{	
		String[] s = { "abcd", "bcda", "hfi", "fiu" ,"ifh"};
		find(s,0);	
	}

}
