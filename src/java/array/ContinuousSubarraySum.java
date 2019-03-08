public class ContinuousSubarraySum
{
	public static boolean check(int[] nums, int k)
	{
		final int n = nums.length;
		int cur = nums[0];
		int i = 0;
		int j = 0;
		
		while(true)
		{
			System.out.println(cur + " "+ i + " "+ j);
			if(cur == k) return true;
			else if(cur < k)
			{	
				if(j == n-1) return false;
				j++;
				cur = cur + nums[j]; 
			}
			else
			{	
				if(i == n-1) return false;
				cur = cur - nums[i];
				i++;
			}
			
		}
		
	}
	
	public static void main(String[] args)
	{
		int[] nums = {23, 2, 6, 4, 7};
		int k = 13;
		System.out.println(check(nums, k));
	}
}
