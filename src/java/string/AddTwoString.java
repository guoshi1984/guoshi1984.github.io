/* Leetcode 415
/* Given two strings made of numerical digits, add these two strings as if they are number and return the sum.
 * One can not use String to Integer library function directly
 */
public class AddTwoString
{
	public static String add(String s1, String s2)
	{
		final int n1 = s1.length();
		final int n2 = s2.length();
		int carry = 0;
		StringBuilder sb = new StringBuilder("");
		for(int i = 0; i < Math.max(n1, n2); i++)
		{
			int i1 = i < n1 ? s1.charAt(n1-1-i) - '0':0;	
			int i2 = i < n2 ? s2.charAt(n2-1-i) - '0':0;
			
			sb = sb.insert(0, (i1+i2+carry)%10);
			carry = (i1+i2+carry)/10;	
		}
		if(carry > 0) sb = sb.insert(0, carry);
		return sb.toString();

	}

	public static void main(String[] args)
	{
		String s1 = "4";
		String s2 = "9";
		String s = add(s1, s2);
		System.out.println(s);
	}
}
