package math;
import java.util.Objects;
public class Complex {
	private final double real;   // the real part
	private final double imag;   // the imaginary part
	
	// create a new object with the given real and imaginary parts
	public Complex(double real, double imag) {
	    this.real = real;
	    this.imag = imag;
	}
	
	// return a string representation of the invoking Complex object
	public String toString() {
	    if (this.imag == 0) return this.real + "";
	    if (this.real == 0) return this.imag + "i";
	    if (this.imag <  0) return this.real + " - " + (-this.imag) + "i";
	    return this.real + " + " + this.imag + "i";
	}
	
	// return abs/modulus/magnitude
	public double mod() {
	    return Math.hypot(real, imag);
	}
	
	// return angle/phase/argument, normalized to be between -pi and pi
	public double phase() {
	    return Math.atan2(imag, real);
	}
	
	// complex number plus a complex number
	// return a new Complex object whose value is (this + b)
	public Complex plus(Complex b) {
	    Complex a = this;             // invoking object
	    double real = a.real + b.real;
	    double imag = a.imag + b.imag;
	    return new Complex(real, imag);
	}
	
	// complex number plus a real number
	// return a new Complex object whose value is (this + b)
	public Complex plus(double b) {
	    Complex a = this;             // invoking object
	    double real = a.real + b;
	    double imag = a.imag;
	    return new Complex(real, imag);
	}

	// return a new Complex object whose value is (this - b)
	public Complex minus(Complex b) {
	    Complex a = this;
	    double real = a.real - b.real;
	    double imag = a.imag - b.imag;
	    return new Complex(real, imag);
	}
	
	// complex number times a complex number return a new Complex object whose value is (this * b)
	public Complex times(Complex b) {
	    Complex a = this;
	    double real = a.real * b.real - a.imag * b.imag;
	    double imag = a.real * b.imag + a.imag * b.real;
	    return new Complex(real, imag);
	}
	
	// complex number times a real number return a new Complex object whose value is (this * b)
	public Complex times(double b) {
		Complex a = this;
	    	double real = a.real * b;
	    	double imag = a.imag * b;
		return new Complex(real, imag);
	}

	// return a new object whose value is (this * alpha)
	public Complex scale(double alpha) {
	    return new Complex(alpha * this.real, alpha * this.imag);
	}
	
	// return a new Complex object whose value is the conjugate of this
	public Complex conjugate() {
	    return new Complex(this.real, -this.imag);
	}
	
	// return a new Complex object whose value is the reciprocal of this
	public Complex reciprocal() {
	    double scale = this.real*this.real + this.imag*this.imag;
	    return new Complex(this.real / scale, -this.imag / scale);
	}
	
	// return the real or imaginary part
	public double getReal() { return this.real; }
	public double getImag() { return this.imag; }
	
	// return a / b
	public Complex divides(Complex b) {
	    Complex a = this;
	    return a.times(b.reciprocal());
	}
	
	// return the sqrt
	public Complex sqrt() {
		double r = Math.sqrt(this.mod());
	        double phase = this.phase()/2;
	        return new Complex(r*Math.cos(phase), r*Math.sin(phase));
	}

	// return a new Complex object whose value is the complex exponential of this
	public Complex exp() {
	    return new Complex(Math.exp(this.real) * Math.cos(this.imag), Math.exp(this.real) * Math.sin(this.imag));
	}
	
	public Complex log() {
	    return new Complex(Math.log(this.mod()), this.phase());
	}
	
	// return a new Complex object whose value is the complex sine of this
	public Complex sin() {
	    return new Complex(Math.sin(this.real) * Math.cosh(this.imag), Math.cos(this.real) * Math.sinh(this.imag));
	}
	
	// return a new Complex object whose value is the complex cosine of this
	public Complex cos() {
	    return new Complex(Math.cos(this.real) * Math.cosh(this.imag), -Math.sin(this.real) * Math.sinh(this.imag));
	}
	
	// return a new Complex object whose value is the complex tangent of this
	public Complex tan() {
	    return sin().divides(cos());
	}
	
	
	
	// a static version of plus
	public static Complex plus(Complex a, Complex b) {
	    double real = a.real + b.real;
	    double imag = a.imag + b.imag;
	    Complex sum = new Complex(real, imag);
	    return sum;
	}
	
	// See Section 3.3.
	public boolean equals(Object x) {
	    if (x == null) return false;
	    if (this.getClass() != x.getClass()) return false;
	    Complex that = (Complex) x;
	    return (this.real == that.real) && (this.imag == that.imag);
	}
	
	// See Section 3.3.
	public int hashCode() {
	    return Objects.hash(this.real, this.imag);
	}
	
	// sample client for testing
	public static void main(String[] args) {
	    Complex a = new Complex(5.0, 6.0);
	    Complex b = new Complex(-3.0, 4.0);
	
	    System.out.println("a            = " + a);
	    System.out.println("b            = " + b);
	    System.out.println("Re(a)        = " + a.getReal());
	    System.out.println("Im(a)        = " + a.getImag());
	    System.out.println("b + a        = " + b.plus(a));
	    System.out.println("a - b        = " + a.minus(b));
	    System.out.println("a * b        = " + a.times(b));
	    System.out.println("b * a        = " + b.times(a));
	    System.out.println("a / b        = " + a.divides(b));
	    System.out.println("(a / b) * b  = " + a.divides(b).times(b));
	    System.out.println("conj(a)      = " + a.conjugate());
	    System.out.println("|a|          = " + a.mod());
	    System.out.println("tan(a)       = " + a.tan());
	}

}
