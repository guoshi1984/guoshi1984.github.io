public class OnlyOnce
{
	public static int find(int[] array)
	{
		int result = 0;
		for(int i : array)
		{
			result = result ^ i;
		}
		return result;
	}
	public static void main(String[] args)
	{
		int[] array = {3, 4, 5, 4, 3};
		System.out.println(find(array));
	}
}
