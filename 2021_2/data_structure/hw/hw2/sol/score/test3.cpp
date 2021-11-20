// Checks whether knightTour() returns false on negative instances (i.e. instances without a solution)

#include "knight.h"
#include <string>


int main(){
  cout << "----------------------- Starting test3.cpp ----------------------- \n";
  Board *B;
  for(int i=1;i<=5;i++){
    string s="instances/badinstance";
    string t;
    t=s+to_string(i)+".in";
    cout <<  "checking " << t <<endl;
    B=new Board(t);
    cout << *B << endl;  
    if (knightTour(*B))
      cout << "ERROR: knightTour should return false" << endl;
    else 
      cout << "SUCCESS" << endl;
  }

  cout << "----------------------- Completed test3.cpp ----------------------- " << endl;
  return EXIT_SUCCESS;
}


