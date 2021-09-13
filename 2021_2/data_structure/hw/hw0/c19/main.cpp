// Suggested solution to textbook exercise C-1.9

#include "vector2.h"
 
int main()       // test function       
{
  double a, b, c, d, e;
  cout << "v1.x= "; cin >> b;
  cout << "v1.y= "; cin >> c;
  cout << "v2.x= "; cin >> d;
  cout << "v2.y= "; cin >> e;
  cout << "a= "; cin >> a;
  Vector2 v1(b,c);
  Vector2 v2(d,e);
  cout << v1 << "+" << v2 << " = " << v1+v2 << endl;
  cout << v1 << "." << v2 << " = " << v1*v2 << endl; 
  cout << v1 << "*" << a << " = " << v1*a << endl; 
  cout << a << "*" << v1 << " = " << a*v1 << endl; 
  return EXIT_SUCCESS;
}
