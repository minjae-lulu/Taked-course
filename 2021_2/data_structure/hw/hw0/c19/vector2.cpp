// Suggested solution to textbook exercise C-1.9

#include "vector2.h"

Vector2 Vector2::operator+(const Vector2& v) const{  // vector addition   
  Vector2 result;
  result.x=x+v.x;
  result.y=y+v.y;
  return result;
}
Vector2 Vector2::operator*(const double& a) const{   // product by a scalar 
  Vector2 result;
  result.x=a*x;
  result.y=a*y;
  return result;
}

Vector2 operator*(const double &a, const Vector2 &v){  // product scalar*vector
  Vector2 result(a*v.getx(), a*v.gety());              // when the scalar is in lhs, we cannot
  return result;                                       // make it a member function
}

double Vector2::operator*(const Vector2& v) const{     // dot product
  return x*v.x+y*v.y;
}

ostream& operator<<(ostream & out, const Vector2& v){  // prints a vector 
  out << "(" << v.getx() << ", " << v.gety() << ")";
  return out;
}

