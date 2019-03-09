import java.util.Stack;
/* You are given a score list which consists of a few numbers and characters,
 * if the character is '+', then add the last two scores to the total,
 * if the character is 'Z', substract the last score from the total,  
 * if the character is 'X', add the twice value of the last score to the total.
 */
public class Baseball{
	public static int ballCount(String[] score)
        {
		if(score == null ||score.length == 0)
        	{
			return 0;
        	}
		Stack<Integer> stack = new Stack<Integer>();
		
		int sum=0;
		for (int i = 0; i < score.length; i++)
 		{
			String curScore = score[i];
			int val = 0;
			if(!stack.isEmpty() && curScore.equals("Z"))
			{	
				val = stack.pop();
				sum -= val;
			}
			else if(!stack.isEmpty() && curScore.equals("X"))
			{
				val = stack.peek()*2;
				sum += val;
				stack.push(val);
			}
			else if(curScore.equals("+"))
			{
				int size = stack.size();
				if(size >= 2)
				{
					val = stack.get(size- 1 )
					    + stack.get(size -2 );
				}
				else if(size == 1)
				{
					val = stack.get(size - 1);
				}
				sum += val;
				stack.push(val);
			}
			else
			{
				val = Integer.parseInt(curScore);
				sum += val;
				stack.push(val);
			}
			System.out.println("sum: " + sum + stack);
		}
		System.out.println(sum);
		return sum;
	}	
	public static void main(String[] args)
	{	
		String[] score= {"3","2","+","3","Z","1","X"};
		ballCount(score);
		
	}
}
