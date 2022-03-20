import java.util.*;
/*
 * Path class
 * Given a sample of asset value, it value is evolve in time based on specific model, and the collection
 * of all the values respect to time form a sample path. This class is used to store asset values
 *  at each time point for a given sample, and the stopping time for this given sample path.
 */

public class Path {
	public List<Double> values;
	public int stoppingTime;

	public Path() {
		values = new ArrayList<Double>();
		stoppingTime = values.size() - 1;
	}

	public List<Double> getValues() {
		return values;
	}

	public int getStoppingTime() {
		return stoppingTime;
	}
	
	public void setStoppingTime(int stoppingTime) {
		this.stoppingTime = stoppingTime;
	}

	public void resetStoppingTime() {
		stoppingTime = values.size() - 1;
	}
}
