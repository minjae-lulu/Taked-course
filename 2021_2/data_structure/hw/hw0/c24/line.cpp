// Suggested solution to textbook exercise C-1.9

#include "line.h"
#include <string.h>

double Line::intersect(Line l){
  double c=l.a;
  double d=l.b;
  if (a==c)
  	throw Parallel("The lines y="+to_string(a)+"x+"+to_string(b)+
  	 " and y="+to_string(c)+"x+"+to_string(d)+" are parallel.");
  return (d-b)/(a-c);
}

