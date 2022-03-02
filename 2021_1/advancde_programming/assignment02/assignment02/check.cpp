// check.cpp contains a main method and does unit testing. 
// it include functions.h and define check_f and check_p for unit testing.
// If all unit test passed, cout all test passed.

#include <iostream>
#include "functions.h"
using namespace std;
#define CHECK_F(x,y) if (factorial(x) !=y) {cout << "Factorial error occur" << endl;}
#define CHECK_P(a,b,c) if (probability(a,b) != c) {cout << "Probability error occur" << endl;}

int main()
{
    int n=0;
    CHECK_F(5,120);
    CHECK_F(8,40320);
    CHECK_F(12,479001600);

    CHECK_P(4,3,62.5);
    CHECK_P(4,2,25);
    CHECK_P(5,3,52);
    CHECK_P(5,5,96.16);
    CHECK_P(8,3,34.375);

    if(n == 0){
        cout << "All tests passed" << endl;
    }

    return 0;
}