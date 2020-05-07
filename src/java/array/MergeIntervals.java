import java.util.*;

/* Leetcode 56 Merge Intervals
 * Given a collection of intervals, merge all overlapping intervals.
 * Example 1:
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * 
 * Solutions:
 * First, sort the intervals by lower bound.
 * for an inteval, if its lower bound is bigger than its previous interval's upper bound, 
 * then do nothing, push the previous interval to the result
 *
 * if its lower bound is smaller than the upper bound of its previous one, change the current interval 
 * so that it covers its previous. Use previous's lower bound as the new lower bound, the maximum of
 * its previous and itself's upper bound as the upper bound.
 */

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
