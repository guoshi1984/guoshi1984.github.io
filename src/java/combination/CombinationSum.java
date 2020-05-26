import java.util.*;
/* Leetcode 39
 * Given a set of candidate numbers (candidates) (without duplicates) 
 * and a target number (target), find all unique combinations in candidates 
 * where the candidate numbers sums to target.
 * The same repeated number may be chosen from candidates unlimited number of times.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is
 * [[7],[2,2,3]]
 * Example 2:
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [[2,2,2,2],[2,3,3],[3,5]]
 *
 * 40. Combination Sum II
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used once in the combination.
 * The solution set must not contain duplicate combination/
 * */
class CombinationSum
{

	public static List<List<Integer>> findUsingDfs(int[] numbers, int target)
	{
		List<List<Integer>> results = new LinkedList<List<Integer>>();
		List<Integer> current = new LinkedList<Integer>();
		int level = 0;
		find(numbers, target, 0, current, results, level);
		return results;

	}
	public static void find(int[] numbers, int target, int start, List<Integer> current, List<List<Integer>> results, int level )
	{ 
		System.out.println(writeIndent(level) + "entering level " + level);	
		System.out.println(writeIndent(level) + "target "+ target);
		System.out.println(writeIndent(level) + "current "+ current);

      		if(target == 0 ){ 
         		results.add(new LinkedList<Integer>(current));
			System.out.println(writeIndent(level) + "find and exiting level "+ level);
         				
			return;
     		}

    		for(int i=start; i< numbers.length; i++){
      			if(numbers[i] <= target){ 
        			current.add(numbers[i]);
        			
				find(numbers, target-numbers[i], 
						i + 1, // i if allowing duplicate  
						// i+1 if not allowing duplicate
						current, results, level + 1);
        			current.remove(current.size()-1);
      			}
      			else { }
    		}
		System.out.println(writeIndent(level) + "exiting level "+ level);
		System.out.println(writeIndent(level) + "target "+ target);
		System.out.println(writeIndent(level) + "current "+ current);
	}

	public static String writeIndent(int level)
	{
		String indent = new String("");
		for(int i = 0; i < level; i++)
		{
			indent +="\t";
		}
		return indent;
	}

	public static List<List<Integer>> findUsingEnum(int[] numbers, int target)
	{
		
		int[] max = new int[numbers.length];
		for(int i = 0; i< numbers.length; i++)
		{	
			max[i] = target/numbers[i];
		}
		int[] current = new int[numbers.length];
		Arrays.fill(current, 0);
		boolean finished = false;
		int currentSum = 0;
		List<List<Integer>> results = new LinkedList<List<Integer>>();
		while(!finished)
		{
			current = generateNext(max, current, numbers.length);
			currentSum = sum(current, numbers);
			if(currentSum == target){
				add(results, current, numbers);
			}
			finished = checkFinish(max, current, numbers.length);

		}
		return results;
	        	
	}
	
	public static int[] generateNext(int[] max, int[] current, int length)
	{
		//check length
		int addition = 0;
		for(int i = 0; i<length; i++)
		{
			if(current[i]+1 <= max[i]){
			       	current[i] = current[i] + (i==0? 1 : addition);
				break;
			}
			else {
				current[i] = 0;
				addition = 1;
			}

		}
		System.out.println(Arrays.toString(current));
		return current;
	}

	public static int sum(int[] current, int[] numbers)
	{
		int sum = 0;
		for(int i = 0; i< current.length; i++)
		{
			sum += current[i]*numbers[i];
		}

		return sum;
	}
	
	public static void add(List<List<Integer>> results, int[] current, int[] numbers)
	{
		List<Integer> result = new LinkedList<Integer>();

		for(int i = 0; i< current.length; i++)
		{
			for(int j = 1; j <= current[i]; j++)
			{
				result.add(numbers[i]);
			}
		}
		results.add(result);

	}

	public static boolean checkFinish(int[] max, int[] current, int length)
	{
		boolean finished = true;
		{
			for(int i = 0; i < length; i++){
				if(current[i] < max[i])
					return false;
			}

		}
		return true;
	}

	public static void main(String[] args)
	{
		int[] numbers = {1,2,4};
		int target = 3;
		List<List<Integer>> results;
		results = findUsingEnum(numbers, target);
		System.out.println(results);
		results = findUsingDfs(numbers, target);
		System.out.println(results);

	}
}
