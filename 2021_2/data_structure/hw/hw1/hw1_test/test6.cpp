#include "circlelist.h"
#include <string>


// This program checks the 4 additional member functions of assignment 1:
// size(), find(), reverse() and the copy constructor

int main(){
  
    CircleList<int> l1;
  
    for(int i=0;i<10;i++)
        l1.add(rand() % 10); 
  
    CircleList<int> l2=l1;

    // cout << l1 << '\n';
    // cout << l2 << '\n';


  bool success=true;
  for(int i=1;i<=10;i++){
    l1.advance(); l2.advance();
    if(l1.back()!=l2.back()){
      success=false;
      cout << "ERROR: " << i << "th elements are different\n";
      break;
    }
  }
  if (success==true)
    cout << "SUCCESS: l1=l2\n";
  
  cout << "modify a random element of l1" << endl;

  cout << '\n';

  int r=rand() % 10;
  cout << "test_ r val is: " << r << '\n';
  for(int i=0;i<10;i++){
    if(i==r){
      l1.remove();
      l1.add(-1);
      cout << "test_ add-1 and back is:  " << l1.back() << '\n';  
      cout << "test_ add-1 and front is:  " << l1.front() << '\n';  
    }
    l1.advance();
  }
  success=false;
  for(int i=1;i<=10;i++){
    l1.advance(); l2.advance();
    cout << "test_ l1 and l2 back is:  " << l1.back() << " and " << l2.back() << '\n';  
    if(l1.back()!=l2.back()){
      success=true;
    }
  }
  if (success==true)
    cout << "SUCCESS: l1 and l2 are now different\n";
  else
    cout << "ERROR: l1=l2\n";
  return EXIT_SUCCESS;
}

