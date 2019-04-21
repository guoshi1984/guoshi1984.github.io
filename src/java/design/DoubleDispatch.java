public class DoubleDispatch
{
	static abstract class A
	{
		protected abstract void calculate(B b);

	}
	
	static class A1 extends A
	{
		protected void calculate(B b)
		{
			b.calculate(this);
		}
	}

	static class A2 extends A
	{
		protected void calculate(B b)
		{
			b.calculate(this);
		}
	}


	static abstract class B
	{
		abstract void calculate(A1 a1);
		abstract void calculate(A2 a2);
		
	}
	
	static class B1 extends B
	{
		@Override
		void calculate(A1 a1)
		{
			System.out.println("calculate A1 B1");
		}
		@Override
		void calculate(A2 a2)
		{
			System.out.println("calculate A2 B1");
		}
	}
	
	static class B2 extends B
	{
		@Override
		void calculate(A1 a1)
		{
			System.out.println("calculate A1 B2");
		}
		@Override
		void calculate(A2 a2)
		{
			System.out.println("calculate A2 B2");
		}

	}

	public static void main(String[] args)
	{
		A a = new A1();
		B b = new B1();
		a.calculate(b);
		a = new A2();
	        a.calculate(b);	
		return;
	}
}
