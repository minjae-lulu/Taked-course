#include "circlelist.h"
#include <string>
#include <iostream>
#include <fstream>

// compute the final score for all tests

int countSuccess(string filename){
  ifstream in(filename);
  int result=0;
  for (string line; getline(in, line); ){
    if (line.substr(0,7)=="SUCCESS")
      result++;
  }
  in.close();
  return result;
}

int main(){
  int s[6];
  int max[6]={0,14,2,15,4,20};
  double total=0;
  for (int i=1;i<=5;i++){
    s[i]=countSuccess(string("log")+to_string(i)+string(".txt"));
    cout << "test " << i << " : " << s[i] << " successes out of " << max[i] << endl;
    double score=20*double(s[i])/double(max[i]);
    cout << "normalized score: " <<  score << " out of 20" << endl;
    total+=score;
  }
  cout << endl << "total: " << int(total) << " out of 100" << endl;
  return EXIT_SUCCESS;
}

