import math.*;
public class HestonCalculator
{
	
	HestonCalculator(Option option, double kappa, double theta, double sigma, double rho) {
		this.underlying = option.underlying;
		this.strike = option.strike;
		this.riskFreeRate = option.riskFreeRate;
		this.time = option.time;
		this.type = option.type;
		this.volatility = option.volatility;
		this.kappa = kappa;
		this.theta = theta;
		this.sigma = sigma;
		this.rho = rho;
	}



	public Complex getCharacteristicFunction(Complex u) {
		System.out.println("u" + u);
		// aa = i\rho \sigma u
		Complex aa = u.times(new Complex(0, rho*sigma)); 
		System.out.println("aa "+ aa);
		// d1 = (aa - kappa)^2
		Complex d1 = aa.plus(-1.0*kappa);
		d1 = d1.times(d1);
		
		// d2 = \sigma^2 (u^2 + iu)
		Complex d2 = u.times(u).plus(u.times(new Complex(0, 1))).times(sigma*sigma);
	
		// d = sqrt(d1^2 + d2)
		Complex d = d1.plus(d2);
		d = d.sqrt();
		System.out.println("d " + d);	
		// gminus = kappa - aa - d
		Complex gplus = d.minus(aa).plus(kappa);
		System.out.println("gplus "+ gplus);		
		// gplus = kappa - aa - d
		Complex gminus = d.times(-1.0).minus(aa)
			.plus(kappa);
		System.out.println("gminus "+ gminus);		
		// g = g1 /g2
		Complex g;
		if(gminus.mod() > 0.000001) 
			g = gplus.divides(gminus);
		else 
			g = new Complex(999999, 0); 
		// ffd = 1 - e^{d\tau}
		System.out.println("g "+ g);		

		//edtau = e^{d\tau}
		Complex edtau = d.times(time).exp();
		//gedtau = ge^{d\tau}
		Complex gedtau = d.times(time).exp().times(g);
		// D = g1/sigma^2*ff 
		Complex ffd = edtau.times(-1).plus(1.0).divides(gedtau.times(-1).plus(1.0));
		System.out.println("ffd " + ffd);
		// D = g1/\sigma^2 * ff
		//Complex D = gplus.times(ffd);
		//System.out.println("D "+ D);		
		Complex D = gplus.times(1.0/sigma/sigma).times(ffd);
		System.out.println("D "+ D);		
		// C = C1 + C2
		// C1 = iru\tau
		Complex C1 = u.times(riskFreeRate).times(time).times(new Complex(0, 1));
		// C2 = \kappa*\theta/sigma^2(C21 +C22)
		// C21 = g1\tau
		Complex C21 = gplus.times(time);
		
		// C22 = -2 log(ff)
		Complex C22 = gedtau.times(-1).plus(1.0).divides(g.times(-1.0).plus(1.0)).log().times(-2.0);
		System.out.println("C22 "+ C22);		
		// C2 = \kappa \theta /sigma^2 *(C21 + C22)
		Complex C2 = C21.plus(C22).times(kappa*theta/sigma/sigma);
		System.out.println("C2 "+ C2);		
		
		Complex C = C1.plus(C2);
		System.out.println("C "+ C);	
		System.out.println("DV" + D.times(volatility*volatility));	
		System.out.println("C + DV "+ C.plus(D.times(volatility*volatility)));	
		Complex ch = C.plus(D.times(volatility*volatility)).plus(u.times(Math.log(underlying)).times(new Complex(0,1)));
		System.out.println("ch base "+ ch);
		ch = ch.exp();
		System.out.println("ch "+ ch);
		return ch; 
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
			Complex chf = getCharacteristicFunction(new Complex(u, 0));
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

                //System.out.println(integrand1.calculateIntegrand(0.0000001));
                //System.out.println(getCharacteristicFunction(new Complex(0.1, 0)));

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
	private double kappa;
	private double theta;
	private double sigma;
	private double rho;
	private OptionType type;
}
