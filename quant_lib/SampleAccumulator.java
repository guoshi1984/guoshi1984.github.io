package quant;
import java.util.*;
class SampleAccumulator
{
	SampleAccumulator()
	{
		samples = new ArrayList<Double>();
	}
	public  void  average()
	{
		double total = 0;
		for(int i=0; i< samples.size(); i++)
		{
			total += samples.get(i);
		}
		
		mean = total/samples.size();
		for(int i=0; i< samples.size(); i++)
		{
			errorbar += Math.pow(samples.get(i), 2);
		}
		errorbar = Math.sqrt(errorbar/samples.size()-1)/Math.sqrt(samples.size());
	}
	public List<Double> samples;
	public double mean;
	public double errorbar;
}
