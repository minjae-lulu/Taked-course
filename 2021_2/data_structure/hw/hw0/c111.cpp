// Suggested solution to textbook exercise C-1.11
// We give two possible answers: recursive or iterative
#include <iostream>
  
using namespace std;
  
long gcd_r(long n,long m){   // 1st solution:
  if (m==0)                  // recursive computation
  	return n;
  return gcd_r(m,n%m);
}  

long gcd_i(long n,long m){   // 2nd solution:
  while(m!=0){               // iterative
    int t=n%m;
    n=m; m=t;
  }
  return n;
}  
  

int main()
{
  long n,m;                 
  cout << "n= "; cin >> n;         
  cout << "m= "; cin >> m;
  cout << "gcd_r= " << gcd_i(n,m) << endl;
  cout << "gcd_i= " << gcd_r(n,m) << endl;
  return 0;
}
