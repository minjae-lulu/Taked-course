
//I use array and sort() find big value. I use clear(), ignore() 

#include <iostream>
#include <climits>

int main(){
    using namespace std;
    long a;
    double b,c;
    double arr[3];

    cin >> a;
    cin.clear();
    cin.ignore(SSIZE_MAX,'\n');

    cin >> b;
    cin.clear();
    cin.ignore(SSIZE_MAX,'\n');
    
    cin >> c;
    cin.clear();
    cin.ignore(SSIZE_MAX,'\n');

    arr[0]=a;
    arr[1]=b;
    arr[2]=c;

    sort(arr,arr+3);
    
    if((arr[0] == 0) && (arr[1] == 0) && (arr[2] ==0)){
        cout << "Bad input for all 3 values" << endl;
        return 0;
    }
    if((arr[2] == 0) && (arr[1] !=0) && (arr[0] !=0)){
        cout << arr[1] << endl;
    }
    if((arr[2] == 0) && (arr[1] ==0) && (arr[0] !=0)){
        cout << arr[0] << endl;
    }
    else{
        cout << arr[2] << endl;
    }
    return 0;
}