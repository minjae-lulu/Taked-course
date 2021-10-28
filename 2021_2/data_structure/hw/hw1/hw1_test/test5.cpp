#include "circlelist.h"
#include <string>
#include <iostream>
#include <fstream>

// This program checks the two overloaded operators

int main(int argc, char *argv[]){
  // checks with a circular list of 4 integers
  ofstream out("log5.txt");
  cout << "log is written in log5.txt\n";
  
  if(argc!=3){
  	cout << "syntax: test5 [instance file] [solution file]\n";
  	return EXIT_SUCCESS;
  }
  ifstream instance (argv[1]);
  
  
  out << "----------------------- Starting test5.cpp ----------------------- \n";
  CircleList<int> l;

  for (string line; getline(instance, line); ){
    // cout << line << endl;
    string ins = line.substr(0, line.find(" "));
    if (ins=="add"){
      string s=line.substr(line.find(" ")+1);
      int a=stoi(s);
	  l.add(a);
    }
    if (ins=="advance"){
      l.advance();
    }
    if (ins=="remove"){
      l.remove();
    }
    if (ins=="reverse"){
      l.reverse();
    }
    if (ins=="find"){
      string s=line.substr(line.find(" ")+1);
      int a=stoi(s);
	  l.find(a);
    }
    // cout << l << endl;
  }
  ifstream solution (argv[2]);
  int b,f,s;
  string line;
  getline(solution,line);
  b=stoi(line);
  getline(solution,line);
  f=stoi(line);
  getline(solution,line);
  s=stoi(line);
  getline(solution,line);
  
  if(l.back()==b)
    out << "SUCCESS: back=" << b << endl;
  else
    out << "ERROR: back!=" << b << endl;
  if(l.front()==f)
    out << "SUCCESS: front=" << f << endl;
  else
    out << "ERROR: front!=" << f << endl;
  if(l.size()==s)
    out << "SUCCESS: size=" << s << endl;
  else
    out << "ERROR: size!=" << s << endl;

  freopen("temp.txt","w",stdout);    
  cout << l << endl;
  fclose (stdout);
  freopen ("/dev/tty", "a", stdout);
  ifstream infile("temp.txt");
  string str;
  getline(infile, str);
  infile.close();
  
  out << str << " your output" << endl;
  out << line << " desired output" << endl;
  if (line==str)
    out << "SUCCESS\n";
  else
    out << "ERROR\n";

  out << "----------------------- Completed test5.cpp ----------------------- " << endl;
  out.close();
  instance.close();
  solution.close();
  return EXIT_SUCCESS;
}

