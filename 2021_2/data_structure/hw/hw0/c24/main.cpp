// Suggested solution to textbook exercise C-2.4


#include "line.h"

int main()       // test function       
{
 Line l[5];       // creates an array of 5 lines
 l[0]=Line(2,1);
 l[1]=Line(3,2);
 l[2]=Line(2,-4);
 l[3]=Line(3,-3);
 l[4]=Line(-2,1);
 for(int i=0;i<5;i++)    // prints out the lines
   cout << "line " << i << ": y=" << l[i].geta() << "x+" << l[i].getb() << endl;
 for(int i=0;i<5;i++)    // tests intersect()
   for(int j=i+1;j<5;j++)
     try{
       double x=l[i].intersect(l[j]);
       cout << "x-coordinate of intersection line "<< i << ", line" << j
          << " = " << x << endl;
     }
     catch(Parallel &pe){   // l[i] and l[j] are parallel
       cout << pe.getError() << endl;   
     }
 return EXIT_SUCCESS;
}
