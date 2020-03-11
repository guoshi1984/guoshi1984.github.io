public class Test 
{


	public static void main(String[] args)
	{
		Option option = new Option(OptionType.CALL, 36.0,
			40.0, 0.06, 
			0.2, 1.0);
		BSMCalculator bsm = new BSMCalculator();
		double value = bsm.calculate(option);
		System.out.println(value);
		MonteCarlo mc = new MonteCarlo(option,
			       1, 100000);
		mc.initialize();
		mc.run();
		return;
	}
}
