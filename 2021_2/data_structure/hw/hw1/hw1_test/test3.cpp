#include "circlelist.h"
#include <string>


// This program checks the 4 additional member functions of assignment 1:
// size(), find(), reverse() and the copy constructor

int main(){
  // checks with a circular list of 4 integers
  cout << "----------------------- Starting test3.cpp ----------------------- \n";
  cout << "create list l1 of integers with default constructor\n";
  CircleList<int> l1;
  if (l1.size()==0)
  	cout << "SUCCESS: size=0\n";
  else 
    cout << "ERROR: size!=0\n";
  
  cout << "l1.add(1)\n";
  l1.add(1);
  if(l1.size()==1)
  	cout << "SUCCESS: size=1\n";
  else 
    cout << "ERROR: size!=1\n";
    
  cout << "l1.add(2)\n";
  l1.add(2);
  if(l1.size()==2)
  	cout << "SUCCESS: size=2\n";
  else 
    cout << "ERROR: size!=2\n";

  cout << "l1.remove(); l1.advance(); l1.add(3); l1.add(4);\n";
  l1.remove(); l1.advance(); l1.add(3); l1.add(4);
  if(l1.size()==3)
  	cout << "SUCCESS: size=3\n";
  else 
    cout << "ERROR: size!=3\n";
  
  cout << "fills l1 with  0 1 10 11 ... 90 91 \n";
  l1.remove(); l1.remove(); l1.remove(); 
  for (int i=9;i>=0;i--){
    l1.add(10*i+1); l1.add(10*i);
  }
  cout << "find 50\n"; 
  if(l1.find(50)==true)
    cout << "SUCCESS: find(50)=true\n";
  else 
    cout << "ERROR: find(50)=false\n";
  if(l1.back()==50)
  	cout << "SUCCESS: back=50\n";
  else 
    cout << "ERROR: back!=50\n";
  if(l1.front()==51)
  	cout << "SUCCESS: front=51\n";
  else 
    cout << "ERROR: back!=51\n";

  cout << "find 52\n"; 
  if(l1.find(52)==false)
    cout << "SUCCESS: find(52)=false\n";
  else 
    cout << "ERROR: find(50)=true\n";
  if(l1.back()==50)
  	cout << "SUCCESS: back=50\n";
  else 
    cout << "ERROR: back!=50\n";
    
  cout << "fills l1 with 4 3 2 1\n";
  for(int i=0;i<20;i++)
    l1.remove();
  l1.add(1); l1.add(2); l1.add(3); l1.add(4);
  cout << "reverse\n";
  l1.reverse();
  for(int i=1;i<5;i++){
    if(l1.front()==i)
   	  cout << "SUCCESS: front=" << i << endl;
    else
      cout << "ERROR: front!=" << i << endl;
    cout << "advance\n";
    l1.advance();
  }
  
  cout << "fill l1 with 100 random numbers\n";
  l1.remove(); l1.remove(); l1.remove(); l1.remove();
  for(int i=0;i<100;i++)
  	l1.add(rand() % 100); 
  cout << "testing copy constructor\n";
  cout << "CircleList<int> l2=l1\n";
  CircleList<int> l2=l1;
  bool success=true;
  for(int i=1;i<=100;i++){
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
  int r=rand() % 100;
  for(int i=0;i<100;i++){
    if(i==r){
      l1.remove();
      l1.add(-1);
    }
    l1.advance();
  }
  success=false;
  for(int i=1;i<=100;i++){
    l1.advance(); l2.advance();
    if(l1.back()!=l2.back()){
      success=true;
    }
  }
  if (success==true)
    cout << "SUCCESS: l1 and l2 are now different\n";
  else
    cout << "ERROR: l1=l2\n";
  cout << "----------------------- Completed test3.cpp ----------------------- " << endl;
  return EXIT_SUCCESS;
}

