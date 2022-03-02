//  i contains a factorial function and a probability function.
// factorial function define using for loop 1 to n.
// probability function define using factorial and pow() from cmath library.

#include "functions.h"
#include <cmath> 

long long factorial(int n){
    long long ans = 1;
    for(int i=1; i<=n; i++){
        ans *= i;
    }
    return ans;
}

double probability(unsigned int k,int i){
    double val=0;

    val = 1 - factorial(k)/(pow(k,i)*factorial(k-i));
    val = val * 100;
    return val;
}