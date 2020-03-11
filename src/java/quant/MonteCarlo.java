import java.util.*;
public class MonteCarlo
{
	public MonteCarlo(Option option,
			int timeStep, int sampleSize)
	{
		this.option = option;
		this.init = option.underlying;
		this.drift = option.riskFreeRate-0.5*option.volatility*option.volatility;
		this.sigma = option.volatility;
		this.timeStep = timeStep;
		this.sampleSize = sampleSize;
	}

	public void initialize()
	{
		samples = new ArrayList<Double>(sampleSize);
		for(int i = 0; i<sampleSize; i++){
			samples.add(init);
		}
		accumulator = new SampleAccumulator();

	}

	void evolve(int i)
	{
		Random r = new Random();
		double move = drift + sigma*r.nextGaussian();
		double exp = Math.exp(move);
		samples.set(i, samples.get(i)*exp);
		
		
		
	}

	void run()
	{
		for(int i = 0; i<sampleSize; i++){
			evolve(i);
			double discountPayoff = option.type.payoff(samples.get(i), option.strike)
				*Math.exp(-option.riskFreeRate*1);	
			accumulator.samples.add(discountPayoff);
		}
		accumulator.average();
		System.out.println(accumulator.mean +"+/-" + accumulator.errorbar);
	}

	Option option;	
	double init;
	double drift;
	double sigma;
	int timeStep;
	int sampleSize;
	public List<Double> samples;
	public SampleAccumulator accumulator;
}
