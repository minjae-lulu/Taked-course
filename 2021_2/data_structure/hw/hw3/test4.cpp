// This program checks whether the destructor causes memory leaks

#include "tree.h"

#include <unistd.h>
#include <ios>
#include <iostream>
#include <fstream>
#include <string>

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

int main(){
  cout << "----------------------- Starting test4.cpp ----------------------- \n";

  double vm, rss, vm2, rss2;
  process_mem_usage(vm, rss);
  cout << "VM: " << vm << "; RSS: " << rss << endl; 

  cout << "Create pointer T to an empty tree\n";
  Tree<int> * T;
  Tree<int>::Position p[10000];
  cout << "Grow the tree and then delete it repeatedly\n";
  for(int k=0; k<10;k++){
    T=new typename Tree<int>::Tree();
    for(int i=0; i<10000; i++){ 
      int r,j;
      r=rand() % 100;
      if(i==0){
        p[0]=T->addRoot(r);
      }
      else{
        j=rand() % i;
        p[i]=T->insertAt(p[j],r);
      }
    }
     delete T;
  }
  
  process_mem_usage(vm, rss);
  cout << "VM: " << vm << "; RSS: " << rss << endl; 

  cout << "Grow the tree and then delete it repeatedly\n";
  for(int k=0; k<100;k++){
    T=new typename Tree<int>::Tree();
    for(int i=0; i<10000; i++){ 
      int r,j;
      r=rand() % 100;
      if(i==0){
        p[0]=T->addRoot(r);
      }
      else{
        j=rand() % i;
        p[i]=T->insertAt(p[j],r);
      }
    }
    delete T;
  }

  process_mem_usage(vm2, rss2);
  cout << "VM: " << vm2 << "; RSS: " << rss2 << endl; 
  
  if(vm2>=2*vm)
    cout << "ERROR: Memory leak detected\n";
  else
    cout << "SUCCESS: No memory leak detected\n";
  
  cout << "----------------------- Completed test4.cpp ----------------------- " << endl;
  return EXIT_SUCCESS;
}

