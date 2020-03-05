import java.util.*;
class MonteCarlo
{
	MonteCarlo(Option option,
			int timeStep, int sampleSize)
	{
		this.init = option.underlying;
		this.drift = option.riskFreeRate-0.5*option.volatility*option.volatility;
		this.sigma = option.volatility;
		this.timeStep = timeStep;
		this.sampleSize = sampleSize;
	}

	void initialize()
	{
		samples = new ArrayList<Double>(sampleSize);
		for(int i = 0; i<sampleSize; i++){
			samples.add(init);
		}
	}

	void evolve()
	{
		Random r = new Random();
		for(int i = 0; i<sampleSize; i++){
			double move = drift + sigma*r.nextGaussian();
			double exp = Math.exp(move);
			samples.set(i, samples.get(i)*exp);
		}
		
		
		
	}

	
	double init;
	double drift;
	double sigma;
	int timeStep;
	int sampleSize;
	List<Double> samples;
}
