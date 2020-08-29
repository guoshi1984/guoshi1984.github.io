#include <iostream> 
#include <memory> 
using namespace std; 
  
class A { 
public:
	A() {}
	~A() { cout << "delete A" << endl;}	
	
	const int i = 11;
	void show() 
    	{ 
        	cout << "A::show()" << endl; 
    	} 
}; 
  
int main() 
{
	// create a shared pointer p1 pointing to a new allocated object of A
	shared_ptr<A> p1(new A()); 
	// print out the address of object of A that p1 points to 
	cout << "p1's value: " << p1.get() << endl; 
    	p1->show(); 
	// create a shared pointer p2 and initialize it using p1, now p1 and p2 point to the same address
    	shared_ptr<A> p2(p1); 
    	p2->show(); 
	// print out the values of p1 and p2, it should be the same
    	cout << "p1's value: " << p1.get() << endl; 
    	cout << "p2's value: " << p2.get() << endl; 
  
    	// Returns the number of shared_ptr objects 
    	// referring to the same managed object. 
    	cout << "p1 use count: " << p1.use_count() << endl; 
    	cout << "p2 use count: " << p2.use_count() << endl; 
  
    	// Relinquishes ownership of p1 on the object 
    	// and pointer becomes NULL 
    	p1.reset();
	// get p1's value, should be NULL
    	cout << p1.get() << endl; 
	// get the number of share_ptr objects, should be 1 after p1 has been reset
    	cout << p2.use_count() << endl; 
    	
	cout << p2.get() << endl; 

	cout << "Demo raw pointer"<< endl;	
	A* a1 = new A();
	cout<< "a1 "  <<a1 << endl;
	cout<< "a1:i=" <<a1->i << endl;
	A* a2(a1);
	cout<< "a2 " <<a2 << endl;
	cout<< "a2:i=" <<a2->i << endl;
	delete a2;
	//a1 = NULL;
	cout<< "a1 " <<a1 << endl;
	// after the object of A has been deleted, value of a1' i is invalid
	cout<< "a1:i" <<a1->i << endl;
	cout<< "a2 " <<a2 << endl;
	//cout<< "a2:i=" <<a2->i << endl;
    	return 0; 
} 
