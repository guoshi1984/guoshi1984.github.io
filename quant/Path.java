package quant;
import java.util.*;
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
