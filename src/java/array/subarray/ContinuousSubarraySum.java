import java.util.*;

public class ContinuousSubarraySum
{
	/* Leetcode 523 
 	* Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 
	* that sums up to the 
	* multiple of k, that is, sums up to n*k where n is also an integer.
 	* 
 	* Solution:
 	* (sum 1 to i) -(sum 1 to j) %k = 0, then (sum i+1 to j) is a multiple of k, 
 	* so we keep track of value (sum 1 to i)%k for each element in the array
 	*/
	public static boolean find(int[] nums, int k)
	{
		Map<Integer, Integer> map = new HashMap<Integer,
		Integer>(){{put(0, -1);}};
	
		int runningSum = 0;
		for(int i=0; i<nums.length; i++)
		{
			runningSum += nums[i];
			if(k != 0) runningSum %= k;
			Integer prev = map.get(runningSum);
			if(prev != null)
				if(i - prev > 1) return true;
			else map.put(runningSum, i);
		
		}
		return false;
	}


	/* Leetcode 560
	 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
	 *
	 * This problem differ the above problem in two ways: first the length of the subarray does not have to be at least two.
	 * Second, we need to return the number of subarrays, not just true or false/
	 *
	 * Solution:
	 * We still keep track of the (sum up to i)%k, but also number of times this residual appears before. 
	 * For each number, the number of subarray that ends at this number is the number of times the same residual (sum up to i)%k appears before.
	 */
	public static int getCount(int[] nums, int k)
        {
                Map<Integer, Integer> map = new HashMap<Integer,
                Integer>(){{put(0, 1);}};

                int runningSum = 0;
                int count = 0;
                for(int i=0; i<nums.length; i++)
                {
                        runningSum += nums[i];
                        if(k != 0) {
				runningSum %= k;
			}
                        Integer prev = map.get(runningSum);
                        if(prev != null)
                                        count++;
                        else map.put(runningSum, map.getOrDefault(runningSum, 0)+1);

                }
                return count;
        }

	
	public static void main(String[] args)
	{
		int[] nums = {23, 2, 4, 6, 7};
		System.out.println(find(nums, 6));	
		System.out.println(getCount(nums, 6));	
	}
}
