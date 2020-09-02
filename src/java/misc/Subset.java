import java.lang.*;
import java.util.List;
import java.util.LinkedList;
/* Leetcode 78 SubSets
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 */
public class Subset
{
	public static List<List<Integer>> find(int[] nums)
	{
		final int n = nums.length;
		List<List<Integer>> lsls = new LinkedList<List<Integer>>();
		for(int i = 0; i< Math.pow(2, n); i++)
		{
			String s = Integer.toBinaryString(i);
			System.out.println(s + "\n");
			List<Integer> ls = new LinkedList<Integer>();
			for(int j = 0; j< s.length(); j++ )
			{
				if(s.charAt(j) == '1')
					ls.add(nums[s.length()-1-j]);
			}
			lsls.add(ls);

		}
		return lsls;
	}
	
	public static void main(String[] args)
	{
		int[] nums = {1, 2, 3};
		List<List<Integer>> result = find(nums);
		System.out.println(result);
	}
}
