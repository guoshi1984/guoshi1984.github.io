import java.util.*;
class SampleAccumulator
{
	public SampleAccumulator() {
		samples = new ArrayList<Double>();
	}

	public void showInfo() {
		System.out.println("Sampling Technique: Raw.");
	}
	//public List<Double> getSamples() {
	//	return samples;
	//}

	public void addSample(double sample) {
		samples.add(sample);
		return;
	}

	public void addControlVar(double controlVar) {
		return;
	}

	public  void  average() {
		mean = 0;
		for(int i=0; i< samples.size(); i++) {
			mean += samples.get(i);
		}
		
		mean /= samples.size();
		for(int i=0; i< samples.size(); i++) {
			errorbar += Math.pow(samples.get(i) - mean, 2);
		}
		errorbar = Math.sqrt(errorbar/samples.size()-1)/Math.sqrt(samples.size());
	}

	

	public List<Double> samples;
	public double mean;
	public double errorbar;
}

enum SampleAccumulatorType {
	RAW, CONTROL;
}
