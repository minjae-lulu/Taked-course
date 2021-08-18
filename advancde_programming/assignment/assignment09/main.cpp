// 20161190 leeminjae this is main.cpp
#include <iostream>
#include "matrix_2_2.h"
using namespace std;

template <typename T> // make template triangular 
T triangular(T num){
    return num * (num + 1) / 2;
}

int main() {
   using namespace std;

   cout << triangular<int>(10) << endl;
   cout << triangular<matrix_2_2<int>>(10) << endl;
   matrix_2_2<double> m1 = 1.1;
   m1[0][1] = -1.5;
   m1[1][0] = -0.125;
   m1[0][0] += 0.5;
   m1 *= triangular<matrix_2_2<double>>(20);
   cout << m1 << endl;
   return 0;
}