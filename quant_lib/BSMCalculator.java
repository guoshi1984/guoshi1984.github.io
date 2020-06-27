package quant;
import math.*;
public class BSMCalculator
{
	
	BSMCalculator(Option option) {
		this.underlying = option.underlying;
		this.strike = option.strike;
		this.riskFreeRate = option.riskFreeRate;
		this.volatility = option.volatility;
		this.time = option.time;
		this.type = option.type;
	}

	public double calculate()
	{
		if (time < 0.000001) 
			return underlying > strike? underlying - strike: 0;
		double forward = underlying
			*Math.exp(riskFreeRate*time);
		double d1 = Math.log(forward/strike)/(volatility*Math.sqrt(time))
			+ 0.5*volatility*Math.sqrt(time);
		double d2 = d1 - volatility*Math.sqrt(time);
		double c1 = NormalDistribution.cdf(d1);	
		double c2 = NormalDistribution.cdf(d2);
		double value;
		switch(type){
			case CALL:
				value = Math.exp(-riskFreeRate*time)
					*(forward*c1 + strike*(-c2));
				break;
			case PUT:			
				value = Math.exp(-riskFreeRate*time)
					*(forward*(c1-1)+ strike*(1-c2));
				break;
			default:
				throw new IllegalArgumentException("Invalid option type.");
		}	
		return value;
		/*if (option.time < 0.000001) 
			return option.underlying > option.strike? option.underlying - option.strike: 0;
		double forward = option.underlying
			*Math.exp(option.riskFreeRate*option.time);
		double d1 = Math.log(forward/option.strike)/(option.volatility*Math.sqrt(option.time))
			+ 0.5*option.volatility*Math.sqrt(option.time);
		double d2 = d1 - option.volatility*Math.sqrt(option.time);
		double c1 = NormalDistribution.cdf(d1);	
		double c2 = NormalDistribution.cdf(d2);
		double value;
		switch(option.type){
			case CALL:
				value = Math.exp(-option.riskFreeRate*option.time)
					*(forward*c1 + option.strike*(-c2));
				break;
			case PUT:			
				value = Math.exp(-option.riskFreeRate*option.time)
					*(forward*(c1-1)+ option.strike*(1-c2));
				break;
			default:
				throw new IllegalArgumentException("Invalid option type.");
		}	
		return value;
		*/
	}

	public Complex getCharacteristicFunction(double u) {
		double imag = (Math.log(underlying) 
			+ (riskFreeRate - .5*volatility*volatility)*time)*u;
		double real = -.5*volatility*volatility*time*u*u;
		Complex exponent = new Complex(real, imag);
		return exponent.exp();
	}

	public Complex getCharacteristicFunction(Complex u) {
		Complex usq = u.times(u);
		double real1 = (Math.log(underlying) 
			+ (riskFreeRate - .5*volatility*volatility)*time);
		double real2 = -.5*volatility*volatility*time;
	
		Complex exponent1 = u.times(new Complex(0, 1)).times(new Complex(real1, 0)); 
		Complex exponent2 = usq.times(new Complex(real2, 0));
		Complex exponent = exponent1.plus(exponent2);
		return exponent.exp();
	}




	public static double integrate(double a, double b, Integrand integrand ) {
      		int N = 10000;                    // precision parameter
      		double h = (b - a) / (N - 1);     // step size
 
      		// 1/3 terms
      		double sum = 1.0 / 3.0 * (integrand.calculateIntegrand(a) + integrand.calculateIntegrand(b));

      		// 4/3 terms
      		for (int i = 1; i < N - 1; i += 2) {
         		double x = a + h * i;
         		sum += 4.0 / 3.0 * integrand.calculateIntegrand(x);
      		}

      		// 2/3 terms
      		for (int i = 2; i < N - 1; i += 2) {
         		double x = a + h * i;
         		sum += 2.0 / 3.0 * integrand.calculateIntegrand(x);
      		}

      		return sum * h;
   	}



	public double calculateUsingCharacteristicFunction() {
	
		Integrand integrand1 = (double u) -> {
			Complex chf1 = getCharacteristicFunction(new Complex(u, -1));
			Complex chf2 = getCharacteristicFunction(new Complex(0, -1));
			Complex num = new Complex(0, -u*Math.log(strike));
			num = num.exp();
			Complex den = new Complex(0.0, 0.0);
			switch(type) {
				case CALL:
					den = new Complex(0.0, u);
					break;
				case PUT:
					den = new Complex(0.0, -u);
					break;
				default:
					throw new IllegalArgumentException("Invalid option type.");
			}
			return chf1.divides(chf2).times(num).divides(den).getReal();
		};

		Integrand integrand2 = (double u) -> {
			Complex chf = getCharacteristicFunction(u);
			Complex num = new Complex(0, -u*Math.log(strike));
			num = num.exp();
			Complex den = new Complex(0.0, 0.0);
			switch(type) {
				case CALL:
					den = new Complex(0.0, u);
					break;
				case PUT:
					den = new Complex(0.0, -u);
					break;
				default:
					throw new IllegalArgumentException("Invalid option type.");
			}
			return chf.times(num).divides(den).getReal();
		};

                

		double i1 = .5+ 1/3.1415926*integrate(0.001, 100, integrand1);
		double i2 = .5+ 1/3.1415926*integrate(0.001, 100, integrand2);
		//System.out.println("i1 " + i1 + " i2 " + i2);
		double npv = 0;
		switch(type) {
			case CALL:
				npv = underlying*i1-strike*Math.exp(-riskFreeRate*time)*i2;
				break;
			case PUT:
				npv= - underlying*i1 + strike*Math.exp(-riskFreeRate*time)*i2;
				break;
			default:
				throw new IllegalArgumentException("Invalid option type.");
		}		
		return npv;
	}

	interface Integrand {
		//double calculateIntegrand(double... parameters);
		double calculateIntegrand(double parameters);
	}


	private double underlying;
	private double strike;
	private double riskFreeRate;
	private double volatility;
	private double time;
	private OptionType type;
}
