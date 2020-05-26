import java.util.*;
public class NearestCombination
{

	public static class Pair implements Comparable<Pair>
	{
		Pair(int i, int j)
		{
			this.i = i;
			this.j = j;
		}

		int getDist()
		{
			return i*i + j*j;
		}
		
		@Override
		public int compareTo(Pair p)
		{
			return this.getDist() - p.getDist();
		}

		@Override
		public String toString()
		{
			return new String("[" + i +", " + j + "]");
		}

		int i; 
		int j;

	}

	public static void find(int n)
	{
		List<Pair> list = new LinkedList<Pair>();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				list.add(new Pair(i, j));
			}

		}

		Collections.sort(list);
		System.out.println(list);
	}


	public static void main(String[] args)
	{
		find(5);
	}
}
