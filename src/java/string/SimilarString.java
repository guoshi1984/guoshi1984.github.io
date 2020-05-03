import java.util.*;
/*839. Similar String Groups
 * Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y. Also two strings X and Y are similar if they are equal.
 * For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".
 * Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  
 * Notice that "tars" and "arts" are in the same group even though they are not similar.  
 * Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.
 * We are given a list A of strings.  Every string in A is an anagram of every other string in A.  How many groups are there?
 * Example 1:
 * Input: A = ["tars","rats","arts","star"]
 * Output: 2
 */ 
public class SimilarString
{
	public static int getNumberOfGroup(String[] strings)
	{
		int[] grouped = new int[strings.length];
		int count = 0;
		Set<String> visited = new HashSet<String>();
		for(int i = 0; i < strings.length; i++){
			if(!visited.contains(strings[i])){
				visited.add(strings[i]);
				count++;
				System.out.println("start "+ strings[i]);
				find(strings, i, visited);	
			}
		}
		return count;
	}

	public static boolean isSimilar(String s1, String s2)
	{
			int ndiff = 0;
			for(int i = 0; i < s1.length(); i++){
				if(s1.charAt(i) != s2.charAt(i)){
					ndiff++;
					if(ndiff > 2) {
						return false;
					}
				}
			}
			return true;
							
	}

	public static void find(String[] strings, int start, Set<String> visited)
	{
		for(int i = start + 1; i< strings.length; i++) {
			if(isSimilar(strings[start], strings[i])) {
				System.out.println("find "+ strings[start] + " "+ strings[i]);
				start = i;
				visited.add(strings[i]);
			}
		}
	
	}


	public static void find2(String[] strings, int start, Set<String> visited)
	{
			if(!visited.contains(strings[start])) return;
			visited.add(string[start]);
			for(int i = 0; i< strings.length; i++) {
				if(isSimilar(strings[start], strings[i])) {
				find2(strings, i, visited);
			}
		}
	
	}

	public static void main(String[] args)
	{
		//String s1 = new String("tars");
		//String s2 = new String("rats");
		//System.out.println(isSimilar(s1, s2));
		String[] strings = { "tars", "rats", "arts", "star"};
		System.out.println(getNumberOfGroup(strings));
	}
}
