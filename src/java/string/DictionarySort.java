import java.util.*;
public class DictionarySort 
{

	/* Leetcode 953. Verifying an Alien Dictionary
	 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. 
	 * The order of the alphabet is some permutation of lowercase letters.
	 * Given a sequence of words written in the alien language, and the order of the alphabet, 
	 * return true if and only if the given words are sorted lexicographicaly in this alien language.
	 */
	public static void sort(String[] words, String order)
	{
		 Arrays.sort(words, new LexiComparator(order)); 
	}
	
	public static boolean isSorted(String[] words, String order)
	{
		for(int i=0; i< words.length - 1; i++){
			String word1 = words[i];
			String word2 = words[i+1];
			LexiComparator lexiComp = new LexiComparator(order);
			if(lexiComp.compare(word1, word2) > 0) return false;
		}
		return true;
	}


	// given a string that contains the lexicographical order, implement the comparator. The order that
	// characters appear in the string named "order" should be the order that is used to sort an abitrary
	// string.
	//
	//
	public static class LexiComparator implements Comparator<String>
	{
		// Constructor
		// @param order, the string that is the order of the characters used to compare
		public LexiComparator(String order)
		{
			this.order = order;
		}

		// Method to compare two strings
		// 1. for each character with the same index in two strings, if they are the same continue
		// to the character of the next index. For example, "apple" and "alpha" with first character
		// "a" the same.
		// 2. for each character with the same index in two strings, compare based on the order 
		// string, return -1 if they come with same order as in the string "order", return -1 
		// if they come with opposite order as in the string "order".
		// 3. if one string is a prefix of the other, then compare the length, return -1 if first
		// string is shorter, 1 otherwise.
		// 4. if two string are identical, return 0.
		//
		// Time complexity analysisO(1)
		//
		// The probability when two characters are the same is 26*1/26*1/26 = 1/26
		// ........................................different is 25/26
		// We need one iteration when the first characters from two strings are different, the
		// probability is 25/26
		//
		// We need two iterations when the first characters from two string are the same, then the
		// second characters are different, the probability is 1/26 * 25/26
		// 
		// If we keep going, the expectation value of the iteration is
		// E = 25/26 * 1 + 1/26 *25/26 * 2 + 1/26^(n-1) 25/26 * n
		//
		// Based on the formula
		// \frac{1}{(1-x)^2} = 1 + 2x^1 + ... + nx^(n-1) and let x = 1/26
		// E = 25/26(1/(1-1/26)^2) = 26/25, which is constant.
		// 
		//
		// @return -1, 0, 1 based on the method above
		//
		@Override
		public int compare(String str1, String str2)
		{
			Map<Character, Integer> charIndex = new HashMap<Character, Integer>();
			for(int i=0; i<order.length(); i++){
				charIndex.put(order.charAt(i), i);	
			}
			for (int i = 0; i < Math.min(str1.length(), str2.length()); i++) { 
            			if (charIndex.get(str1.charAt(i)) ==  
                				charIndex.get(str2.charAt(i))) { 
                			continue; 
            			}	  
            			else { 
                			return charIndex.get(str1.charAt(i)) -  
                    				charIndex.get(str2.charAt(i)); 
            			}		 
        		} 
  
        		// Edge case for strings like 
        		// String 1="Geeky" and String 2="Geekyguy" 
        		if (str1.length() != str2.length()) { 
            			return (str1.length()-str2.length()); 
        		}  
        		// If none of the above conditions is true, 
        		// it implies both the strings are equal 
        		else { 
            			return 0; 
        		} 
		}
		public String order;
	}

	public static void main(String[] args)
	{
		String[] words1 = new String[]{"int","integer"};
	       	String order1 = "hlabcdefgijkmnopqrstuvwxyz";
		boolean isSorted1 = isSorted(words1, order1);
		System.out.println(isSorted1);	
		String[] words2 = new String[]{"word","world","row"};
	       	String order2 = "worldabcefghijkmnpqstuvxyz";
		boolean isSorted2 = isSorted(words2, order2);
		System.out.println(isSorted2);	
		String[] words3 = new String[]{"apple","app"};
	       	String order3 = "abcdefghijklmnopqrstuvwxyz";
		boolean isSorted3 = isSorted(words3, order3);
		System.out.println(isSorted3);	
		
		String[] words4 = new String[]{"","world","wolf", "row"};
	       	String order4 = "worldabcefghijkmnpqstuvxyz";
		boolean isSorted4 = isSorted(words4, order4);
		System.out.println(isSorted4);	
		//for(String word: words)
		{	
		//	System.out.println(word);
		}	
	}
}
