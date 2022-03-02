/* 
 I use while(1) want to receive until two valid values. if two valid values
 receive calcaulate perfect square. (using cmath and for loop)
*/ 

#include <iostream>
#include <cmath>
#include <climits>

int main()
{
    using namespace std;
    unsigned int  a,b;

    while(1)
    {
        cin >> a;
        if(cin.fail()){
            cin.clear();
            cin.ignore(SSIZE_MAX,'\n');
        }
        else
        {
            break;
        }
    }  
    while(1)
    {
        cin >> b;
        if(cin.fail()){
            cin.clear();
            cin.ignore(SSIZE_MAX,'\n');
        }
        else
        {
            break;
        }
    }  
    if (a<=b){
        for(int i=ceil(sqrt(a)); i<=sqrt(b); i++){
            cout << i*i << endl;
        }
    }
    else{
        return 0;
    }
    return 0;
}