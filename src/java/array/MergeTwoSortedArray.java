import java.util.*;
/* Leetcode 88 
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note: The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * Example:
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * Output: [1,2,2,3,5,6]
 */
public class MergeTwoSortedArray
{
	public static void merge(int[] nums1, int[] nums2, int m, int n)
	{
		for(int i = 0; i< nums2.length; i++) {
			nums1[m+i] = nums2[i];
		}

		int i = 0; 
		int j = m;
		while(i < j && j< nums1.length) {
			System.out.println("i: "+ i +" j: " + j);
			if(nums1[i] < nums1[j]) {
				if(i<j-1)
					i++;
				else
					j++;
				System.out.println(Arrays.toString(nums1));

			}
			else {
				int tmp = nums1[j];
				nums1[j] = nums1[i];
				nums1[i] = tmp;
				if(j+1 < nums1.length && nums1[j] > nums1[j+1] )
					j++;
				System.out.println(Arrays.toString(nums1));
			}
		}

	}
	
	public static void merge2(int[] nums1, int[] nums2, int m, int n)
	{
		int ia = m-1;
   		int ib = n-1;
		int index = m + n - 1;
   		while(ia >= 0 && ib >= 0){  
			nums1[index--] = nums1[ia] > nums2[ib]? nums1[ia--]:nums2[ib--];
			System.out.println(Arrays.toString(nums1));  
		}
   		while(ib >= 0){  
			nums1[index--] = nums2[ib--];
   		}

	}

	public static void main(String[] args)
	{
		int[] nums1 = {1, 3, 5, 7, 0, 0, 0, 0};
		int[] nums2 = {2, 4, 6, 8};
		merge2(nums1, nums2, 4, 4);
		System.out.println(Arrays.toString(nums1));
		int[] nums3 = {5, 6, 7, 8, 0, 0, 0, 0};
		int[] nums4 = {1, 2, 3, 4};
		merge2(nums3, nums4, 4, 4);
		System.out.println(Arrays.toString(nums3));
	}

}
