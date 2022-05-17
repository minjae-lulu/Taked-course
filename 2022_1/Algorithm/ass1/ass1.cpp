#include <iostream>
#include <algorithm>
using namespace std;

int main(){
    cin.tie(0);
    cin.sync_with_stdio(0);
    
    int d1,d2;
    cin >> d1 >> d2;
    int dist = abs(d1-d2);
    cout << "dist is : " << dist << '\n';

    int flag = 0;
    for(int i=1; i< 1000000; i++){ // i< 범위는 dist 값에 따라 처리
        if(i*i >= 4 * dist){
            //cout << "right point is: " << i << '\n';
            flag = i-1; // consider sqrt(4y+1/4) - 1/2
            break;
        }
    }
    cout << "smallest number of step: " << flag;
}