import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
public class IsomorphicString2
{
	public static boolean check(String s1, String s2)
	{	
		Map<Character, Character> cToC
		= new HashMap<Character, Character>();
		Set<Character> c2Set
		= new HashSet<Character>();

		if(s1.length() != s2.length()) return false;
		char[] c1 = s1.toCharArray();
		
		char[] c2 = s2.toCharArray();
		for(int i = 0; i< c1.length; i++)
		{
			if(!cToC.containsKey(c1[i]))
			{
				if(c2Set.contains(c2[i])) 
				{	
					return false;
				}
				else 
				{
					cToC.put(c1[i], c2[i]);
					c2Set.add(c2[i]);
				}
			}
			else
			{	
				if(cToC.get(c1[i]) != c2[i]) return false;
			}
		}
		return true;
	}
	public static void main(String[] args)
	{
		String s1 = new String("begin");
		String s2 = new String("desin");
		System.out.println(check(s1,s2));
	}
	
}
