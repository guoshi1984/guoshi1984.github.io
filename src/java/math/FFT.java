public class FFT {
	public static final double PI = 3.1415926535897;
	// separate the waveform to even indices and ood indices
	// input: a[0] a[1] a[2] a[3]...a[n]
	// output: a[0] a[2] a[4]....a[1] a[3]...
	private static void separate(Complex[] a, int start, int length) {
		Complex[] b = new Complex[length/2];
		for(int i = 0; i < length/2; i++) 
			b[i] = a[start + i*2 + 1];
		for(int i = 0; i < length/2; i++)
			a[start + i] = a[start + i*2];
		for(int i = 0; i < length/2; i++)
			a[start + i + length/2] = b[i];
	}

	public static void doFFT(Complex[] X, int start, int length) {
        	if (Integer.highestOneBit(length) !=  length) {
            		throw new RuntimeException("The number of samples is not a power of 2");
        	}

		if(length < 2) {
		} else {
			separate(X, start, length);
			//System.out.println("start "+ start);
			//System.out.println("length "+ length);
			//printWaveForm(X);
			doFFT(X, start, length/2);
			doFFT(X, start+length/2, length/2);
			//System.out.println("length: " + length/2);
			//printWaveForm(X);
			for(int k = 0; k < length/2; k++) {
				Complex e = X[start + k];
				Complex o = X[start + k + length/2];
				Complex w = new Complex(0, -2.*PI*k/length);
				w = w.exp();
				X[start + k] = e.plus(w.times(o));
				X[start + k + length/2] = e.minus(w.times(o));
			}
			//System.out.println(" FFT " + " start " + start + " length "+ length );
			//printWaveForm(X);
		}
	}
	
	public static void printWaveForm(Complex[] x) {
		for(int i = 0; i < x.length; i++) 
			System.out.println(i + " " + x[i]);
	}

	public static void test1() {
		final int N = 16;
		Complex[] x = new Complex[N];
		for(int i = 0; i < N; i++)
			x[i] = new Complex(Math.sin(2*PI*i/N), 0);
		System.out.println("Original WaveForm:");
		printWaveForm(x);
		doFFT(x, 0, N);
		System.out.println("Spectrum:");
		printWaveForm(x);

	}	

	
	public static void main(String[] args) {
		test1();		
		return;
	}

}
