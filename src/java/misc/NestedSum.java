import java.util.List;
import java.util.LinkedList;
public class NestedSum
{
	public static class NestedInteger
	{
		Integer data;
		List<NestedInteger> list;
		boolean isInteger;	
		NestedInteger(Integer data)
		{
			this.data = data;
			this.list = null;
			isInteger = true;
		}

		NestedInteger(List<NestedInteger> list)
		{
			this.list = list;
			this.data = null;
			isInteger = false;
		}

		public boolean isInteger()
		{
			return isInteger;
		}
		public Integer getInteger()
		{
			if(isInteger) 
			{
				return data;
			}
			else 
			{ 	
				return null;
			}
		}
		public List<NestedInteger> getList()
		{
			if(isInteger) return null;
			else return this.list;
		}	
			
	}
	public static int calcSumPerLevel(NestedInteger ni, int level)
	{
		int sum =0;  
		
		if(ni.isInteger)
		{
			sum =ni.getInteger()*level;
		}
		else
		{
			for(NestedInteger ni1 : ni.getList())
			{
				sum += calcSumPerLevel(ni1, level+1);
			} 
		}
		return sum;
	}	
	public static int calcSum(NestedInteger ni)
	{
		return calcSumPerLevel(ni, 0);
	}
	public static void main(String[] args)
	{
		List<NestedInteger> nil0 = new LinkedList<NestedInteger>(); 
		List<NestedInteger> nil11 = new LinkedList<NestedInteger>(); 
		List<NestedInteger> nil12 = new LinkedList<NestedInteger>();
		nil11.add(new NestedInteger(1)); 
		nil11.add(new NestedInteger(1)); 
		nil12.add(new NestedInteger(1)); 
		nil12.add(new NestedInteger(1));
		nil0.add(new NestedInteger(nil11));
		nil0.add(new NestedInteger(2));
		nil0.add(new NestedInteger(nil12));
		NestedInteger ni0 = new NestedInteger(nil0);
		System.out.println(calcSum(ni0));		
	}
}
