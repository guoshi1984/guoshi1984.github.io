/* Problem based on Leetcode 415, change int to string
/* Given two strings made of numerical float digits, add these two strings as if they are number and return the sum.
 * One can not use String to Integer library function directly
 */
public class AddTwoFloatString
{
	public static String add(String s1, String s2)
	{
		final int n1 = s1.length();
		final int n2 = s2.length();
		final int d1 = s1.indexOf('.');
		final int d2 = s2.indexOf('.');
		final int intlen = Math.max(d1, d2);
		final int declen = Math.max(n1 - d1, n2 - d2)-1;
		System.out.println(n1 + " "+ n2);
		System.out.println(d1 + " "+ d2);
		System.out.println(intlen + " "+ declen);
		int carry = 0;
		StringBuilder sb = new StringBuilder("");
		for(int i = declen; i >= -1*intlen; i--)
		{
			if(i == 0) {
				sb.insert(0, '.');
				continue;
			}
			System.out.println("i "+i);
			int i1 = 0; 
			int i2 = 0;
			i1 = getDigit(s1, d1, i);
			i2 = getDigit(s2, d2, i);
			System.out.println("i1 " + i1 + "i2 " + i2);
			sb = sb.insert(0, (i1+i2+carry)%10);
			carry = (i1+i2+carry)/10;
			
			
		}
		return sb.toString();

	}


	public static int getDigit(String s, int dotIndex, int i)
	{
		if(dotIndex + i < 0 || dotIndex + i >= s.length()) return 0;
		else
			return s.charAt(dotIndex + i) -'0';
	}
	public static void main(String[] args)
	{
		String s1 = "12.9";
		String s2 = ".8";
		String s = add(s1, s2);
		System.out.println(s);
	}
}
