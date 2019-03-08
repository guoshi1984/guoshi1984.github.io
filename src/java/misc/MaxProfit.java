import java.lang.Math;
public class MaxProfit
{
	public static int find(int[] prices)
	{
		int result = 0;
		int min = Integer.MAX_VALUE;
		for(int i=0; i< prices.length; i++)
		{
			if((prices[i] -  min) > result)
			{
				result = prices[i]-min;
			}
			if(prices[i] < min) 
			{	
				min = prices[i];
			}
			
		}
		return result;
	}
	public static void main(String[] args)
	{
		int[] prices = {7, 2, 5, 3, 6, 5};
		System.out.println(find(prices));
	}

}	
