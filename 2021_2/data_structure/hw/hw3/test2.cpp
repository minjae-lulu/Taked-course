// Checks I/O functions

#include "tree.h"

typedef typename Tree<int>::Position IntPos;
typedef typename Tree<string>::Position StringPos;

int main(){
  ofstream out("log2.txt");
  cout << "log is written in log2.txt\n";
  
  out << "----------------------- Starting test.cpp ----------------------- \n";

  out << "Create a tree T by inserting elements one by one.\n";

  Tree<int> T;
  IntPos p0=T.addRoot(10);
  IntPos p1=T.insertAt(p0,20);
  T.insertAt(p1,40);
  IntPos p3=T.insertAt(p1,50);
  T.insertAt(p3,70);
  T.insertAt(p3,80);
  IntPos p6=T.insertAt(p0,30);
  T.insertAt(p6,60);  
  out << "T.print()\n";
  
  out << "write T.print() into a string s\n";
  freopen("temp.txt","w",stdout);    
  T.print();
  fclose (stdout);
  freopen ("/dev/tty", "a", stdout);
  ifstream infile("temp.txt");
  string s;
  getline(infile, s);
  infile.close();
  
  out << "Result of T.print():\n";
  out << "\"" <<  s << "\""  << endl;
  out << "Desired result:\n";
  out << "\"10 20 40 50 70 80 30 60\"\n";
  if (s== "10 20 40 50 70 80 30 60" or s== "10 20 40 50 70 80 30 60 ")
    out << "SUCCESS\n\n";
  else 
    out << "ERROR\n\n";

  out << "Construct T5 from file instance/instance5.in\n";
  Tree<int> T5("instances/instance5.in");
  
  out << "write T5.print() into a string s\n";
  freopen("temp.txt","w",stdout);    
  T5.print();
  fclose (stdout);
  freopen ("/dev/tty", "a", stdout);
  infile.open("temp.txt");
  getline(infile, s);
  infile.close();
  
  out << "Result of T5.print():\n";
  out << "\"" <<  s << "\""  << endl;
  out << "Desired result:\n";
  out << "\"83 86 15 21 27 59 26 35 92 26\"\n";
  if (s== "83 86 15 21 27 59 26 35 92 26" or s== "83 86 15 21 27 59 26 35 92 26 ")
    out << "SUCCESS\n\n";
  else 
    out << "ERROR\n\n";
  
  out << "Construct the tree of strings T8 from file instance/instance8.in\n";
  Tree<string> T8("instances/instance8.in");
  
  Tree<string>::PositionList pl=T8.positions();
  if(*pl.front()=="This")
    out << "SUCCESS: root is \"This\"\n";
  else
    out << "ERROR: root not is \"This\"\n";
  if(*pl.back()=="tree.")
    out << "SUCCESS: last node is \"tree.\"\n";
  else
    out << "ERROR: last node is \"tree.\"\n";
  if(*pl.back().parent()=="a") 
    out << "SUCCESS: parent of last node is \"a\"\n";
  else
    out << "ERROR: parent of last node is not \"a\"\n";
  
  out << "write T8.print() into a string s\n";
  freopen("temp.txt","w",stdout);    
  T8.print();
  fclose (stdout);
  freopen ("/dev/tty", "a", stdout);
  infile=ifstream("temp.txt");
  getline(infile, s);
  infile.close();
  
  out << "Result of T8.print():\n";
  out << "\"" <<  s << "\""  << endl;
  out << "Desired result:\n";
  out << "\"This sentence is stored in a tree.\"\n";
  if (s== "This sentence is stored in a tree." or s== "This sentence is stored in a tree. ")
    out << "SUCCESS\n\n";
  else 
    out << "ERROR\n\n";
    
  out << "----------------------- Completed test.cpp ----------------------- " << endl;
  return EXIT_SUCCESS;
}

