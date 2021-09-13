// Suggested solution to textbook exercise C-1.6

#include <iostream>
#include <string>
#include <vector>
 
using namespace std;
  
int main()
{
  vector<string> solutions;      // set of solutions
  solutions.push_back("a");      // start with the solution "a"
  
  // this loop inserts character c at each possible position in each current solution 
  for(char c='b'; c<='f';c++){       // loop over letters from 'b' to 'f'
    int s=solutions.size();          // current number of solutions
    int n=solutions[0].size();       // number of characters in the current solutions
    for(int i=0;i<s;i++){
      for(int j=1;j<=n;j++){
        solutions.push_back(solutions[i]);   // copy solutions[i] at the end of the current solutions
        solutions[solutions.size()-1].insert(j,string(1,c));    // inserts character c at position j 
      }
      solutions[i].insert(0,string(1,c));    // this solution still had to be updated
    }
  }
  for(int i=0; i<solutions.size();i++)    // prints out the answer
    cout << solutions[i] << " ";
  cout << endl;
  return EXIT_SUCCESS;
}
