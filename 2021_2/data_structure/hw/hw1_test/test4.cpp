#include "circlelist.h"
#include <string>
#include <iostream>
#include <fstream>

// This program checks the two overloaded operators

int main(){
  // checks with a circular list of 4 integers
  ofstream out("log4.txt");
  cout << "log is written in log4.txt\n";
  
  out << "----------------------- Starting test4.cpp ----------------------- \n";
  out << "create lists l1 and l2 of integers with default constructor\n";
  CircleList<int> l1;
  CircleList<int> l2;
 
  out << "fill l1 and l2 with the same 100 random numbers\n";
  for(int i=0;i<100;i++){
 	int r=rand() % 100;
  	l1.add(r);
  	l2.add(r);
  } 
  if (l1==l2)
    out << "SUCCESS: l1==l2\n";
  else 
    out << "ERROR: l1!=l2\n";

  if (l1==l1)
    out << "SUCCESS: l1==l1\n";
  else 
    out << "ERROR: l1!=l1\n";


  out << "modify a random element of l1" << endl;
  int r=rand() % 100;
  for(int i=0;i<100;i++){
    if(i==r){
      l1.remove();
      l1.add(-1);
    }
    l1.advance();
  }
  if (l1==l2)
    out << "ERROR: l1!=l2\n";
  else 
    out << "SUCCESS: l1!=l2\n";

  out << "create empty list l3 and add 45,3,-7,124" << endl;
  CircleList<int> l3;
  l3.add(45); l3.add(3); l3.add(-7); l3.add(124);
  out << "write l3 into a string s through <<" << endl;
  
  freopen("temp.txt","w",stdout);    
  cout << l3 << endl;
  fclose (stdout);
  freopen ("/dev/tty", "a", stdout);
  ifstream infile("temp.txt");
  string s;
  getline(infile, s);
  infile.close();
  
  out << s << " your output" << endl;
  string rs="124 -7 3 45";
  out << rs << " desired output" << endl;
  if (s==rs)
    out << "SUCCESS\n";
  else
    out << "ERROR\n";

  out << "----------------------- Completed test4.cpp ----------------------- " << endl;
  out.close();
  return EXIT_SUCCESS;
}

