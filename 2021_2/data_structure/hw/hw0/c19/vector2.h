// Suggested solution to textbook exercise C-1.9

#ifndef VECTOR2
#define VECTOR2

#include <iostream>
 
using namespace std;

class Vector2{
  public:
    Vector2(double a=0, double b=0) : x(a), y(b){}   // constructor
    double getx() const {return x;}                  // accessor
    double gety() const {return y;}                  // accessor
    Vector2 operator+(const Vector2& v) const;       // vector addition
    Vector2 operator*(const double &a) const;        // product vector*scalar
    double operator*(const Vector2 &v) const;        // dot product
  private: 
    double x,y;    // coordinates
};

Vector2 operator*(const double &a, const Vector2 &v);  // product scalar*vector
             // when the scalar is in lhs, we cannot make it a member function

ostream& operator<<(ostream & out, const Vector2& v);  // prints a vector 

#endif
