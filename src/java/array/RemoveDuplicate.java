import java.util.*;
public class RemoveDuplicate {

	/* Leetcode 26
	 * Remove Duplicates from Sorted Array 
	 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
	 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
	 */
	public static int removeDuplicateFromSortedArray(int a[]) {
   		if(a.length == 0) return 0;
   		int j = 0;
   		for(int i = 1; i < a.length; i++){   
			if(a[i] != a[j]) a[j++] = a[i];
   		}
   		return j+1;
	}


	/* 442. Find All Duplicates in an Array
	 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
	 * Find all the elements that appear twice in this array.
	 * Could you do it without extra space and in O(n) runtime?
	 *
	 * Solution: 
	 * 1. the key point is a[i]'s range is [1, n], so if there is no duplicate, we should place a[i] at index a[i]-1 for all a[i]
	 * for example if n = 5, we are able to reorder the array so it becomes [1, 2, 3, 4, 5]
	 * 2. for each a[i], put it at index a[i]-1 by swap a[i] and a[a[i]-1], and set a[a[i]-1] = 0, meaning already found 
	 * a[i] and put it at position a[i]-1
	 * 3. when doing swap at 2, if a[a[i]-1] = 0, meaning in the previous step a[i] is found already, so we encounter a duplicate a[i],
	 * we mark it as -1, add into result list
	 */

	public static List<Integer> findDuplicatesFromFirstNIntegers(int[] nums) {
		final int n = nums.length;
		int i = 0;
		List<Integer> result = new LinkedList<Integer>();
		while(i < n) {
			if(nums[i] == 0 || nums[i] == -1)
				i++;
			else {
				if(nums[nums[i]-1] == 0) {
					result.add(nums[i]);
					nums[i] = -1;
					i++;
				}
				else {
				       	int tmp = nums[i];
					nums[i] = nums[nums[i]-1];
					nums[tmp-1] = 0;
				}
			}
			System.out.println("i " + i + " " + Arrays.toString(nums));
		}
		return result;
	}

	public static void test1() {
   		int[] nums = {0, 1, 1, 2, 3, 8};
   		int length = removeDuplicateFromSortedArray(nums);
   		if(length == 5)
			System.out.println("Test 1 passed.");	
		else
			System.out.println("Test 1 failed.");
	}

	public static void test2() {
		int[] nums2 = {4, 3, 2, 7, 8, 2, 3, 1};
		List<Integer> result = findDuplicatesFromFirstNIntegers(nums2);
		List<Integer> ref1 = new LinkedList<Integer>(Arrays.asList(2,3));
		List<Integer> ref2 = new LinkedList<Integer>(Arrays.asList(3,2));
		System.out.println(result.toString());
		if(result.equals(ref1) || result.equals(ref2))
			System.out.println("Test 2 passed.");
		else 	
			System.out.println("Test 2 failed.");
	}

	public static void test3() {
		int[] nums3 = {1, 1, 3};
		List<Integer> result = findDuplicatesFromFirstNIntegers(nums3);
		List<Integer> ref1 = new LinkedList<Integer>(Arrays.asList(1,1));
		if(result.equals(ref1))
			System.out.println("Test 3 passed.");
		else 	
			System.out.println("Test 3 failed.");
	}

	public static void main(String[] args) {
		test1();
		test2();
		test3();
		return;
	}
}

