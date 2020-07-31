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
	
	/*public void showInfo() {
                System.out.println("Kappa: " + kappa);
                System.out.println("Theta: " + theta);
                System.out.println("Volatility of volatility: " + sigma);
                System.out.println("Process correlation: " + rho);
        }
	*/

	// specify how the volatility changes
	@Override
	public void evolveVolatility(double dt, double dw1, double dw2)
	{
		double nu = this.kappa*(this.theta - this.volatility*this.volatility);
		double volsq =  this.volatility*this.volatility + nu*dt 
			+ this.sigma*this.volatility*Math.sqrt(dt)*(this.rho*dw1 
					+ Math.sqrt(1 - this.rho * this.rho)*dw2);	
		this.volatility = (volsq > 0) ? Math.sqrt(volsq) : 0.0;
		//System.out.println("evolve volatility " + this.volatility);
		
	}


	private double sigma;
	private double kappa;
	private double theta;
	private double rho;
}
