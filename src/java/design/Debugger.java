/* In programming, we often need to print out something for debugging, 
 * however, we would like to turn off printing in production run. 
 * Degugger class that makes it easy to switch on and off print statement in the code.
 * The class utilize enum type in java and singleton design pattern.
 * We overload basic function print in two ways, print something or print nothing.
 * We wrap the overloaded function using an enum class DebuggerTypes, with two type named ON and OFF.
 * We create a singleton instance named debugger, which can be assigned to ON and OFF as needed.
 * 
 */

public class Debugger 
{	
	/* define as a inner class as DebuggerTypes is only used by
	 * Debugger, define it as static because we do not need to use it by
	 * creating an instance of outside class Debugger.
	 */
	static enum DebuggerTypes //implements DebuggerType 
	{
		// java enum can have abstract method for each value to overload.
		ON
		{

			@Override
			public void print(Object o)
			{
				System.out.print(o);
				return;
			}
			@Override
			public void println(Object o)
			{
				System.out.println(o);
				return;
			}

		},
		OFF
		{
			
			@Override
			public void print(Object o)
			{
				return;
			}
			@Override
			public void println(Object o)
			{
				return;
			}
		};
		public abstract void print(Object o);
		public abstract void println(Object o);
	}
	// hide the constructor because we do not need to create an object
	private Debugger() {};
	public static DebuggerTypes debugger;
	private static boolean isDebugging = false;

	// static block to initialize static field
	static
	{
		isDebugging = false;
		debugger = DebuggerTypes.OFF;
	}

	// set debugging on or off by boolean
	public static void setDebugging(boolean debugging)
	{
		Debugger.isDebugging = debugging;
	        debugger = (isDebugging)? DebuggerTypes.ON: DebuggerTypes.OFF;	
	}

	// return true if debugging is on, return false is debugging is off.
	public static boolean isDebugging()
	{
		return isDebugging;
	}

	public static void print(String s)
	{
		debugger.print(s);
	}

	public static void println(String s)
	{
		debugger.println(s);
	}

	public static void print(Object o)
	{
		debugger.print(o);
	}

	public static void println(Object o)
	{
		debugger.println(o);
	}

	public static void test1()
	{
		String s = "hello";
		Debugger.println(s);
		Debugger.setDebugging(true);
		Debugger.println(s);
		
		int i = 1;
		Debugger.setDebugging(false);
		Debugger.println(i);
		Debugger.setDebugging(true);
		Debugger.println(i);

		return;
	}

	public static void main(String args[])
	{
		test1();
		return;
	}
	
}
