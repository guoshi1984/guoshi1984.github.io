import java.util.*;
/* Leetcode 273
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 */
public class IntegerToWords
{
	public static String convert(int num)
	{
		int result = 0;
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> map1000 = new HashMap<Integer, String>();
		map1000.put(3, new String("Billion"));
		map1000.put(2, new String("Million"));
		map1000.put(1, new String("Thousand"));
		for(int i = 3; i > 0; i-- )
		{
			result = num/(int)Math.pow(1000, i);
			num = num%(int)Math.pow(1000, i);
			if(result != 0)  //
			{
				sb.append(convertUnder1000(result)).append(map1000.get(i)).append(" ");
			}
		}
		if(num != 0)
			sb.append(convertUnder1000(num));
		return sb.toString();
	}

	public static String convertUnder1000(int num)
	{
		int result = 0;
                int residual = num;
		StringBuilder sb = new StringBuilder();
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(0, "Zero");
		map.put(1, "One");
		map.put(2, "Two");		
		map.put(3, "Three");		
		map.put(4, "Four");		
		map.put(5, "Five");		
		map.put(6, "Six");		
		map.put(7, "Seven");		
		map.put(8, "Eight");		
		map.put(9, "Nine");		
		map.put(10, "Ten");
		map.put(11, "Eleven");
		map.put(12, "Twelve");		
		map.put(13, "Thirteen");		
		map.put(14, "Fourteen");		
		map.put(15, "Fifteen");		
		map.put(16, "Sixteen");		
		map.put(17, "Seventeen");		
		map.put(18, "Eightenn");		
		map.put(19, "Nineteen");
		map.put(20, "Twenty");		
		map.put(30, "Thirty");
		map.put(40, "Forty");
		map.put(50, "Fifty");
		map.put(60, "Sixty");
		map.put(70, "Seventy");
		map.put(80, "Eighty");
		map.put(90, "Ninety");
                if(residual > 100) {
                        result = residual/100;
			sb.append(map.get(result)).append(" ").append("Hundred").append(" ");
                        residual = residual%100;
                }
		if(residual> 20) {
			result = residual/10;
			sb.append(map.get(result*10)).append(" ");
			residual = residual%10;
		}
		sb.append(map.get(residual)).append(" ");
		return sb.toString();
	}

	public static void main(String[] args)
	{
		System.out.println("0 " + convert(0));
		System.out.println("9 " + convert(9));
		System.out.println("19 " + convert(18));
		System.out.println("2345 " + convert(2345));
		System.out.println("10000 " + convert(10000));
		System.out.println("1643922345 " + convert(1643922345));
	}
}
