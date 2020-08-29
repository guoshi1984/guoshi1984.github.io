public class ProductOfArrayExceptItself {
	
	public int[] getProduct(int[] nums) {
		int n = nums.length;
        	int[] left = new int[n];
       		int[] right = new int[n];
		int[] result = new int[n];
		left[0] = 1;
        	for (int i = 0; i < n - 1; ++i) {
            		left[i + 1] = left[i] * nums[i];
        	}
		right[n-1] = 1;
        	for (int i = n - 1; i > 0; --i) {
            		right[i - 1] = right[i] * nums[i];
        	}
        	for (int i = 0; i < n; ++i) {
            		result[i] = left[i] * right[i];
        	}
        	return result;
    	}
}
