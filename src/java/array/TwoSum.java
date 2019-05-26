package array;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
/* Problem:
 * Given a array a[] and a number target, write a function that returns indices of a pair in a[] 
 * whose sum equals to target.
 */
public class TwoSum
{
	/* Analysis: for each element, we would like to find if target-element exists. We need something
	 * which can do fast search, a optimal choice would be hashset or hashmap. 
	 * 
	 * Search method: one pass from left to right
	 *
	 * Additional data structure: for each element in the array, use a map to store num[i] as
	 * key, and index as value. So for each element, look if target - num[i] is in the map, if so
	 * return the indice of the both.
	 */
	public static int[] find(int[] nums, int target)
	{
		Map<Integer, Integer> mp = 
		new HashMap<Integer, Integer>();
		int[] result = new int[2];
		for(int i=0 ; i< nums.length; i++)
		{	
			Integer diff = (Integer)(target - nums[i]);
			if(mp.containsKey(diff))
			{
				result[0] = mp.get(target - nums[i]);
				result[1] = i;
				return result;
			}	
			else
			{
				mp.put(nums[i], i);
			}
		}
		return result;
	}

	public static void test1()
	{
		System.out.println(Arrays.toString(find(ArrayUtils.array1, 7)));

	}
	public static void test2()
	{
		System.out.println(Arrays.toString(find(ArrayUtils.array1, 8)));

	}
	public static void main(String[] args)
	{
		test1();

	}
}
