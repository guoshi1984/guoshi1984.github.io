# define java compiler
JC = javac

#define a makefile varible 
JFLAGS = -g

default:
	$(JC) $(JFLAGS)  Test.java Option.java MonteCarlo.java Process.java stat/NormalDistribution.java Path.java BSMCalculator.java HestonCalculator.java HestonProcess.java BatesProcess.java stat/PoissonDistribution.java SampleAccumulator.java ControlSampleAccumulator.java BSMProcess.java math/MatrixAlgebra.java math/Complex.java
clean:
	$(RM) *.class
	$(RM) math/*.class
	$(RM) stat/*.class
