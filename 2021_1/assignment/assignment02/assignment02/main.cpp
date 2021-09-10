// 20161190 leeminjae / include iomanip to align lines, and header files to use functions. 
// After receving the integer k value, the probability was calculated using values from 1 to k 


#include <iostream>
#include <iomanip>
#include "functions.h"
using namespace std;

int main()
{
    unsigned int k;
    cin >> k;

    if(!cin){
        cin.clear();
        cin.ignore(SSIZE_MAX,'\n');
        return 0;
    }

    for(int i=1; i<=k; i++){
        cout <<setw(2)<< i << "  " <<setw(10)<<fixed<<setprecision(7)<<probability(k,i) << endl;
    }
    return 0;
}