// Check shortest path algorithm on severall instances

#include "graph.h"
#include <vector>

int main(){
  cout << "----------------------- Starting test3.cpp ----------------------- \n";
  
   cout << endl << "Checking shortest path algorithm" << endl;
  Graph G[6];
  vector<string> filename={"instances/instance1.in","instances/instance2.in","instances/instance3.in",
    "instances/instance4.in","instances/instance5.in","instances/instance6.in"};
  vector<vector<string>> sol={
    {"A", "C", "B", "D"},
    {"Ulsan", "Daegu", "Daejeon", "Seoul"},
    {"Gangneung", "Pohang", "Daegu", "Gwangju"},
    {"v84","v92","v98","v55","v66","v26"},
    {"v6219","v9331","v5876","v7398","v630","v7150","v8044","v8775","v2430","v5175","v6106","v3059","v1373","v7757"},
    {"v111337","v174889","v191451","v136135","v182604","v34403","v61632","v3108","v184878","v19566","v149974",
      "v181101","v54425","v77918","v14970","v61866","v97675","v168344","v96175","v129948","v106701","v109348"}
  };
  Graph::EdgeList eL;
  Graph::EdgeItor eit;
  vector<string> path;
  Graph::Vertex s,t;
  for (int g=0;g<6;g++){
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

