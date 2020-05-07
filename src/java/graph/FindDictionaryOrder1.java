import java.util.*;

/* LeetCode 269
 * Alien Dictionary
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules
 * of this new language. Derive the order of letters in this language.
 * */
class FindDictionaryOrder1
{

	public static void buildGraph(String[] words, Map<Character, List<Character>> graph)
	{
		for(int i = 0; i<words.length-1; i++){
			String w1 = words[i];
			String w2 = words[i+1];
			int j = 0;
			while(j < Math.min(w1.length(), w2.length())){
				if(w1.charAt(j) != w2.charAt(j)){
					//graph add node w1[j] , w1[j] addAdj w2[j]
					if(!graph.containsKey(w1.charAt(j))){
						graph.put(w1.charAt(j), new LinkedList<Character>());
					}
											
					if(!graph.containsKey(w2.charAt(j))){
						graph.put(w2.charAt(j), new LinkedList<Character>());
					}
					graph.get(w1.charAt(j)).add(w2.charAt(j));
					break;
				}
				else{
					j++;
				}

			}
		}
	}
	
	private static void find(Map<Character, List<Character>> graph, Character c, Set<Character> visited,
                LinkedList<Character> result)
        {
                visited.add(c);
                System.out.println("current char "+ c);
                for(Character thisC : graph.get(c))
                {
                        if(!visited.contains(thisC))
                                 find(graph, thisC, visited, result);
                }
                System.out.println("add "+ c);
                result.offerFirst(c);


        }

        public static List<Character> find(String[] words)
        {
		Map<Character, List<Character>> graph = new HashMap<Character, List<Character>>();
		buildGraph(words, graph);
		System.out.println(graph.size());
		System.out.println(graph.keySet());
	
		Set<Character> visited = new HashSet<Character>();
                LinkedList<Character> result = new LinkedList<Character>();
                for(Character c : graph.keySet())
                {
                        if(!visited.contains(c))
                                find(graph, c, visited, result);
                }

                for(Character c : result)
                {
                        System.out.println("result "+ c);
                }
                return result;
        }


	public static void main(String[] args)
	{
		String[] words1 = {"wrt",
  			"wrf",
  			"er",
  			"ett",
  			"rftt"};
		find(words1);
		String[] words2 = {
			"here",
			"hero",
			"rho",
			"tee",
			"there",
  			"toe",
			"tree"};
		find(words2);
		return;
	}

}
