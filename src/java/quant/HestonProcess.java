import java.util.*;
class HestonProcess extends Process
{
	/* Define Heston Process
	 * dS = rSdt + \sqrt{S} dW1
	 * dV = \kappa(\theta - V)dt + \sigma \sqrt{V} dW2
	 * cor[dW1, dW2] = \rho dt
	 */

	// constructor
	public HestonProcess(double riskFreeRate, double volatility,
				double kappa, double theta, 
				double sigma, double rho)
	{
		this.riskFreeRate = riskFreeRate;
		this.volatility = volatility;
		this.kappa = kappa;
		this.theta = theta;
		this.sigma = sigma;
		this.rho = rho;
	}
	


	// specify how the volatility changes
	public void evolveVolatility(double dt, double dw1, double dw2)
	{
		double nu = this.kappa*(this.theta - this.volatility*this.volatility);
		double vol2 =  this.volatility*this.volatility + nu*dt 
			+ this.sigma*this.volatility*(this.rho*dw1 
					+ Math.sqrt(1 - this.rho * this.rho)*dw2);	
		this.volatility = (vol2 > 0) ? Math.sqrt(vol2) : 0.0;
	}


	private double sigma;
	private double kappa;
	private double theta;
	private double rho;
}
