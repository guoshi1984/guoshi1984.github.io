public class BSMCalculator
{

	public double calculate(Option option)
	{
		if (option.time < 0.000001) 
			return option.underlying > option.strike? option.underlying - option.strike: 0;
		double forward = option.underlying
			*Math.exp(option.riskFreeRate*option.time);
		double d1 = Math.log(forward/option.strike)/(option.volatility*Math.sqrt(option.time))
			+ 0.5*option.volatility*Math.sqrt(option.time);
		double d2 = d1 - option.volatility*Math.sqrt(option.time);
		double c1 = NormalDistribution.cdf(d1);	
		double c2 = NormalDistribution.cdf(d2);
		double value;
		switch(option.type){
			case CALL:
				value = Math.exp(-option.riskFreeRate*option.time)
					*(forward*c1 + option.strike*(-c2));
				break;
			case PUT:			
				value = Math.exp(-option.riskFreeRate*option.time)
					*(forward*(c1-1)+ option.strike*(1-c2));
				break;
			default:
				throw new IllegalArgumentException("Invalid option type.");
		}	
		return value;
	}

}
