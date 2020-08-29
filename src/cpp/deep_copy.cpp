#include <iostream>
using namespace std;

// class B that contains an integer value
class B
{
	public: 
		B(int i);
		int get_value() const;
		void change(int i);
	protected:
		int b;
};

// class A that contains a pointer to B object
class A
{
	public:
	       	A(B* b);
		// copy constructor of A
		A(const A& a);
		int get_value() const;
		void change(int i);
	protected:
	       	B* ptr_b;
	
}
;




A::A(B* b)
{
	ptr_b = b;
}
int A::get_value() const 
{
	ptr_b->get_value();
}
void A::change(int i)
{
	ptr_b->change(i);
}
A::A(const A& a1)
{
	int i = a1.get_value();
	ptr_b = new B(i);
}

B::B(int i)
{
	b = i;
}

void B::change(int i)
{
	b = i;
}

int B::get_value() const
{
	return b;
}
int main(int argc, char* agrv[])
{
	B* b = new B(5);
	A a1(b);
	// define a2, a1 and a2 point to different object B
	A a2(a1);
	cout << "a1's b value " << a1.get_value() << endl; 
	a1.change(7);
	cout << "a1's b value changes to 7" << endl;
	cout << "a1's b value " << a1.get_value() << endl;
        // after a1's b value is changed, a2's b keep its original value	
	cout << "a2's b value " << a2.get_value() << endl; 
	return 0;
}
