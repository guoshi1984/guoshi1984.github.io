import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
public class IsomorphicString
{
	public static boolean check(String s1, String s2)
	{
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		int[] array1 = buildArray(c1); 
		int[] array2 = buildArray(c2);
		return Arrays.equals(array1, array2); 
	}
	public static int[] buildArray(char[] c1)
	{	
		Map<Character, Integer> cToInt
		= new HashMap<Character, Integer>();
		int value = 1;
		int[] cToIntArray = new int[c1.length];
		for(int i=0; i< c1.length; i++)
		{
			if(!cToInt.containsKey(c1[i]))
			{
				cToInt.put(c1[i], value++);
				cToIntArray[i]=value;
			}
			else
			{
				cToIntArray[i]=cToInt.get(c1[i]);
			}
		}
		return cToIntArray;
	}
	public static void main(String[] args)
	{
		String s1 = new String("paperb");
		String s2 = new String("titlea");
		System.out.println(check(s1,s2));
	}
	
}
