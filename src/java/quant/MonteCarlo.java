import java.util.*;
public class MonteCarlo
{
	public MonteCarlo(Option option,
			int nTimeStep, int sampleSize, Process process)
	{
		this.option = option;
		this.init = option.underlying;
		this.nTimeStep = nTimeStep;
		this.sampleSize = sampleSize;
		this.process = process;
	}

	public void initialize()
	{
		samples = new ArrayList<Double>(sampleSize);
		for(int i = 0; i<sampleSize; i++){
			samples.add(init);
		}
		accumulator = new SampleAccumulator();
	}

	/*double evolve(double sample, double dt)
	{
		Random r = new Random();
		double move = drift*dt + sigma*Math.sqrt(dt)*r.nextGaussian();
		//double move = drift + sigma*r.nextGaussian();
		double exp = Math.exp(move);
		return sample*exp;
		
		
		
	}
	*/
	void run()
	{
		for(int i = 0; i<sampleSize; i++){
			double currentSample = samples.get(i);			
			double dt = option.time/nTimeStep;
			//System.out.println(nTimeStep);	
			for(int j = 0; j < nTimeStep; j++)
			{
				Random random = new Random(); 
				double dw1 = random.nextGaussian();
				double dw2 = random.nextGaussian();
				process.setSample(currentSample);
				process.evolve(dt, dw1, dw2);	
				currentSample = process.getSample();
			}
				double discountPayoff = option.type.payoff(currentSample, option.strike)
				*Math.exp(-option.riskFreeRate*option.time);	
			accumulator.samples.add(discountPayoff);
		}
		accumulator.average();
		System.out.println(accumulator.mean +"+/-" + accumulator.errorbar);
	}

	Option option;	
	double init;
	int nTimeStep;
	int sampleSize;
	List<Double> samples;
	SampleAccumulator accumulator;
	Process process;
}
