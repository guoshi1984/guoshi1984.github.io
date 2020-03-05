import java.util.*;
class SamplePricing
{
	static double calculatePrice(Option option, List<Double> sample)
	{
		double total = 0;
		for(int i=0; i< sample.size(); i++)
		{
			total += option.type.payoff(sample.get(i), option.strike);
		}
		return total/sample.size()*Math.exp(-option.riskFreeRate*1);
	}
}
