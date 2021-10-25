#include "knight.h"
#include <string>


// Checks your implementation of class Square

int main(){
  cout << "----------------------- Starting test1.cpp ----------------------- \n";
  cout << "Square s(3,7)" << endl;
  Square s(3,7);
  if (s.geti()==3)
    cout << "SUCCESS: s.geti()=3\n";
  else
    cout << "ERROR: s.geti()!=3\n";
  if (s.getj()==7)
    cout << "SUCCESS: s.getj()=7\n";
  else
    cout << "ERROR: s.getj()!=7\n";
  cout << "s.seti(4)\n" << "s.setj(6)\n";
  s.seti(4); s.setj(6);
  if (s.geti()==4)
    cout << "SUCCESS: s.geti()=4\n";
  else
    cout << "ERROR: s.geti()!=4\n";
  if (s.getj()==6)
    cout << "SUCCESS: s.getj()=6\n";
  else
    cout << "ERROR: s.getj()!=6\n";

  cout << "Square r(4,6)" << endl;
  Square r(4,6);
  if (s==r)
    cout << "SUCCESS: s=r\n";
  else
    cout << "ERROR: s!=r\n";
    
  cout << "r.seti(3)\n";
  r.seti(3);
  if (s==r)
    cout << "ERROR: s=r\n";
  else
    cout << "SUCCESS: s!=r\n";
    
  Square t[9];
  for(int d=1;d<=8;d++)
    t[d]=move(s,d);
  // for(int d=1;d<=8;d++)
  //   cout << t[d].geti() << " " << t[d].getj()<< endl;
  Square u[9];
  u[1]=Square(6,7);
  u[2]=Square(5,8);
  u[3]=Square(3,8);
  u[4]=Square(2,7);
  u[5]=Square(2,5);
  u[6]=Square(3,4);
  u[7]=Square(5,4);
  u[8]=Square(6,5);
  for(int d=1; d<=8;d++)
    if (u[d]==t[d])
      cout << "SUCCESS moving in direction " << d << endl;
    else
      cout << "ERROR moving in direction " << d << endl;
  for(int d=1; d<=8;d++)    
    u[d]=moveBack(u[d],d);
  for(int d=1; d<=8;d++)
    if (u[d]==s)
      cout << "SUCCESS moving back from direction " << d << endl;
    else
      cout << "ERROR moving back from direction " << d << endl;
  cout << "----------------------- Completed test1.cpp ----------------------- " << endl;
  return EXIT_SUCCESS;
}

