import java.util.*;
class BSMProcess extends Process
{
	// constructor
	public BSMProcess(double riskFreeRate, double volatility)
	{
		this.riskFreeRate = riskFreeRate;
		this.volatility = volatility;
	}
	// calcuate the drift
	public double drift(double riskFreeRate, double volatility)
	{
		return riskFreeRate - 0.5*volatility*volatility;
	}
	// specify how the process change from time t to t + dt
	public void evolve(double dt, double dw1, double dw2)
	{
		super.evolve(dt, dw1, dw2);
	}

	// specify how the volatiltiy changes from time t to t+dt
	public void evolveVolatility(double dt, double dw1, double dw2)
	{
		// BSM process does not involve change in volatility
		return;
	}
}
