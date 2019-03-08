public class MaxSubArray{
	public static int find(int[] a)
	{
		int result = a[0];
		int tmp=a[0];
		for(int i=1; i<a.length; i++)
		{
			// if the previous sum < 0,
 			// then new sum count start from a[i]
			// if the previous sum > 0;
			// then continue to add a[i] in the sum
			tmp = Math.max(tmp+a[i], a[i]);
			result = Math.max(tmp, result); 	
		}
		return result;
	}
	public static void main(String[] args)
	{
		int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

	}

}
