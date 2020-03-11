public class Option
{
	public Option(OptionType type, double underlying,
			double strike, double riskFreeRate, 
			double volatility, double time)
	{
		this.type = type;
		this.underlying = underlying;
		this.strike = strike;
		this.riskFreeRate = riskFreeRate;
		this.volatility = volatility;
		this.time = time;
	}

	public OptionType type;
	public double underlying;
	public double strike;
	public double riskFreeRate;
	public double volatility;
	public double time;
	
}

enum OptionType
{
	CALL
	{
		@Override
		public double payoff(double underlying, double strike)
		{
			return Math.max(underlying - strike,0);
		}
	}, 
	PUT
	{
		@Override
		public double payoff(double underlying, double strike)
		{
			return Math.max(strike - underlying, 0);
		}
	};
	public abstract double payoff(double underlying, double strike);
}

