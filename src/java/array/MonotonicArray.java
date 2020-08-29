/* LeetCode 896
 * */
public static MonotonicArray {
	public static boolean isMonotonic(int[] nums) {
		boolean isIncreasing = true;
		boolean isDecreasing = true;
		for(int i = 1; i< nums.length; i++) {
			if(nums[i] > nums[i-1])
				isDecreasing = false;
			if(nums[i] < nums[i-1])
				isIncreasing = false;
			if(isIncreasing == false && inDecreasing == false)
				return false;

		}

		return true;
	}
	
	public static void main(String[] args) {
		return;
	}
}
