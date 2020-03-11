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
	shared_ptr<A> p1(new A()); 
    	cout << p1.get() << endl; 
    	p1->show(); 
    	shared_ptr<A> p2(p1); 
    	p2->show(); 
    	cout << p1.get() << endl; 
    	cout << p2.get() << endl; 
  
    	// Returns the number of shared_ptr objects 
    	// referring to the same managed object. 
    	cout << p1.use_count() << endl; 
    	cout << p2.use_count() << endl; 
  
    	// Relinquishes ownership of p1 on the object 
    	// and pointer becomes NULL 
    	p1.reset(); 
    	cout << p1.get() << endl; 
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
	a1 = NULL;
	cout<< "a1 " <<a1 << endl;
	cout<< "a1:i" <<a1->i << endl;
	cout<< "a2 " <<a2 << endl;
	//cout<< "a2:i=" <<a2->i << endl;
    	return 0; 
} 
