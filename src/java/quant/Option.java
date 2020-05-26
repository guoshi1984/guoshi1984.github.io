package quant;
public class Option
{
	public Option(ExerciseStyle style, OptionType type, double underlying,
			double strike, double riskFreeRate, 
			double volatility, double time)
	{
		this.style = style;
		this.type = type;
		this.underlying = underlying;
		this.strike = strike;
		this.riskFreeRate = riskFreeRate;
		this.volatility = volatility;
		this.time = time;
		this.isPathDependent = (style == ExerciseStyle.AMERICAN) ? true : false;
	}

	protected OptionType type;
	protected ExerciseStyle style;
	protected double underlying;
	protected double strike;
	protected double riskFreeRate;
	protected double volatility;
	protected double time;
	protected boolean isPathDependent;

	
}

enum OptionType {
	CALL {
		@Override
		public double payoff(double underlying, double strike) {
			return Math.max(underlying - strike,0);
		}
	}, 
	PUT {
		@Override
		public double payoff(double underlying, double strike) {
			return Math.max(strike - underlying, 0);
		}
	};
	public abstract double payoff(double underlying, double strike);
}

enum ExerciseStyle {
	EUROPEAN,
	AMERICAN;
}
