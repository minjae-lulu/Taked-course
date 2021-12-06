// Check shortest path algorithm on severall instances

#include "graph.h"
#include <vector>

int main(){
  cout << "----------------------- Starting test3.cpp ----------------------- \n";
  
   cout << endl << "Checking shortest path algorithm" << endl;
  Graph G[3];
  vector<string> filename={"instances/instance1.in","instances/instance2.in","instances/instance3.in"};
  vector<vector<string>> sol={
    {"A", "C", "B", "D"},
    {"Ulsan", "Daegu", "Daejeon", "Seoul"},
    {"Gangneung", "Pohang", "Daegu", "Gwangju"}
  };
  Graph::EdgeList eL;
  Graph::EdgeItor eit;
  vector<string> path;
  Graph::Vertex s,t;
  for (int g=0;g<3;g++){
    cout << endl;
    cout << "Reading " << filename[g] << endl;
    G[g].ReadFile(filename[g],s,t);
    path=Dijkstra(G[g],s,t);
    cout << "   solution: \"";
    for (int i=0;i<sol[g].size();i++)
       cout << sol[g][i] << " ";
    cout << "\"" << endl;
    cout << "your result: \"";
    for (int i=0;i<path.size();i++)
       cout << path[i] << " ";
     cout << "\"" << endl;
    if (sol[g].size()!=path.size())
      cout << "ERROR" << endl;
    else {
      bool b=true;
      for(int i=0; i<sol[g].size(); i++)
        if (sol[g][i]!=path[i])
          b=false;
      if (b==true)
        cout << "SUCCESS" << endl;
      else
        cout << "ERROR" << endl;  
    }
  }
 
  cout << endl << "----------------------- Completed test3.cpp ----------------------- " << endl;
  
  return EXIT_SUCCESS;
}

