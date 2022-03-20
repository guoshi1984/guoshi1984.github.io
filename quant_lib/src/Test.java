import java.util.*;
import math.*;
import stat.*;
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
		double riskFreeRate = 0.06;  //0.06;
		double volatility = 0.4;
		double time = 2;
			
		//paramters for MC
		int timeStepPerYear = 1;
		int euroSampleSize = 20000;
		int ameSampleSize = 50000;
		SampleAccumulatorType sEuroType = SampleAccumulatorType.CONTROL;	         	
		SampleAccumulatorType sAmeType = SampleAccumulatorType.RAW;	         	
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
			       timeStepPerYear, euroSampleSize, process,
			       sEuroType);
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
			       timeStepPerYear, ameSampleSize, process,
			       sAmeType);
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
		bsm = new BSMCalculator(option);
		double npv2 = bsm.calculate();
		System.out.println("Net Present Value: "+ npv2);
		System.out.println();	
		
		
		System.out.println("=============================");
		System.out.println("Pricing Task: Eueopean Put Black-Scholes Monte Carlo: ");
		option.showInfo();
		process = new BSMProcess(option.riskFreeRate,
                                option.volatility);
		mc = new MonteCarlo(option,
			       timeStepPerYear, euroSampleSize, process,
			       sEuroType);
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
			       timeStepPerYear, ameSampleSize, process,
			       sAmeType);
		mc.initialize();
		mc.showInfo();
		mc.run();
		mc.showResult();
		System.out.println("=============================");
		System.out.println();
	}

	public static void testOption2() {
		//parameters for option
		
		
		System.out.println("======================================");
		System.out.println("Test Option Pricing using Heston Model");
		System.out.println("======================================");
		System.out.println();
		OptionType type = OptionType.CALL;
		ExerciseStyle style = ExerciseStyle.EUROPEAN;
	
		// parameters for BSM model	
		double underlying = 44;
		double strike = 40;
		double riskFreeRate = 0.06;
		double volatility = 0.40;
		double time = 2;
		
	
		//paramters for heston model
		double kappa = 2.0;
		double theta = 0.16;
		double sigma = 0.1;
		double rho = -0.5;
			
		//paramters for MC
		int timeStepPerYear = 50;
		int SampleSize = 50000;	
		Option option = new Option(style,
				OptionType.CALL, underlying,
			strike, riskFreeRate, 
			volatility, time);
		System.out.println("Pricing task: European Call Option Analytical: ");
		option.showInfo();
		BSMCalculator bsm = new BSMCalculator(option);
		HestonCalculator heston = new HestonCalculator(option, kappa, theta, sigma, rho);
		heston.showInfo();
		double npv1 = bsm.calculate();
		System.out.println("Net Present Value using Analytical Formula: "+ npv1);
		System.out.println("Net Present Value using BSM Characteristic function: "+  bsm.calculateUsingCharacteristicFunction());
		System.out.println("Net Present Value using Heston Characteristic function: "+  heston.calculateUsingCharacteristicFunction());
		System.out.println();
		// Monte carlo for Heston, theta is set so the model is the same as BSM model
		System.out.println("Pricing Task: European Call Option Monte Carlo: ");
		option.showInfo();
		Process process2 = new HestonProcess(option.riskFreeRate,
                                option.volatility, 
				kappa,
				theta,
			       	sigma,
				rho);
		//process2.showInfo();	
		MonteCarlo mc2 = new MonteCarlo(option,
			       timeStepPerYear, SampleSize, process2,
			       SampleAccumulatorType.RAW);

		mc2.initialize();
		mc2.showInfo();
		mc2.run();
		mc2.showResult();
		System.out.println();

		System.out.println("Pricing task: European Put Option Analytical: ");
		type = OptionType.PUT;
		option = new Option(style,
				type, underlying,
			strike, riskFreeRate, 
			volatility, time);
		option.showInfo();

		heston = new HestonCalculator(option, kappa, theta, sigma, rho);
		heston.showInfo();
		bsm = new BSMCalculator(option);
		System.out.println("NPV using BSM formula:  "+ bsm.calculate());
		System.out.println("NPV using BSM Characteristic function: "+  bsm.calculateUsingCharacteristicFunction());
		System.out.println("Net Present Value using Heston Characteristic function: "+  heston.calculateUsingCharacteristicFunction());
		System.out.println();

		System.out.println("Pricing Task: European Put Option Monte Carlo: ");
		option.showInfo();
		//process2.showInfo();
		mc2 = new MonteCarlo(option,
			       timeStepPerYear, SampleSize, process2,
			       SampleAccumulatorType.RAW);
		mc2.initialize();
		mc2.showInfo();
		mc2.run();
		mc2.showResult();
		System.out.println();
		// Bates process, lambda, nu, delta are set to 0 to reproduce Heston 	
		/*
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
		*/
	}


	public static void testOption4() {
		double mo1 = 0;
		double mo2 = 0;
		double mo3 = 0;
		double mo4 = 0;
		int n = 100000;
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
		
	}

	public static void testOption5() {
		System.out.println("=============================");
		System.out.println("Test Option Pricing Model using Black-Scholes Model");
		System.out.println("=============================");
		
		//parameters for option
		OptionType type = OptionType.CALL;
		ExerciseStyle style = ExerciseStyle.EUROPEAN;
		
		double underlying = 36.0;
		double strike = 40.0;
		double riskFreeRate = 0.06;  //0.06;
		double volatility = 0.4;
		double time = 2;
			
		//paramters for MC
		int timeStepPerYear = 1;
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
		System.out.println("Pricing Task: European Call Option Black-Scholes Monte Carlo Using Raw Sampling: ");
		option.showInfo();
		Process process = new BSMProcess(option.riskFreeRate,
                                option.volatility);
		MonteCarlo mc = new MonteCarlo(option,
			       timeStepPerYear, SampleSize, process,
			       SampleAccumulatorType.RAW);
		mc.initialize();
		mc.showInfo();
		mc.run();
		mc.showResult();
		System.out.println();

		System.out.println("=============================");
		System.out.println("Pricing Task: European Call Option Black-Scholes Monte Carlo Using Control Sampling: ");
		option.showInfo();
		mc = new MonteCarlo(option,
			       timeStepPerYear, SampleSize, process,
			       SampleAccumulatorType.CONTROL);
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
		bsm = new BSMCalculator(option);
		double npv2 = bsm.calculate();
		System.out.println("Net Present Value: "+ npv2);
		System.out.println();	
		
		
		System.out.println("=============================");
		System.out.println("Pricing Task: Eueopean Put Black-Scholes Monte Carlo Using Raw Sampling: ");
		option.showInfo();
		process = new BSMProcess(option.riskFreeRate,
                                option.volatility);
		mc = new MonteCarlo(option,
			       timeStepPerYear, SampleSize, process,
			       SampleAccumulatorType.RAW);
		mc.initialize();
		mc.showInfo();
		mc.run();
		mc.showResult();
		System.out.println();
		
		
		System.out.println("=============================");
		System.out.println("Pricing Task: Eueopean Put Black-Scholes Monte Carlo Using Control Sampling: ");
		option.showInfo();
		mc = new MonteCarlo(option,
			       timeStepPerYear, SampleSize, process,
			       SampleAccumulatorType.CONTROL);
		mc.initialize();
		mc.showInfo();
		mc.run();
		mc.showResult();
		System.out.println();
		
	}

	public static void main(String[] args) {
//		testOption5();
		testOption1();
		for(int i = 0; i < 10; i++) {
			//testOption4();
			System.out.println();
		}
		return;
	}
}
