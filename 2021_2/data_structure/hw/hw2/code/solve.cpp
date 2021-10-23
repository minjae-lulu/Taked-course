#include "knight.h"
#include <string>


int main(int argc, char *argv[]){
  if(argc!=2){
  	cout << "syntax: solve [instance file name]\n";
  	return EXIT_SUCCESS;
  }
  Board C(argv[1]);
  bool result=knightTour(C);
  if (result)
    cout << C.getm() << endl << C.getn() << endl << C;
  else
    cout << 0 << endl;
  return EXIT_SUCCESS;
}

