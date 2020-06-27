import java.util.*;
/*
 * dS = r*S*dt + volatility*dW
 */
class BSMProcess extends Process
{
	// constructor
	public BSMProcess(double riskFreeRate, double volatility)
	{
		this.riskFreeRate = riskFreeRate;
		this.volatility = volatility;
	}

}
