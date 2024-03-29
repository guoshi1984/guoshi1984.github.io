import java.util.*;
import math.*;
/* MonteCarlo class
 * This class defines how sample evolve with time 
 */

public class MonteCarlo
{
	// Constructor
	public MonteCarlo(Option option,
			int nTimeStep, int sampleSize, Process process,
			SampleAccumulatorType sampleAccumulatorType) {
		this.option = option;
		this.init = option.underlying;
		this.nTimeStep = nTimeStep;
		this.sampleSize = sampleSize;
		this.process = process;
		this.sampleAccumulatorType = sampleAccumulatorType;
	}
	
	// Print basec information
	public void  showInfo() {
		System.out.println("TimeSteps per Year: " + nTimeStep);
		System.out.println("Number of Samples: " + sampleSize);
		if(accumulator != null)
			accumulator.showInfo();
	}

 	// Initialize samples and simulation paths(collection of samples with resptect to time)	
	public void initialize() {

		//initialize samples
		samples = new ArrayList<Double>(sampleSize);
		for(int i = 0; i<sampleSize; i++){
			samples.add(init);
		}

		//initialize SampleAccumulator, used for calculating mean, variance, etc.
		if(sampleAccumulatorType == SampleAccumulatorType.RAW)
			accumulator = new SampleAccumulator();
		else if(sampleAccumulatorType == SampleAccumulatorType.CONTROL)
			accumulator = new ControlSampleAccumulator();
		else
			throw new IllegalArgumentException();

		//create data container to store sample paths
		paths = new ArrayList<Path>(this.sampleSize);
		for(int i = 0; i < this.sampleSize; i++) {
			paths.add(new Path());
		}
	}

	// save sample to paths
	public void saveSample(int sampleIndex, double currentSample) {
		paths.get(sampleIndex).getValues().add(currentSample);
		// each time we add the sample to the path, we update the stopping time 
		// to be the end time of the path
		paths.get(sampleIndex).resetStoppingTime();
	}

	// reload the sample from a path at its stopping time
	public double reloadSampleWithStoppingTime(int sampleIndex) {
		Path path = paths.get(sampleIndex);
		return path.getValues().get(path.getStoppingTime());
	}

	// reload the sample from a path at a given time point
	public double reloadSample(int sampleIndex, int timeStepIndex) {
		Path path = paths.get(sampleIndex);
		return path.getValues().get(timeStepIndex);
	}

	// update stopping time using least square for american options
	public void updateStoppingTime() {
		// go backwards in time to determine stoptime 
		for(int t = nTimeStep - 2; t >= 0; t--) {
			//System.out.println("TimeStep " + t);
			double[][] Xs = new double[sampleSize][3];
			double[] Ys = new double[sampleSize];
			int count = 0;
			int[] earlyCandidateIndex = new int[sampleSize];
			for(int i = 0; i < sampleSize; i++ ) {
				double currentSample = reloadSample(i, t);
				//System.out.println("Reload Sample: " + i + " "+ t + " " + currentSample);
				//at each time points, collect all the samples that have 
				//positive payoff
				if(option.type.payoff(currentSample, option.strike) > 0) {
					earlyCandidateIndex[count] = i;

					// construct matrix of explainary variables for regression
					Xs[count] = new double[]{1, currentSample, currentSample*currentSample};
					//System.out.println("Xs: " + Xs[count][0] + " " +Xs[count][1]);	
				
					double deltaT = option.time/nTimeStep*
						(paths.get(i).getStoppingTime() - t);
					//System.out.println("DeltaT: " + i +" " + t + " "+ deltaT);
					//System.out.println("Reload Sample Stopping Time " + reloadSampleWithStoppingTime(i));
					Ys[count]= option.type.payoff(reloadSampleWithStoppingTime(i),
				       	option.strike)
					*Math.exp(-option.riskFreeRate*deltaT);
					count++;					
				}	
			}

			earlyCandidateIndex = Arrays.copyOf(earlyCandidateIndex, count);
			Xs = Arrays.copyOf(Xs, count);
			Ys = Arrays.copyOf(Ys, count);	
			//MatrixAlgebra.print(Xs);	
			//MatrixAlgebra.print(Ys);	
			double[] beta = new double[3];
			//do linear regression of Ys on Xs
			MatrixAlgebra.leastSquareCalculation(Xs, Ys, beta);
			//Yhat is the predictive value of holding a option to next stopping time
			double[] Yhat = MatrixAlgebra.matrixMultiply(Xs, beta);
			
			for(int c = 0; c < count; c++ ) {
                                double currentSample = reloadSample(earlyCandidateIndex[c], t);
                                if(option.type.payoff(currentSample, option.strike) > Yhat[c]) {
					paths.get(earlyCandidateIndex[c]).setStoppingTime(t);
					//System.out.println("Update stopping time " + earlyCandidateIndex[c]
					//		+ " " + t);
					//System.out.println(" Get stopping time " 
					//		+ paths.get(earlyCandidateIndex[c]).getStoppingTime() );					
				}
                        }

			for(int i = 0; i < sampleSize; i++ ) {
				//System.out.println("Path "+ i+ " "+paths.get(i).getStoppingTime());
			}

		}	
	}


	void run()
	{
		for(int i = 0; i < sampleSize; i++) {
			double currentSample = samples.get(i);			
			double dt = option.time/nTimeStep;
			//System.out.println(nTimeStep);	
			for(int t = 0; t < nTimeStep; t++) {
				process.setSample(currentSample);
				process.evolve(dt);	
				currentSample = process.getSample();
				saveSample(i, currentSample);
			}
			
		}

		if(option.isPathDependent == true) updateStoppingTime(); 
			
		for(int i = 0; i < sampleSize; i++) {
			double stoppingTime = option.time/nTimeStep*(paths.get(i).getStoppingTime()+1);
			double discountPayoff = option.type.payoff(reloadSampleWithStoppingTime(i),
				       	option.strike)
				*Math.exp(-option.riskFreeRate*stoppingTime);	
			accumulator.addSample(discountPayoff);
			accumulator.addControlVar(reloadSampleWithStoppingTime(i) 
					- option.underlying*Math.exp(option.riskFreeRate
						*stoppingTime));
		}
		accumulator.average();
	}

	public void showResult() {
		System.out.println("Net Present Value " + accumulator.mean +" +/- " + accumulator.errorbar);
	}

	protected Option option;	
	protected double init;
	protected int nTimeStep;
	protected int sampleSize;
	protected List<Double> samples;
	protected SampleAccumulator accumulator;
	protected Process process;
	protected List<Path> paths;
	protected SampleAccumulatorType sampleAccumulatorType;
}
