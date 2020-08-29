#include<iostream>
using namespace std;
// implicit cast
void f1() {
	int a = 5;
	double b = 3.7;
	// truncation happens
	int c = a + b;
	double d = a + b;
	// cast an int to a char is problematic when int has more than 1 byte
	int e1 = 500;
	char c1 = e1;
	// it is OK when int is only 1 byte
	int e2 = 122;
	char c2 = e2;
	cout << "a + b: " << a + b << endl;
	cout << "c: " << c << endl;
	cout << "d: " << d << endl;
	cout << "c1: " << c1 << endl;
	cout << "c2: " << c2 << endl;
}

void f2() {
	int a = 5;
	double b = 3.7;
	// truncation happens
	int c = a + static_cast<int>(b);
	double d = a + b;
	// cast an int to a char is problematic when int has more than 1 byte
	int e1 = 500;
	char c1 = static_cast<char>(e1);
	// it is OK when int is only 1 byte
	int e2 = 122;
	char c2 = e2;
	cout << "a + b: " << a + b << endl;
	cout << "c: " << c << endl;
	cout << "d: " << d << endl;
	cout << "c1: " << c1 << endl;
	cout << "c2: " << c2 << endl;
}

int main(int argc, char* argv[]) {
	f1();
	f2();
	return 0;
}
