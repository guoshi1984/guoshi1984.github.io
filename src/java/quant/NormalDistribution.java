package quant;
import java.util.*;
public class NormalDistribution
{

	public static double generate()
	{
		Random r = new Random();
		double u1 = 0;
		double u2 = 0;
		double dsq = 0;
		do {
			u1 = (r.nextDouble() - 0.50)*2.0;
			u2 = (r.nextDouble() - 0.50)*2.0;
			dsq = u1*u1 + u2*u2;
		} while (dsq > 1 );
		return u1*Math.sqrt(-2*Math.log(dsq)/dsq);

	}
	// return pdf(x) = standard Gaussian pdf
    	public static double pdf(double x) {
        	return Math.exp(-x*x / 2) / Math.sqrt(2 * Math.PI);
    	}

    	// return pdf(x, mu, signma) = Gaussian pdf with mean mu and stddev sigma
    	public static double pdf(double x, double mu, double sigma) {
        	return pdf((x - mu) / sigma) / sigma;
    	}

    	// return cdf(z) = standard Gaussian cdf using Taylor approximation
    	public static double cdf(double z) {
        	if (z < -8.0) return 0.0;
        	if (z >  8.0) return 1.0;
        	double sum = 0.0, term = z;
        	for (int i = 3; sum + term != sum; i += 2) {
            		sum  = sum + term;
            		term = term * z * z / i;
        	}	
        	return 0.5 + sum * pdf(z);
	}
}
