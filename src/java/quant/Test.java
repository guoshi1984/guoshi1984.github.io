import java.util.*;
public class Test 
{


	public static void main(String[] args)
	{
		Option option = new Option(OptionType.CALL, 36.0,
			40.0, 0.06, 
			0.2, 2.0);
		BSMCalculator bsm = new BSMCalculator();
		double value = bsm.calculate(option);
		System.out.println(value);
		Process process = new BSMProcess(option.riskFreeRate,
                                option.volatility);
		MonteCarlo mc = new MonteCarlo(option,
			       10, 100000, process);
		mc.initialize();
		mc.run();
		Process process2 = new HestonProcess(option.riskFreeRate,
                                option.volatility, 
				1.0,  //kappa
				option.volatility*option.volatility, //theta
			       	0.001, //sigma
				0.0); //rho
	
		MonteCarlo mc2 = new MonteCarlo(option,
			       10, 100000, process2);
		mc2.initialize();
		mc2.run();
		
		double mo1 = 0;
		double mo2 = 0;
		double mo3 = 0;
		double mo4 = 0;
		int n = 1000;
		Random r = new Random();
		for (int i = 0; i < n; i++)
		{
			double gr = NormalDistribution.generate();	
		//	double gr = r.nextGaussian();
			mo1 += gr;
			mo2 += gr*gr;
			mo3 += gr*gr*gr;
			mo4 += gr*gr*gr*gr;
		}
		mo1 /= n;
		mo2 /= n;
		mo3 /= n;
		mo4 /= n;
		System.out.println(mo1);
		System.out.println(mo2);
		System.out.println(mo3);
		System.out.println(mo4);
		return;
	}
}
