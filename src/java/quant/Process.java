abstract class Process
{
	/*    Define stochastic process for asset and volatility
	 *    dS = drift * dt + \sigma * dw	
	 */

	
	//setter for sample
	public void setSample(double sample)
	{
		this.sample = sample;
	}

	// getter for sample
	public double getSample()
	{
		return this.sample;
	}

	// drift term for dt
	public abstract double drift(double riskFreeRate, double volatility);
	
	// how the asset value evolves
	public void evolve(double dt, double dw1, double dw2)
	{
		double drift = drift(riskFreeRate, volatility);
                double move = drift*dt + volatility*Math.sqrt(dt)*dw1;
                double exp = Math.exp(move);
                this.setSample(this.sample*exp);

	}
	// how the volatility evolves
	public abstract void evolveVolatility(double dt, double dw1, double dw2);
	
	// sample to be evolved by the process
	protected double sample;
	
	// risk free interest rate
	protected double riskFreeRate;

	// volatitlity
	protected double volatility;
	
	
	public enum ProcessType
	{
		BSM,
		Heston
	}
}

