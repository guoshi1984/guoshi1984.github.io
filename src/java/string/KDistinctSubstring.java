import java.util.*;
/* Given a string, count the number of substrings with exactly k distinct characters
 */
public class KDistinctSubstring{
	public static int find(String string, int k)
	{
		Map<Character, Integer> count = new HashMap<Character, Integer>();
		int resultCount = 0;
		for(int i=0; i<string.length(); i++)
		{
			count.clear();	
			int distinctCount = 0;
			for(int j=i; j<string.length(); j++)
			{
				if(!count.containsKey(string.charAt(j)))
				{
					count.put(string.charAt(j), 1);
					distinctCount++;
				}
				else
				{
					count.put(string.charAt(j), count.get(string.charAt(j))+1);
				}
				
				if(distinctCount==k)
				{
					resultCount++;
					System.out.println(distinctCount);
					System.out.println("i "+i +"j "+j);
				}
				if(distinctCount>k)
				{
					break;
				}
			}
		}
		return resultCount;
	}


	public static int getDistinct(Map<Character, Integer> count)
	{
		int result = 0;
		for(Integer i : count.values())
		{
			if(i>0)
				result++;
		}
		return result;
	}
	public static void main(String[] args)
	{
		String input = "abcbaa";
		System.out.println(find(input, 3));
	}
}
