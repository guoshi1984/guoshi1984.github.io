# define java compiler
JC = javac

#define a makefile varible 
JFLAGS = -g -d bin

default:
	$(JC) $(JFLAGS) src/Test.java src/Option.java src/MonteCarlo.java src/Process.java src/NormalDistribution.java src/Path.java src/BSMCalculator.java src/HestonCalculator.java src/HestonProcess.java src/BatesProcess.java src/PoissonDistribution.java src/SampleAccumulator.java src/BSMProcess.java src/math/MatrixAlgebra.java src/math/Complex.java
clean:
	$(RM) bin/*.class
	$(RM) bin/math/*.class
