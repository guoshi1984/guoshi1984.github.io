public class MultiplyStrings
{
	//LeetCode 43
	/* Multiply mutiple digit strings by calling multiply multiple digit string with a single digit string */
	public static String multiply(String num1, String num2)
	{
		String result = new String("");
		String padding = new String("");
		for(int i =  num2.length() - 1; i >= 0; i--) {
			String simpleResult = simpleMultiply(num1, num2.charAt(i));
			result = AddTwoString.add(result, simpleResult + padding);
			padding = padding + "0"; 
		}
		return result;	
	}

	/* Multiply multiple digit string with a single digit string, return string. */
	public static String simpleMultiply(String num, char c)
	{
		StringBuilder sb = new StringBuilder();
		int ci = c - '0';
		int carrier = 0;
		for(int i = num.length() - 1; i >= 0; i--) {
			int num1 = num.charAt(i) - '0';
			int result = num1*ci + carrier;
			sb.insert(0, Integer.toString(result%10));
			carrier = result/10;
		}
		if(carrier > 0)
			sb.insert(0, Integer.toString(carrier));

		return sb.toString();
	}
	
	/* Multiply mutiple digit strings by calling multiply multiple digit string with a single digit string */
	public static int multiply2(String num1, String num2)
	{
		int result = 0;
		for(int i = 0; i <  num2.length(); i++) {
			result = simpleMultiply2(num1, num2.charAt(i)) + result*10;;
		}
		return result;	
	}

	/* Multiply multiple digit string with a single digit string, return string. */
	public static int simpleMultiply2(String num, char c)
	{
		StringBuilder sb = new StringBuilder();
		int ci = c - '0';
		int carrier = 0;
		int result = 0;
		for(int i = 0; i < num.length(); i++) {
			int num1 = num.charAt(i) - '0';
			result = num1*ci + result*10;
		}

		return result;
	}

	public static String multiply3(String num1, String num2) {
		 String n1 = new StringBuilder(num1).reverse().toString();
 		 String n2 = new StringBuilder(num2).reverse().toString();
 
    		int[] d = new int[num1.length()+num2.length()];
 
    		//multiply each digit and sum at the corresponding positions
    		for(int i=0; i<n1.length(); i++){
        		for(int j=0; j<n2.length(); j++){
            			d[i+j] += (n1.charAt(i)-'0') * (n2.charAt(j)-'0');
        		}
    		}
 
    		StringBuilder sb = new StringBuilder();
 
    		//calculate each digit
    		for(int i=0; i<d.length; i++){
        		int mod = d[i]%10;
        		int carry = d[i]/10;
        		if(i+1<d.length){
            			d[i+1] += carry;
        		}
        		sb.insert(0, mod);
    		}
 
    		//remove front 0's
    		while(sb.charAt(0) == '0' && sb.length()> 1){
        		sb.deleteCharAt(0);
    		}
		return sb.toString();
	}
 		

	public static void main(String[] agrs)
	{
		System.out.println(simpleMultiply(new String("0"), '0'));
		System.out.println(simpleMultiply(new String("48"), '4'));
		System.out.println(simpleMultiply(new String("48"), '3'));
		System.out.println(multiply(new String("48"), new String("34")));
		System.out.println(multiply(new String("128"), new String("128")));
		
		System.out.println(simpleMultiply2(new String("0"), '0'));
		System.out.println(simpleMultiply2(new String("48"), '4'));
		System.out.println(simpleMultiply2(new String("48"), '3'));
		System.out.println(multiply2(new String("48"), new String("34")));
		System.out.println(multiply2(new String("128"), new String("128")));
		System.out.println(multiply3(new String("48"), new String("34")));
		System.out.println(multiply3(new String("128"), new String("128")));
	}
}	

