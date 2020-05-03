import java.util.*;
/* LeetCode 791
 * Sort a string according to the order defined by another string
 * Given two strings (of lowercase letters), a pattern and a string. The task is to sort string according to the order defined by pattern. 
 * It may be assumed that pattern has all characters of the string and all characters in pattern appear only once.
 */
public class CharacterSort
{

	public static String CharacterSort(char[] str, char[] pat) 
    	{	 
    		// Create a count array stor 
        	// count of characters in str. 
        	int count[] = new int[26]; 
  
        	// Count number of occurrences of 
        	// each character in str. 
        	for (int i = 0; i < str.length; i++) { 
        		count[str[i] - 'a']++; 
        	} 
  
        	// Traverse the pattern and print every characters 
        	// same number of times as it appears in str. This 
        	// loop takes O(m + n) time where m is length of 
        	// pattern and n is length of str. 
        	int index = 0; 
        	for (int i = 0; i < pat.length; i++) { 
            		for (int j = 0; j < count[pat[i] - 'a']; j++) { 
                		str[index++] = pat[i]; 
            		} 
        	}
	        return new String(str);	
    	}

	public static void main(String[] args)
	{
		String pattern = new String("dcba");
		String target = new String("aababcbdb");
		
		String result = CharacterSort(target.toCharArray(), pattern.toCharArray());
		System.out.println(result);
		return;
	}

}
