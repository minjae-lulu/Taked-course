#include "circlelist.h"
#include <string>
int main(){
    CircleList<int> l1;
    l1.add(1);
    l1.add(3);
    l1.add(5);
    l1.add(7); 
    l1.add(11); 
    
    CircleList<int> l2;
    l2.add(1);
    l2.add(3);
    l2.add(5);
    l2.add(7); 
    l2.add(11); 

    // cout <<"front is : " << l1.front() << '\n';
    // cout <<"back is : " << l1.back() << '\n';
    cout << "element is: " << l1 << '\n';
    cout << "element is: " << l2 << '\n';

    cout << "test equal: " << (l1 == l2) << '\n';
    // l1.advance();
    // cout << "element is: " << l1 << '\n';
    // l1.advance();
    // cout << "element is: " << l1 << '\n';

    // cout << "test: " << l1.find(11) << '\n';
}

