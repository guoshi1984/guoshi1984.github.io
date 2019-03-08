import java.util.*;
public class WordLadder
{
	private static char[] letters = new char[26];
	private	static int result = 0;
	public static void initializeLetters()
	{
		int i = 0; 
		for(char c  = 'a'; c <= 'z'; c++)
		{
			letters[i] = c;
			i++;
		}
	}
	
	public static String replace(String s, int index, char c)
	{
		StringBuilder sb = new StringBuilder(s);
		sb.setCharAt(index, c);
		return sb.toString();
	}

	public static void search(String begin, String end, 
		List<String> wordList, Stack<String> visited)
	{
		for(int i=0; i<begin.length(); i++)
		{
			for(int j=0; j<26; j++)
			{
				String newWord = replace(begin, i, letters[j]);
				if(wordList.contains(newWord) &&
					visited.search(newWord) ==-1)
				{
					visited.push(newWord);
					System.out.println(
					"word "+ begin+
					" new word "+ newWord);
					System.out.println(visited);
					System.out.println(visited.size());	
					if(newWord.equals(end))
					{

						System.out.println("find ");
						result = result > 0 ?
						Math.min(result, visited.size()) : visited.size();
						visited.pop();
						return;
					}
					else if(visited.size() == wordList.size())
					{
						visited.pop();
						return;
					}
					else
					{
						search(newWord,end,wordList, visited);
					}
					visited.pop();
				}
			}
		}
	}

	public static void main(String[] args)
	{
		String begin = new String("hit");
		String end = new String("cog");
		List<String> wordList = new ArrayList<String>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		initializeLetters();
		Stack<String> visited = new Stack<String>();
		search(begin, end, wordList, visited);
		System.out.println(result);
	}
	
	
}
