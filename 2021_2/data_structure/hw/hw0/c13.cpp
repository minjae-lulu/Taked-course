// Solution to textbook exercise C-1.3
#include <iostream>
#include <vector>
  
using namespace std;
  
bool distinct(const vector<int> &v){
  int n=v.size();
  for(int i=0;i<n;i++)
    for(int j=i+1;j<n;j++)
      if(v[i]==v[j])
        return false;
  return true;
}  
  
int main()
{
  //  input the vector v
  int n;                 
  cout << "Size of the vector: ";
  cin >> n;         
  vector<int> v(n);
  for (int i=0; i<n; i++) 
  	cin >> v[i];
  
  cout << distinct(v) << endl;     // returns the result
  if (distinct(v))
    cout << "The coordinates are distinct." << endl;
  else
    cout << "The coordinates are not distinct." << endl;
  return 0;
}
