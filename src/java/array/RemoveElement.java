public class RemoveElement {
	/* Leetcode 27 Remove Element
	 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
	 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
	 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
	 */
	public static int remove(int a[], int n, int target) {
  		int index = 0;
  		for(int i=0; i<n; i++){  
			if(a[i]!=target) { 
				a[index++]=a[i];
     			}
  		}
  		return index;
	}

	public static void main(String[] args) { 
		int n=8;
   		int[] a = {1,3,4,6,7,4,6,8};
   		int length = remove(a, n, 3);
		System.out.println("New length is " + length);
   		return;
	}
}
