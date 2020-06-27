import java.util.*;
abstract class Process
{
	/*    Define stochastic process for asset and volatility
	 *    dS = drift*S*dt + volatility*S*dw	
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

	// setter for riskFreeRate
	public void setRiskFreeRate(double riskFreeRate)
	{
		this.riskFreeRate = riskFreeRate;
	}
	
	// setter for volatility
	public void setVolatility(double volatitliy)
	{
		this.volatility = volatility;
	}

	public void evolve(double dt)
	{
		Random r = new Random();
		double dw1 = r.nextGaussian();
		double dw2 = r.nextGaussian();
		double dw3 = r.nextGaussian();
		evolveAsset(dt, dw1, dw2, dw3);
		evolveVolatility(dt, dw1, dw2);
	}

	// how the asset value evolves
	public void evolveAsset(double dt, double dw1, double dw2, double dw3)
	{
		
                double move = drift(dt) + diffuse(dt, dw1) + jump(dt, dw3);
                this.setSample(this.sample*Math.exp(move));

	}

	// how the volatility evolves
	protected void evolveVolatility(double dt, double dw1, double dw2)
	{
		// do nothing unless subclass override it
		return;
	}
	// drift term for dt
	protected double drift(double dt)
	{
		return (riskFreeRate - 0.5*volatility*volatility)*dt;
	}

	// diffusion term driven by Brownian motion
	protected double diffuse(double dt, double dw)
	{
		//System.out.println("diffuse " + volatility);
		return volatility*Math.sqrt(dt)*dw;
	}	

 	// jump term driven by Poisson process	
	protected double jump(double dt, double dw3) {
		return 0;
	}

	// sample to be evolved by the process
	protected double sample;
	
	// risk free interest rate
	protected double riskFreeRate;

	// volatitlity
	protected double volatility;
	
	
	public enum ProcessType
	{
		
		BSM,  // drift and diffusion
		Heston,  // drift, diffusion, stochastic volatility 
		Bates    // drift, diffusion, jump, stochastic volatility 
	}
}

