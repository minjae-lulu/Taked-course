#include "tree.h"
#include <chrono>
#include <iomanip>

// This program checks correctness of your solution to vertex cover for 8 instance files

typedef typename Tree<int>::Position IntPos;
typedef typename Tree<string>::Position StringPos;

int main(){
  cout << "----------------------- Starting test3.cpp ----------------------- \n\n";
  
  Tree<int> * T;
  int res[]={0,7,9,100,190,254,1792,3289};
  string filename;
  for(int i=0; i<8;i++){
    auto begin = std::chrono::high_resolution_clock::now();
    filename="instances/instance"+to_string(i)+".in";
    cout << "reading " << filename << endl;
    T = new typename Tree<int>::Tree(filename);
    int r=vertexCover(*T);
    auto end = std::chrono::high_resolution_clock::now();
    auto elapsed = std::chrono::duration_cast<std::chrono::nanoseconds>(end - begin);
    cout << "time: " << elapsed.count()*1e-6 << " ms" << endl;
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

