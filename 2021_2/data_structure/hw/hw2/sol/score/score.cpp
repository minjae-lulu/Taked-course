#include "knight.h"
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
  int s[5];
  int max[5]={0,22,12,5,9};
  int weight[5]={0,29,29,15,27};
  double total=0;
  int maxtotal=0;
  for (int i=1;i<=4;i++){
    cout << endl;
    s[i]=countSuccess(string("log")+to_string(i)+string(".txt"));
    cout << "test " << i << " : " << s[i] << " successes out of " << max[i] << endl;
    double score=double(weight[i])*double(s[i])/double(max[i]);
    cout << "normalized score: " <<  score << " out of "<< weight[i] << endl;
    total+=score;
    maxtotal+=weight[i];
  }
  cout << endl << "total: " << int(total) << " out of " << maxtotal << endl;
  return EXIT_SUCCESS;
}

