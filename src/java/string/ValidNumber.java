public class ValidNumber{
	/* Leetcode 65 Valid Number
	 * ^   start of the string
	 * []  one of the characters in the bracket
	 * ?   once or none
	 * \\d one digit 0-9
	 * +   one or more
	 * $   end of string
	 * (?:) non capturing group
	 * |   or
	 */

	public static void matchByRx(String input)
	{

		input = input.trim();
		boolean isMatch = input.matches("^[+-]?\\d+$");
		isMatch = isMatch || input.matches("^[+-]?(?:\\d*\\.\\d+|\\d+\\.\\d*)$");
		isMatch = isMatch || input.matches("^[+-]?(?:\\d*\\.\\d+|\\d+\\.\\d*|\\d+)[Ee]?[+-]?\\d+$");
		System.out.println(input+" "+isMatch);
	}	


	public static void main(String[] args)
	{
		matchByRx(new String("-7"));
		matchByRx(new String("-7.1"));
		matchByRx(new String("-.1"));
		matchByRx(new String("+.1"));
		matchByRx(new String("+1."));
		matchByRx(new String("-1."));
		matchByRx(new String("-1.e-6"));
		matchByRx(new String("-.1E-01"));
		matchByRx(new String("x"));
		matchByRx(new String("$"));
		matchByRx(new String("."));
		matchByRx(new String("+."));
		matchByRx(new String("-+"));
		matchByRx(new String(".1."));
		matchByRx(new String("1.0e"));
		matchByRx(new String("e-6"));
		matchByRx(new String("-1.e-.6"));
		matchByRx(new String("-.1E-01e"));
		return;
	}




}
