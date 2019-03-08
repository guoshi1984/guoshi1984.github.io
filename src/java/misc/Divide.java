import java.util.*;
public class Divide
{
	public static int divide(int y, int x)
	{
		//corner case
		if(x == 0) return y>0 ? Integer.MAX_VALUE:
					Integer.MIN_VALUE;
		if(y == 0) return 0; 	
		int i = 0; 
		while(y > (x<<(i+1)))
		{
			i++;
		}
		System.out.println(i);
		
		int residual = y;
		int result = 0;
		for(int j = i; j > 0; j--)
		{
			if(residual >= (x<<j))
			{
				result = result + (1<<j);
				System.out.println("result "+result);
				residual = residual - (x<<j);
				System.out.println("residual "+residual);	
			}
		}
		System.out.println(result);
		return (x*y > 0) ? result: -result; 
	}

	public static void main(String[] args)
	{
		divide(30, 5);	
	}
}
