#include "circlelist.h"
#include <string>


// This program checks the basic functions of assignment 1
// In particular, it checks whether front(), back(), advance(), add(), remove(),
// empty() and the default constructor operate correctly on two simple lists.

int main(){
  // checks with a circular list of 4 integers
  cout << "----------------------- Starting test1.cpp ----------------------- \n";
  cout << "create list l1 of integers with default constructor\n";
  CircleList<int> l1;
  if (l1.empty())
  	cout << "SUCCESS: l1.empty()=false\n";
  else 
    cout << "ERROR: l1.empty()=false\n";
  
  cout << "add 1,2,3,4 to l1" << endl;
  l1.add(1); l1.add(2); l1.add(3); l1.add(4);  
  if(l1.back()==1)
  	cout << "SUCCESS: l1.back()=1\n";
  else
  	cout << "ERROR: l1.back()=" <<  l1.back() << ". It should be 1.\n";

  if(l1.front()==4){
  	cout << "SUCCESS: l1.front()=4\n";
  }
  else
  	cout << "ERROR: l1.front()=" << l1.front() << ". It should be 4." << endl;
  
  cout << "advance" << endl;
  l1.advance();
  if(l1.back()==4)
  	cout << "SUCCESS: l1.back()=4 \n";
  else
  	cout << "ERROR: l1.back()=" << l1.back() << ". It should be 4." << endl;
  
  cout << "remove two nodes" << endl;
  l1.remove(); l1.remove();
  if(l1.back()==4)
  	cout << "SUCCESS: l1.back()=4\n";
  else
  	cout << "ERROR: l1.back()=" << l1.back() << ". It should be 4." << endl;
  if(l1.front()==1)
  	cout << "SUCCESS: l1.front()=1\n";
  else 
  	cout << "ERROR: l1.front()=" << l1.front() << ". It should be 1." << endl;

  cout << "remove one node" << endl;
  l1.remove();
  if(l1.back()==4)
    cout << "SUCCESS: l1.back()=4\n";
  else
  	cout << "ERROR: l1.back()=" << l1.back() << ". It should be 4." << endl;
  if(l1.front()==4)
  	cout << "SUCCESS: l1.front()=4\n";
  else
  	cout << "ERROR: l1.front()=" << l1.front() << ". It should be 4." << endl;
  if (l1.empty())
    cout << "ERROR: l1.empty()=true" << endl;
  else
    cout << "SUCCESS: l1.empty()=true\n";

  cout << "remove last node" << endl;
  l1.remove();
  if (l1.empty())
    cout << "SUCCESS: l1 is empty\n";
  else
    cout << "ERROR: l1.empty()=false" << endl;

  // checks with a circular list of 3 strings  
  cout << "create a list l2 of strings with default constructor" << endl;
  CircleList<string> l2;
  
  cout << "add \"list\", \"circular\", and \"A\"" << endl;
  l2.add("list");
  l2.add("circular");
  l2.add("A");

  cout << "advance" << endl;
  l2.advance();
  if(l2.front()=="circular")
    cout << "SUCCESS: l2.front=\"circular\"\n";
  else
  	cout << "ERROR: l2.front()=\"" << l2.front() << "\". It should be \"circular\"." << endl;
  
  //  Checking if back(), front() and remove() handle exceptions properly
  
  try{
    cout << "l1.remove()\n";
    l1.remove();
    cout << "ERROR: l1.remove() did not throw a runtime_error\n"; 
  }catch(runtime_error){
    cout << "SUCCESS: l1.remove() threw a runtime_error\n";
  }
  try{
    cout << "l1.back()\n";
    l1.back();
    cout << "ERROR: l1.back() did not throw a runtime_error\n"; 
  }catch(runtime_error){
    cout << "SUCCESS: l1.back() threw a runtime_error\n";
  }
  try{
    cout << "l1.front()\n";
    l1.back();
    cout << "ERROR: l1.front() did not throw a runtime_error\n"; 
  }catch(runtime_error){
    cout << "SUCCESS: l1.front() threw a runtime_error\n";
  }
  
  cout << "----------------------- Completed test1.cpp ----------------------- " << endl;
  return EXIT_SUCCESS;
}

