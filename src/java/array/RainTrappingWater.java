/* leetcode 42
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water 
 * it is able to trap after raining.
 *  
 */
public class RainTrappingWater
{
	public static int trap(int[] heights)
	{
		int result = 0;
		int prev = -1;
		int curHeight = 1;
		do{
			System.out.println("cur height " + curHeight);
			prev = -1;
			for(int i = 0; i< heights.length; i++){
				if(heights[i]>=curHeight){
				        if(prev == -1 || prev == i-1)
						prev = i;
					if(prev >=0 && prev < i-1){
						result += i - prev -1;
						System.out.println("i "+ i + " prev "+ prev);
						prev =i;
					}
				}
			}
			curHeight++;
			System.out.println("current result "+ result);
		}while(prev != -1);

		return result;
	}

	public static int trap2(int[] heights)
	{
		final int n = heights.length;
		int[] max_left=new int[heights.length];
   		int[] max_right=new int[heights.length];
   		for (int i=0; i<n; i++){  
			max_left[i]=0;
      			max_right[i]=0;
   		}
   		for(int i=1; i<n; i++){ 
			max_left[i]= Math.max(max_left[i-1], heights[i-1]);
     			max_right[n-1-i]= Math.max(max_right[n-i], heights[n-i-1]);
   		}

   		int sum=0;
   		for(int i=0; i<n; i++){  
			int height = Math.min(max_left[i], max_right[i]);
      			if(height> heights[i]){  
				//cout <<" i "<<i << " "<<height-A[i] << endl;
         			sum=sum+height-heights[i];
      			}
   		}
   		return sum;

	}
	public static void main(String[] args)
	{
		int[] heights1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
		System.out.println("result "+ trap(heights1));
		int[] heights2 = {2, 0, 2};
		System.out.println("result "+ trap(heights2));
		int[] heights3 = {3, 0, 0, 2, 0, 4};
		System.out.println("result "+ trap(heights3));
	}
}
