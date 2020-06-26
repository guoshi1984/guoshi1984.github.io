package quant;
import java.util.*;
public class Test 
{

	public static void testOption1() {
		System.out.println("=============================");
		System.out.println("Test Option Pricing Model using Black-Scholes Model");
		System.out.println("=============================");
		
		//parameters for option
		OptionType type = OptionType.CALL;
		ExerciseStyle style = ExerciseStyle.EUROPEAN;
		
		double underlying = 44.0;
		double strike = 40.0;
		double riskFreeRate = 0.06;
		double volatility = 0.4;
		double time = 2;
			
		//paramters for MC
		int timeStepPerYear = 20;
		int SampleSize = 100000;	
		Option option = new Option(style,
				OptionType.CALL, underlying,
			strike, riskFreeRate, 
			volatility, time);
		System.out.println("=============================");
		System.out.println("Pricing task: European Call Option Black-Scholes Analytical: ");
		option.showInfo();
		BSMCalculator bsm = new BSMCalculator(option);
		double npv1 = bsm.calculate();
		System.out.println("Net Present Value: "+ npv1);
		System.out.println();	
		
		

		System.out.println("=============================");
		System.out.println("Pricing Task: European Call Option Black-Scholes Monte Carlo: ");
		option.showInfo();
		Process process = new BSMProcess(option.riskFreeRate,
                                option.volatility);
		MonteCarlo mc = new MonteCarlo(option,
			       timeStepPerYear, SampleSize, process);
		mc.initialize();
		mc.showInfo();
		mc.run();
		mc.showResult();
		System.out.println();

		
		style = ExerciseStyle.AMERICAN;
		type = OptionType.CALL;
		option = new Option(style,
				type, underlying,
			strike, riskFreeRate, 
			volatility, time);
		
		System.out.println("=============================");
		System.out.println("Pricing Task: American Call Option Black-Scholes Monte Carlo: ");
		option.showInfo();
		process = new BSMProcess(option.riskFreeRate,
                                option.volatility);
		mc = new MonteCarlo(option,
			       10, 100000, process);
		mc.initialize();
		mc.showInfo();
		mc.run();
		mc.showResult();
		System.out.println();
		
		style = ExerciseStyle.EUROPEAN;
		type = OptionType.PUT;
		option = new Option(style,
				type, underlying,
			strike, riskFreeRate, 
			volatility, time);
		System.out.println("=============================");
		System.out.println("Pricing Task: European Put Black-Scholes Analytical: ");
		option.showInfo();
		double npv2 = bsm.calculate();
		System.out.println("Net Present Value: "+ npv2);
		System.out.println();	
		
		
		System.out.println("=============================");
		System.out.println("Pricing Task: Eueopean Put Black-Scholes Monte Carlo: ");
		option.showInfo();
		process = new BSMProcess(option.riskFreeRate,
                                option.volatility);
		mc = new MonteCarlo(option,
			       timeStepPerYear, SampleSize, process);
		mc.initialize();
		mc.showInfo();
		mc.run();
		mc.showResult();
		System.out.println();
		
		timeStepPerYear = 100;
		style = ExerciseStyle.AMERICAN;
		type = OptionType.PUT;
		option = new Option(style,
				type, underlying,
			strike, riskFreeRate, 
			volatility, time);
		System.out.println("=============================");
		System.out.println("Pricing Task: American Put Black-Scholes Monte Carlo  ");
		option.showInfo();
		process = new BSMProcess(option.riskFreeRate,
                                option.volatility);
		mc = new MonteCarlo(option,
			       timeStepPerYear, SampleSize, process);
		mc.initialize();
		mc.showInfo();
		mc.run();
		mc.showResult();
		System.out.println();
	}

	public static void testOption2() {
		//parameters for option
		
		
		System.out.println("=============================");
		System.out.println("Test Option Pricing Model using Heston Model");
		System.out.println("=============================");
		OptionType type = OptionType.CALL;
		ExerciseStyle style = ExerciseStyle.EUROPEAN;
		
		double underlying = 100.0;
		double strike = 100.0;
		double riskFreeRate = 0.0;
		double volatility = 0.1;
		double time = 0.5;
		
	
			
		//paramters for MC
		int timeStepPerYear = 20;
		int SampleSize = 20000;	
		Option option = new Option(style,
				OptionType.CALL, underlying,
			strike, riskFreeRate, 
			volatility, time);
		System.out.println("Pricing task: European Call Option Black-Scholes Analytical: ");
		option.showInfo();
		BSMCalculator bsm = new BSMCalculator(option);
		double npv1 = bsm.calculate();
		System.out.println("Net Present Value: "+ npv1);
		System.out.println();	
		//paramters for heston model
		double kappa = 2.0;
		double theta = 0.01;
		double sigma = 0.1;
		double rho = 0.5;
		// Monte carlo for Heston, theta is set so the model is the same as BSM model
		System.out.println("=============================");
		System.out.println("Pricing Task: Monte Carlo for Heston: ");
		option.showInfo();
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
			       timeStepPerYear, SampleSize, process2);
		mc2.initialize();
		mc2.showInfo();
		mc2.run();
		mc2.showResult();
		System.out.println();


		// Monte carlo for Heston, theta is set so the model is different from BSM model
		System.out.println("=============================");
		System.out.println("Pricing Task: Monte Carlo for Heston: ");
		option.showInfo();
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
			       timeStepPerYear, SampleSize, process2);
		mc2.initialize();
		mc2.showInfo();
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
		System.out.println("Pricing Task: Monte Carlo for Bates: ");
		option.showInfo();
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
			       timeStepPerYear, SampleSize, process3);
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
		option.showInfo();
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
			       timeStepPerYear, SampleSize, process3);
		mc3.initialize();
		mc3.run();
		mc3.showResult();
		System.out.println();

	}

	public static void testOption3() {
		System.out.println("Test BSM characteristic function");
		
		OptionType type = OptionType.CALL;
		ExerciseStyle style = ExerciseStyle.EUROPEAN;
		
		double underlying = 100.0;
		double strike = 100.0;
		double riskFreeRate = 0.05;
		double volatility = 0.15;
		double time = 1;
		
		Option option = new Option(style,
				type, underlying,
			strike, riskFreeRate, 
			volatility, time);
		option.showInfo();

		BSMCalculator bsm = new BSMCalculator(option);
		System.out.println("NPV using BSM formula:  "+ bsm.calculate());
		System.out.println("NPV using Characteristic function: "+  bsm.calculateUsingCharacteristicFunction());
	
		type = OptionType.PUT;
		option = new Option(style,
				type, underlying,
			strike, riskFreeRate, 
			volatility, time);
		option.showInfo();

		bsm = new BSMCalculator(option);
		System.out.println("NPV using BSM formula:  "+ bsm.calculate());
		System.out.println("NPV using Characteristic function: "+  bsm.calculateUsingCharacteristicFunction());
	}
	public static void main(String[] args) {
		//testOption1();
		//testOption2();
		testOption3();
//		Option option = new Option(OptionType.CALL, 36.0,
//			40.0, 0.06, 
//			0.2, 2.0);
		
		/*double mo1 = 0;
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
		*/
		return;
	}
}
