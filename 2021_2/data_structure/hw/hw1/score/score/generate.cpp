#include "circlelist.h"
#include <string>
#include <iostream>
#include <fstream>

int main(int argc, char *argv[]){
   
  if(argc!=3){
  	cout << "syntax: test5 [instance file][size]\n";
  	return EXIT_SUCCESS;
  }
  ofstream instance (argv[1]);
  int n=stoi(argv[2]);
  
  CircleList<int> l;
  int current=1;
  
  for(int i=0;i<n;i++){
	  int r=rand()%6;
	  if(r<2){
	  	instance << "add " << current << endl;
	  	l.add(current++);
	  } 
	  else if(r<3){
	    instance << "advance\n";
	    l.advance();
	  }
 	  else if(r<4){
 	    if (l.empty())
 	      i--;
 	    else{
	      instance << "remove\n";
	      l.remove();
	    }
	  }
 	  else if(r<5){
	    instance << "reverse\n";
	    l.reverse();
	  }
   	  else {
   	    int q=rand()%n;
	    instance << "find "<< q << endl;
	    l.find(q);
	  }
  }
  cout << l.back() << endl;
  cout << l.front() << endl;
  cout << l.size() << endl;
  cout << l;

  instance.close();
  return EXIT_SUCCESS;
}

