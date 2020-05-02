import java.util.*;
public class ContinuousSubarraySum1
{
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
	
	public static void main(String[] args)
	{
		int[] nums = {23, 2, 6, 4, 7};
		System.out.println(find(nums, 6));	
	}
}
