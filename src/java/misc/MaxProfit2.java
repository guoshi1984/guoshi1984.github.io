import java.lang.Math;
public class MaxProfit2
{
	public static int find(int[] prices)
	{
		int result = 0;
		int min = Integer.MAX_VALUE;
		for(int i=1; i< prices.length; i++)
		{
			if((prices[i] -  prices[i-1]) > 0)
			{
				result += prices[i] - prices[i-1];
			}
			
		}
		return result;
	}
	public static void main(String[] args)
	{
		int[] prices = {7, 2, 5, 3, 6, 1, 5};
		System.out.println(find(prices));
	}

}	
