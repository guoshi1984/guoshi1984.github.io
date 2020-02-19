public class Test 
{


	public static void main(String[] args)
	{
		for(int i=0; i<10; i++)
		{	
			Option option = new Option(OptionType.CALL, 46.0,
				40.0, 0.06, 
				0.2, i*0.5);
			BSMCalculator bsm = new BSMCalculator();
			double value = bsm.calculate(option);
			System.out.println(i*0.5 +
				       " "+	value);
		}
		return;
	}
}
