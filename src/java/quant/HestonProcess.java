import java.util.*;
class HestonProcess extends Process
{
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
	// calcuate the drift
	public double drift(double riskFreeRate, double volatility)
	{
		return riskFreeRate - 0.5*volatility*volatility;
	}
	// specify how the process change from time t to t + dt
	public void evolve(double dt, double dw1, double dw2)
	{
		super.evolve(dt, dw1, dw2);
		evolveVolatility(dt, dw1, dw2);
	}

	// specify how the volatility changes
	public void evolveVolatility(double dt, double dw1, double dw2)
	{
		double volplus = (this.volatility > 0) ? Math.sqrt(this.volatility) : 0.0;
		double nu = this.kappa*(this.theta - this.volatility);
		this.volatility +=  nu*dt + this.sigma*volplus
			*(this.rho*dw1 + Math.sqrt(1 - this.rho * this.rho)*dw2);	
	}


	private double sigma;
	private double kappa;
	private double theta;
	private double rho;
}
