// Suggested solution to textbook exercise C-2.4

#ifndef LINE_H   // avoid repeated expansion
#define LINE_H

#include <iostream>
 
using namespace std;

class Line{
public:
  Line(double c=0, double d=0) : a(c), b(d){}   // constructor
  double geta() const {return a;}               // accessor
  double getb() const {return b;}               // accessor
  double intersect(Line l);                     // x-coordinate of intersection 
private: 
  double a,b;    // coordinates
};

class Parallel{      // Parallel exception class
public:
  Parallel(const string& err) : errMsg(err) { }
  string getError() { return errMsg; }
private:
  string errMsg;
};

#endif

