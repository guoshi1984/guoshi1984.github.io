import java.util.*;
public class MergeIntervals
{

	public static int[][] merge(int[][] intervals)
	{
		List<int[]> resultList = new LinkedList<int[]>();
		Arrays.sort(intervals, new Comparator<int[]>(){
			@Override
		        public int compare(int[] ints1, int[] ints2)
			{
				return ints1[0] - ints2[0];
			}
		});

		for(int i = 0; i< intervals.length -1; i++) {
			if(intervals[i+1][0] > intervals[i][1]) {
				resultList.add(intervals[i]);
			}
			else {
				intervals[i+1][0] = intervals[i][0];
				intervals[i+1][1] = Math.max(intervals[i][1], intervals[i+1][1]);
			}
		}
		
		resultList.add(intervals[intervals.length - 1]);
		int[][] resultArray = new int[resultList.size()][];
		for (int i =0; i < resultList.size(); i++) {
			resultArray[i] = resultList.get(i);
			System.out.println(Arrays.toString(resultArray[i]));
		}
		return resultArray;
	}

	public static void main(String[] args)
	{
		int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
		int[][] result1 = merge(intervals1);

		int[][] intervals2 = {{1, 4}, {4, 5}};
		int[][] result2 = merge(intervals2);
		
		int[][] intervals3 = {{1, 6}, {4, 5}, {7, 8}};
		int[][] result3 = merge(intervals3);
	}
}
