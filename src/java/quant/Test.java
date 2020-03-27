public class Test 
{


	public static void main(String[] args)
	{
		Option option = new Option(OptionType.CALL, 36.0,
			40.0, 0.06, 
			0.2, 2.0);
		BSMCalculator bsm = new BSMCalculator();
		double value = bsm.calculate(option);
		System.out.println(value);
		Process process = new BSMProcess(option.riskFreeRate,
                                option.volatility);
		MonteCarlo mc = new MonteCarlo(option,
			       10, 100000, process);
		mc.initialize();
		mc.run();
		Process process2 = new HestonProcess(option.riskFreeRate,
                                option.volatility, 1.0, 
				option.volatility*option.volatility, 0.001, 0.0);
	
		MonteCarlo mc2 = new MonteCarlo(option,
			       10, 100000, process2);
		mc2.initialize();
		mc2.run();
		return;
	}
}
