public class ReverseInteger {
	public static int reverseInteger(int n) {
		boolean negative = (n < 0)? true : false;
		if(negative == true) n = -1*n;
		int result = 0;
		while(n%10 > 0 || n/10 >=1) {
			result = result*10 + n%10;
			n = n/10;
		}

		return negative? -1*result: result;
	}

	public static void main(String[] args) {
		int result1 = reverseInteger(0);
		System.out.println(result1);
		int result2 = reverseInteger(1);
		System.out.println(result2);
		int result3 = reverseInteger(10);
		System.out.println(result3);
		int result4 = reverseInteger(100);
		System.out.println(result4);
		int result5 = reverseInteger(101);
		System.out.println(result5);
		int result6 = reverseInteger(-1);
		System.out.println(result6);
	}
}
	

