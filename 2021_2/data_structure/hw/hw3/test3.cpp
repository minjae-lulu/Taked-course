#include "tree.h"

// This program checks correctness of your solution to vertex cover for 8 instance files

typedef typename Tree<int>::Position IntPos;
typedef typename Tree<string>::Position StringPos;

int main(){
  cout << "----------------------- Starting test3.cpp ----------------------- \n\n";
  
  Tree<int> * T;
  int res[]={0,7,9,100,11,180,1608,3260};
  string filename;
  for(int i=0; i<8;i++){
    filename="instances/instance"+to_string(i)+".in";
    cout << "reading " << filename << endl;
    T = new typename Tree<int>::Tree(filename);
    int r=vertexCover(*T);
    cout << "your result: " << r << endl;
    cout << "optimal result: " << res[i] << endl;
    if (r==res[i])
      cout << "SUCCESS\n\n";
    else
      cout << "ERROR\n\n";
 }
  cout << "----------------------- Completed test3.cpp ----------------------- " << endl;
  return EXIT_SUCCESS;
}

