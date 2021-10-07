#include "circlelist.h"
#include <string>

// This program makes a few simple checks on you assignment 1 program
// In particular, it checks whether front(), back(), advance(), add(), remove(),
// empty() and the default constructor operate correctly on two simple lists.

int main(){
  // checks with a circular list of 4 integers
  cout << "create list l1 of integers with default constructor" << endl;
  CircleList<int> l1;
  if (!l1.empty()){
    cout << "ERROR: l1.empty()=false" << endl;
    return EXIT_FAILURE;
  }
  
  cout << "add 1,2,3,4 to l1" << endl;
  l1.add(1); l1.add(2); l1.add(3); l1.add(4);  
  if(l1.back()!=1){
  	cout << "ERROR: l1.back()=" << l1.back() << ". It should be 1." << endl;
    return EXIT_FAILURE;
  }
  if(l1.front()!=4){
  	cout << "ERROR: l1.front()=" << l1.front() << ". It should be 4." << endl;
    return EXIT_FAILURE;
  }
  
  cout << "advance" << endl;
  l1.advance();
  if(l1.back()!=4){
  	cout << "ERROR: l1.back()=" << l1.back() << ". It should be 4." << endl;
    return EXIT_FAILURE;
  }
  
  cout << "remove two nodes" << endl;
  l1.remove(); l1.remove();
  if(l1.back()!=4){
  	cout << "ERROR: l1.back()=" << l1.back() << ". It should be 4." << endl;
    return EXIT_FAILURE;
  }
  if(l1.front()!=1){
  	cout << "ERROR: l1.front()=" << l1.front() << ". It should be 1." << endl;
    return EXIT_FAILURE;
  }

  cout << "remove one node" << endl;
  l1.remove();
  if(l1.back()!=4){
  	cout << "ERROR: l1.back()=" << l1.back() << ". It should be 4." << endl;
    return EXIT_FAILURE;
  }
  if(l1.front()!=4){
  	cout << "ERROR: l1.front()=" << l1.front() << ". It should be 4." << endl;
    return EXIT_FAILURE;
  }
  if (l1.empty()){
    cout << "ERROR: l1.empty()=true" << endl;
    return EXIT_FAILURE;
  }

  cout << "remove last node" << endl;
  l1.remove();
  if (!l1.empty()){
    cout << "ERROR: l1.empty()=false" << endl;
    return EXIT_FAILURE;
  }

  // checks with a circular list of 3 strings  
  cout << endl << "create a list l2 of strings with default constructor" << endl;
  CircleList<string> l2;
  
  cout << "add \"list\", \"circular\", and \"A\"" << endl;
  l2.add("list");
  l2.add("circular");
  l2.add("A");

  cout << "advance" << endl;
  l2.advance();
  if(l2.front()!="circular"){
  	cout << "ERROR: l2.front()=\"" << l2.front() << "\". It should be \"circular\"." << endl;
    return EXIT_FAILURE;
  }  
  
  cout << l2 << endl;
  cout << "------------- Your program passed this test." << endl;
  return EXIT_SUCCESS;
}

