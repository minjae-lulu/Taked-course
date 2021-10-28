// Checks your implementation of class Board, except for operator>>() and KnightTour()

#include "knight.h"
#include <string>
#include <unistd.h>
#include <ios>

void process_mem_usage(double& vm_usage, double& resident_set);

int main(){
  cout << "----------------------- Starting test2.cpp ----------------------- \n";
  cout << "Board B(5,4)\n";
  Board B(5,4);
  if(B.getm()==5)
    cout << "SUCCESS: getm()=5\n";
  else
    cout << "ERROR: getm()!=5\n";
  if(B.getn()==4)
    cout << "SUCCESS: getm()=4\n";
  else
    cout << "ERROR: getm()!=4\n";
  cout << "Square s(2,3)\n";
  Square s(2,3);
  cout << "B.set(s,7)\n";
  B.set(s,7);
  if(B.get(s)==7)
    cout << "SUCCESS: get(s)=7\n";
  else
    cout << "ERROR: get(s)!=7\n";
  if(B.inRange(s))
    cout << "SUCCESS: inRange(s)=true\n";
  else
    cout << "ERROR: inRange(s)!=true\n";
  cout << "s=Square(4,4)\n";
  s=Square(4,4);
  if(B.inRange(s))
    cout << "ERROR: inRange(s)=true\n";
  else
    cout << "SUCCESS: inRange(s)!=true\n";
  try{
    cout << "B.set(s,2)" << endl;
    B.set(s,2);
  }catch(runtime_error){
    cout << "SUCCESS: exception was thrown\n";
  }
  
  cout << "Board C(\"instance6.in\");\n";
  Board C("instance6.in");
  if(C.getm()==4)
    cout << "SUCCESS: getm()=4\n";
  else
    cout << "ERROR: getm()!=4\n";
  if(C.getn()==5)
    cout << "SUCCESS: getm()=5\n";
  else
    cout << "ERROR: getm()!=5\n";
  cout << "s=Square(1,3);\n";
  s=Square(1,3);
  if (C.get(s)==-1)
    cout << "SUCCESS: C.get(s)=-1\n";
  else
    cout << "ERROR: C.get(s)!=-1\n";
 if (C.nEmpty()==10)
    cout << "SUCCESS: C.nEmpty()=10\n";
  else
    cout << "ERROR: C.nEmpty()!=10\n";
  cout << "Square t=C.emptySquare()\n";
  Square t=C.emptySquare();
  cout << "t = (" << t.geti() << "," << t.getj() << ")\n";
  if (C.get(t)==0)
    cout << "SUCCESS: C.get(t)=0\n";
  else
    cout << "ERROR: C.get(t)!=0\n";
    
  cout << "Creates and destroys boards\n";
  Board *bp;
  for(int i=0;i<1000;i++){
    bp=new Board(10,10);
    delete bp;
  }
  double vm, rss, vm2, rss2;
  process_mem_usage(vm, rss);
  cout << "VM: " << vm << "; RSS: " << rss << endl; 
  for(int j=0;j<100;j++)
    for(int i=0;i<1000;i++){
      bp=new Board(10,10);
      delete bp;
    }
  process_mem_usage(vm2, rss2);
    cout << "VM: " << vm2 << "; RSS: " << rss2 << endl; 
  if(vm2>=2*vm)
    cout << "ERROR: Memory leak detected\n";
  else
    cout << "SUCCESS: No memory leak detected\n";
  cout << "----------------------- Completed test2.cpp ----------------------- " << endl;
  return EXIT_SUCCESS;
}

//////////////////////////////////////////////////////////////////////////////
//
// process_mem_usage(double &, double &) - takes two doubles by reference,
// attempts to read the system-dependent data for a process' virtual memory
// size and resident set size, and return the results in KB.
//
// On failure, returns 0.0, 0.0

void process_mem_usage(double& vm_usage, double& resident_set)
{
   using std::ios_base;
   using std::ifstream;
   using std::string;

   vm_usage     = 0.0;
   resident_set = 0.0;

   // 'file' stat seems to give the most reliable results
   //
   ifstream stat_stream("/proc/self/stat",ios_base::in);

   // dummy vars for leading entries in stat that we don't care about
   //
   string pid, comm, state, ppid, pgrp, session, tty_nr;
   string tpgid, flags, minflt, cminflt, majflt, cmajflt;
   string utime, stime, cutime, cstime, priority, nice;
   string O, itrealvalue, starttime;

   // the two fields we want
   //
   unsigned long vsize;
   long rss;

   stat_stream >> pid >> comm >> state >> ppid >> pgrp >> session >> tty_nr
               >> tpgid >> flags >> minflt >> cminflt >> majflt >> cmajflt
               >> utime >> stime >> cutime >> cstime >> priority >> nice
               >> O >> itrealvalue >> starttime >> vsize >> rss; // don't care about the rest

   stat_stream.close();

   long page_size_kb = sysconf(_SC_PAGE_SIZE) / 1024; // in case x86-64 is configured to use 2MB pages
   vm_usage     = vsize / 1024.0;
   resident_set = rss * page_size_kb;
}

