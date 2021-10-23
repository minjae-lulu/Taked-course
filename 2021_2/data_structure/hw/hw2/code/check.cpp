#include <iostream>
#include <fstream>
#include <iomanip>
#include <vector>

using namespace std;


int main(int argc, char *argv[]){
  if(argc!=3){
  	cout << "syntax: check [instance file name][solution file name]\n";
  	return EXIT_SUCCESS;
  }
  ifstream infile(argv[1]);
  vector<vector<int>> B;      // instance
  int m;
  int n;
  if (infile.is_open()){
    infile >> m;
    B.resize(m);
    infile >> n;
    for(int i=0;i<m;i++){
      B[i].resize(n);   
      for (int j=0;j<n;j++)
        infile >> B[i][j];
    }
    infile.close();
  }
  else{
    cout << "ERROR: cannot open instance file\n";
    return EXIT_FAILURE;    
  }
  cout << "--- instance ---\n";
  cout << m << endl << n << endl;
  for(int i=0;i<m;i++){
    for (int j=0;j<n;j++)
      cout << setw(2) << B[i][j] << " ";
    cout << endl;
  }
  infile=ifstream(argv[2]);
  vector<vector<int>> C;               // solution
  if (infile.is_open()){
    int a,b;
    infile >> a;
    infile >> b;
    if (a!=m or b!=n){
      cout << "ERROR: Wrong size \n" << endl;
      return EXIT_SUCCESS;
    }
    C.resize(m);
    for(int i=0;i<m;i++){
      C[i].resize(n);   
      for (int j=0;j<n;j++)
        infile >> C[i][j];
    }
    infile.close();
  }
  else{
    cout << "ERROR: cannot open solution file\n";
    return EXIT_FAILURE;    
  }
  cout << "--- solution ---\n";
  cout << m << endl << n << endl;
  for(int i=0;i<m;i++){
    for (int j=0;j<n;j++)
      cout << setw(2) << C[i][j] << " ";
    cout << endl;
  }
  cout << "---------------\n";
  
  int count=0;           // number of empty squares
  for(int i=0;i<m;i++){
    for(int j=0;j<n;j++){
      if(B[i][j]==0)
        count++;
      if((B[i][j]==-1 and C[i][j]!=-1) or (B[i][j]!=-1 and C[i][j]==-1)){
        cout << "ERROR: obstacles (-1) are not the same\n";
        return EXIT_SUCCESS;
      }
    }
  } 
  for (int k=1;k<=count;k++){
    int a,b,aprev,bprev,a1,b1;
    aprev=a;
    bprev=b;
    a=-1;
    for(int i=0;i<m;i++)
      for(int j=0;j<n;j++)
        if(C[i][j]==k){
          a=i; b=j;
        }
    if(k==1){
      a1=a; b1=b;
    }
    if(a==-1){
      cout << "ERROR: Couldn't find move number " << k << endl;
      return EXIT_SUCCESS;
    }
    if(k>1){
      if(!((abs(a-aprev)==1 and abs(b-bprev)==2) or 
           (abs(a-aprev)==2 and abs(b-bprev)==1))){
        cout << "ERROR at move " << k << endl;
        return EXIT_SUCCESS;   
      }
    }
    if(k==count){
      if(!((abs(a-a1)==1 and abs(b-b1)==2) or 
           (abs(a-a1)==2 and abs(b-b1)==1))){
        cout << "ERROR at move " << k << endl;
        return EXIT_SUCCESS;
      }        
    }
  }
  cout << "SUCCESS\n";
  
  return EXIT_SUCCESS; 
}
