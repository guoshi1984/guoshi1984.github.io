import java.util.*;
/* Leetcode 239 Sliding Window Maximum
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position. 
 * Return the max sliding window.
 *
 * Solution: 
 * A naive solution is to have a double loop, one loop is through the k elememnts in the sliding window, 
 * for each sliding window, there is a another loop through the whole array.
 *
 * We can find a better solution. Think about the array size is 100, and k = 99. We need
 * max for the subarray from the 2nd element to the 99th element, and let's call max[2, 99]. And we can find
 * this by only one loop. 
 * And the max from 1st to 99th elements max[1, 99] would be max(num[1], max[2,99]), the max
 * from 2nd to 100th elements max[2, 99] would be max(max[2,99], num[100]).
 * 
 * we partition the array to groups, each group has k element.
 * we define two additional arrays call leftMax and rightMax
 * for an index i, leftMax is max number from i%k to i. For example, let k =3 
 * leftMax[1] max[1]
 * leftMax[2] max[1,2]
 * leftMax[3] max[1,2,3]
 * leftMax[4] max[4]
 * leftMax[5] max[4,5]
 * 
 * rightMax[1] = max[1,2,3]
 * rightMax[2] = max[2,3]
 * rightMax[3] = max[3]
 * rightMax[4] = max[4, 5, 6]
 *
 * max[1-3] = leftMax[1,2,3]
 * max[2-4] = max( rightMax[2,3] leftMax[4] )
 *
 * 
 *
 *
public class SlidingWindowMaximum {
	public static int[] findMax(int[] nums, int k) {
		final int n = nums.length;
		int[] leftMax = new int[n];
		int[] rightMax = new int[n];
		int leftTmpMax = Integer.MIN_VALUE;
		int rightTmpMax = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++) {
			if(i % k == 0)
				leftTmpMax = Integer.MIN_VALUE;
			leftMax[i] = Math.max(leftTmpMax, nums[i]);
			leftTmpMax = leftMax[i];
			if((n-i-1) % k == 0)
				rightTmpMax = Integer.MIN_VALUE; 
			rightMax[n-i-1] = Math.max(rightTmpMax, nums[n-i-1]);
			rightTmpMax = rightMax[n-i-1];

		}
		System.out.println(Arrays.toString(leftMax));
		System.out.println(Arrays.toString(rightMax));
		int [] result = new int[n-k+1];
		for(int i = 0; i < n-k+1; i++) {
			if(i % k == 0)
				result[i] = leftMax[i+2];
			else
				result[i] = Math.max(rightMax[i], leftMax[i+2]);
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
		int k = 3;
		int[] result = findMax(nums, k);
		System.out.println(Arrays.toString(result));
		return;
	}
}

