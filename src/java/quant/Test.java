package quant;
import java.util.*;
public class Test 
{

	public static void testOption1() {
		System.out.println("Test European Call Option Pricing Model");
		OptionType type = OptionType.CALL;
		ExerciseStyle style = ExerciseStyle.EUROPEAN;
		double underlying = 36.0;
		double strike = 40.0;
		double riskFreeRate = 0.06;
		double volatility = 0.2;
		double time = 2.0;
		Option option = new Option(style,
				OptionType.CALL, underlying,
			strike, riskFreeRate, 
			volatility, time);
		System.out.println("=============================");
		System.out.println("Pricing Method 1: Black-Scholes Analytical: ");
		System.out.println("Option Type: " + type);
		System.out.println("Underlying Price: " + underlying);
		System.out.println("Strike Price: " + strike);
		System.out.println("Risk Free Interest Rate: " + riskFreeRate);
		System.out.println("Volatility: " + volatility);
		System.out.println("Expiring Time: "+ time + " years ");	
		BSMCalculator bsm = new BSMCalculator();
		double npv1 = bsm.calculate(option);
		System.out.println("Net Present Value: "+ npv1);
		System.out.println();	
		
		

		System.out.println("=============================");
		System.out.println("Pricing Method 2: Monte Carlo for Black-Scholes Model: ");
		System.out.println("Option Type: " + type);
		System.out.println("Underlying Price: " + underlying);
		System.out.println("Strike Price: " + strike);
		System.out.println("Risk Free Interest Rate: " + riskFreeRate);
		System.out.println("Volatility: " + volatility);
		System.out.println("Expiring Time: "+ time + " years ");	
		Process process = new BSMProcess(option.riskFreeRate,
                                option.volatility);
		MonteCarlo mc = new MonteCarlo(option,
			       10, 100000, process);
		mc.initialize();
		mc.run();
		mc.showResult();
		System.out.println();

		// Monte carlo for Heston, theta is set so the model is the same as BSM model
		double kappa = 1.0;
		double theta = volatility*volatility;
		double sigma = 0.001;
		double rho = 0.0;
		System.out.println("=============================");
		System.out.println("Pricing Method 3: Monte Carlo for Heston: ");
		System.out.println("Option Type: " + type);
		System.out.println("Underlying Price: " + underlying);
		System.out.println("Strike Price: " + strike);
		System.out.println("Risk Free Interest Rate: " + riskFreeRate);
		System.out.println("Volatility: " + volatility);
		System.out.println("Kappa: " + kappa);
		System.out.println("Theta: " + theta);
		System.out.println("Volatility of volatility: " + sigma);
		System.out.println("Process correlation: " + rho);
		System.out.println("Expiring Time: "+ time + " years ");	
		Process process2 = new HestonProcess(option.riskFreeRate,
                                option.volatility, 
				kappa,
				theta,
			       	sigma,
				rho);
	
		MonteCarlo mc2 = new MonteCarlo(option,
			       10, 100000, process2);
		mc2.initialize();
		mc2.run();
		mc2.showResult();
		System.out.println();


		// Monte carlo for Heston, theta is set so the model is different from BSM model
		kappa = 1.0;
		theta = 0.039;
		sigma = 0.001;
		rho = 0.0;
		System.out.println("=============================");
		System.out.println("Pricing Method 4: Monte Carlo for Heston: ");
		System.out.println("Option Type: " + type);
		System.out.println("Underlying Price: " + underlying);
		System.out.println("Strike Price: " + strike);
		System.out.println("Risk Free Interest Rate: " + riskFreeRate);
		System.out.println("Volatility: " + volatility);
		System.out.println("Kappa: " + kappa);
		System.out.println("Theta: " + theta);
		System.out.println("Volatility of volatility: " + sigma);
		System.out.println("Process correlation: " + rho);
		System.out.println("Expiring Time: "+ time + " years ");	
		process2 = new HestonProcess(option.riskFreeRate,
                                option.volatility, 
				kappa,
				theta,
			       	sigma,
				rho);
	
		mc2 = new MonteCarlo(option,
			       10, 100000, process2);
		mc2.initialize();
		mc2.run();
		mc2.showResult();
		System.out.println();
	
		// Bates process, lambda, nu, delta are set to 0 to reproduce Heston 	
		kappa = 1;
		theta = 0.04;
		sigma = 0.001;
		double lambda = 0;
		double nu = 0;
		double delta = 0;
		System.out.println("=============================");
		System.out.println("Pricing Method 5: Monte Carlo for Bates: ");
		System.out.println("Option Type: " + type);
		System.out.println("Underlying Price: " + underlying);
		System.out.println("Strike Price: " + strike);
		System.out.println("Risk Free Interest Rate: " + riskFreeRate);
		System.out.println("Volatility: " + volatility);
		System.out.println("Kappa: " + kappa);
		System.out.println("Theta: " + theta);
		System.out.println("Volatility of volatility: " + sigma);
		System.out.println("Process correlation: " + rho);
		System.out.println("Lambda: " + lambda);
		System.out.println("Nu: " + nu);
		System.out.println("Delta: " + delta);
		System.out.println("Expiring Time: "+ time + " years ");	
		Process process3 = new BatesProcess(option.riskFreeRate,
                                option.volatility, 
				kappa,  //kappa
				option.volatility*option.volatility, //theta
			       	sigma, //sigma
				rho, // rho
				lambda,  // lambda
				nu, // nu
				delta); // delta

	
		MonteCarlo mc3 = new MonteCarlo(option,
			       10, 100000, process3);
		mc3.initialize();
		mc3.run();
		mc3.showResult();
		System.out.println();

		// Bates process, lambda, nu, delta are set to have jumps 	
		kappa = 1;
		theta = 0.04;
		sigma = 0.001;
		lambda = 1;
		nu = 0.01;
		sigma = 0.01;
		System.out.println("=============================");
		System.out.println("Pricing Method 6: Monte Carlo for Bates: ");
		System.out.println("Option Type: " + type);
		System.out.println("Underlying Price: " + underlying);
		System.out.println("Strike Price: " + strike);
		System.out.println("Risk Free Interest Rate: " + riskFreeRate);
		System.out.println("Volatility: " + volatility);
		System.out.println("Kappa: " + kappa);
		System.out.println("Theta: " + theta);
		System.out.println("Volatility of volatility: " + sigma);
		System.out.println("Process correlation: " + rho);
		System.out.println("Lambda: " + lambda);
		System.out.println("Nu: " + nu);
		System.out.println("Delta: " + delta);
		System.out.println("Expiring Time: "+ time + " years ");	
		process3 = new BatesProcess(option.riskFreeRate,
                                option.volatility, 
				kappa,  //kappa
				option.volatility*option.volatility, //theta
			       	sigma, //sigma
				rho, // rho
				lambda,  // lambda
				nu, // nu
				delta); // delta

	
		mc3 = new MonteCarlo(option,
			       10, 100000, process3);
		mc3.initialize();
		mc3.run();
		mc3.showResult();
		System.out.println();
		
		style = ExerciseStyle.AMERICAN;
		type = OptionType.CALL;
		underlying = 36.0;
		strike = 40.0;
		riskFreeRate = 0.06;
		volatility = 0.2;
		time = 2.0;
		option = new Option(style,
				type, underlying,
			strike, riskFreeRate, 
			volatility, time);
		
		System.out.println("=============================");
		System.out.println("Pricing Method 7: Monte Carlo for Black-Scholes Model for American Call: ");
		System.out.println("Exercise Style: "+ style);
		System.out.println("Option Type: " + type);
		System.out.println("Underlying Price: " + underlying);
		System.out.println("Strike Price: " + strike);
		System.out.println("Risk Free Interest Rate: " + riskFreeRate);
		System.out.println("Volatility: " + volatility);
		System.out.println("Expiring Time: "+ time + " years ");	
		process = new BSMProcess(option.riskFreeRate,
                                option.volatility);
		mc = new MonteCarlo(option,
			       10, 100000, process);
		mc.initialize();
		mc.run();
		mc.showResult();
		System.out.println();
		
		style = ExerciseStyle.EUROPEAN;
		type = OptionType.PUT;
		underlying = 36.0;
		strike = 40.0;
		riskFreeRate = 0.06;
		volatility = 0.2;
		time = 2.0;
		option = new Option(style,
				type, underlying,
			strike, riskFreeRate, 
			volatility, time);
		System.out.println("=============================");
		System.out.println("Pricing Method 8: Black-Scholes Analytical for European Put: ");
		System.out.println("Exercise Style: " + style);
		System.out.println("Option Type: " + type);
		System.out.println("Underlying Price: " + underlying);
		System.out.println("Strike Price: " + strike);
		System.out.println("Risk Free Interest Rate: " + riskFreeRate);
		System.out.println("Volatility: " + volatility);
		System.out.println("Expiring Time: "+ time + " years ");	
		double npv2 = bsm.calculate(option);
		System.out.println("Net Present Value: "+ npv2);
		System.out.println();	
		
		
		System.out.println("=============================");
		System.out.println("Pricing Method 9: Monte Carlo for Black-Scholes Model for European Put: ");
		System.out.println("Exercise Style: " + style);
		System.out.println("Option Type: " + type);
		System.out.println("Underlying Price: " + underlying);
		System.out.println("Strike Price: " + strike);
		System.out.println("Risk Free Interest Rate: " + riskFreeRate);
		System.out.println("Volatility: " + volatility);
		System.out.println("Expiring Time: "+ time + " years ");	
		process = new BSMProcess(option.riskFreeRate,
                                option.volatility);
		mc = new MonteCarlo(option,
			       10, 100000, process);
		mc.initialize();
		mc.run();
		mc.showResult();
		System.out.println();
		
		style = ExerciseStyle.AMERICAN;
		type = OptionType.PUT;
		option = new Option(style,
				type, underlying,
			strike, riskFreeRate, 
			volatility, time);
		System.out.println("=============================");
		System.out.println("Pricing Method 10: Monte Carlo for Black-Scholes Model for American Put: ");
		System.out.println("Exercise Style: " + style);
		System.out.println("Option Type: " + type);
		System.out.println("Underlying Price: " + underlying);
		System.out.println("Strike Price: " + strike);
		System.out.println("Risk Free Interest Rate: " + riskFreeRate);
		System.out.println("Volatility: " + volatility);
		System.out.println("Expiring Time: "+ time + " years ");	
		process = new BSMProcess(option.riskFreeRate,
                                option.volatility);
		mc = new MonteCarlo(option,
			       100, 50000, process);
		mc.initialize();
		mc.run();
		mc.showResult();
		System.out.println();
	}

	public static void main(String[] args) {
		testOption1();
//		Option option = new Option(OptionType.CALL, 36.0,
//			40.0, 0.06, 
//			0.2, 2.0);
		
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
